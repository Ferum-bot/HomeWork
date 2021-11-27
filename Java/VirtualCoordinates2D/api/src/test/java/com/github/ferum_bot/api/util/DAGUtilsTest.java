package com.github.ferum_bot.api.util;

import com.github.ferum_bot.api.exception.DAGSerializationException;
import com.github.ferum_bot.api.manager.ApiManager;
import com.github.ferum_bot.api.models.Coord2D;
import com.github.ferum_bot.api.models.Space;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAGUtilsTest {

    private final String EMPTY_GRAPH = """
            {
            type: SPACE
            coordinates: (0.0, 0.0)
            children:
            [
            ]
            }
            """;

    private final String GRAPH_1 = """
            {
            type: SPACE
            coordinates: (0.0, 0.0)
            children:
            [
            {
            type: POINT
            coordinates: (-32.0, 0.1)
            }
            {
            type: POINT
            coordinates: (123.321, 17.0)
            }
            {
            type: POINT
            coordinates: (0.0, 0.0)
            }
            {
            type: POINT
            coordinates: (1.0, 2.0)
            }
            ]
            }
            """;

    private final String GRAPH_2 = """
            {
            type: SPACE
            coordinates: (0.0, 0.0)
            children:
            [
            {
            type: POINT
            coordinates: (1.0, 1.0)
            }
            {
            type: POINT
            coordinates: (2.0, 2.0)
            }
            {
            type: ORIGIN
            coordinates: (5.0, 5.0)
            children:
            [
            {
            type: POINT
            coordinates: (0.0, 0.0)
            }
            ]
            }
            ]
            }
            """;

    private final String GRAPH_3 = """
            {
            type: SPACE
            coordinates: (-100.0, 763.0)
            children:
            [
            {
            type: POINT
            coordinates: (0.0, 0.0)
            }
            {
            type: ORIGIN
            coordinates: (0.0, 0.0)
            children:
            [
            {
            type: POINT
            coordinates: (5.0, 0.0)
            }
            {
            type: POINT
            coordinates: (3.0, 0.0)
            }
            {
            type: POINT
            coordinates: (1.0, 0.0)
            }
            {
            type: POINT
            coordinates: (2.0, 0.0)
            }
            {
            type: POINT
            coordinates: (4.0, 0.0)
            }
            ]
            }
            {
            type: ORIGIN
            coordinates: (1.0, 1.0)
            children:
            [
            {
            type: POINT
            coordinates: (5.0, 0.0)
            }
            ]
            }
            {
            type: ORIGIN
            coordinates: (2.0, 2.0)
            children:
            [
            ]
            }
            ]
            }
            """;

    private final String GRAPH_4 = """
            {
            type: SPACE
            coordinates: (0.0, 0.0)
            children:
            [
            {
            type: ORIGIN
            coordinates: (0.0, 0.0)
            children:
            [
            {
            type: POINT
            coordinates: (0.0, 0.0)
            }
            {
            type: POINT
            coordinates: (1.0, 1.0)
            }
            ]
            }
            {
            type: ORIGIN
            coordinates: (2.0, 2.0)
            children:
            [
            {
            type: POINT
            coordinates: (0.0, 0.0)
            }
            {
            type: POINT
            coordinates: (1.0, 1.0)
            }
            ]
            }
            {
            type: ORIGIN
            coordinates: (3.0, 3.0)
            children:
            [
            {
            type: ORIGIN
            coordinates: (0.0, 0.0)
            children:
            [
            {
            type: POINT
            coordinates: (0.0, 0.0)
            }
            {
            type: POINT
            coordinates: (1.0, 1.0)
            }
            ]
            }
            ]
            }
            ]
            }
            """;

    private final String GRAPH_5 = """
            {
            type: SPACE
            coordinates: (0.0, 0.0)
            children:
            [
            {
            type: ORIGIN
            coordinates: (1.0, 1.0)
            children:
            [
            {
            type: POINT
            coordinates: (0.0, 0.0)
            }
            {
            type: POINT
            coordinates: (2.0, 2.0)
            }
            {
            type: ORIGIN
            coordinates: (6.0, 6.0)
            children:
            [
            {
            type: POINT
            coordinates: (4.0, 4.0)
            }
            {
            type: POINT
            coordinates: (-5.0, 12.3)
            }
            ]
            }
            ]
            }
            {
            type: ORIGIN
            coordinates: (5.0, 5.0)
            children:
            [
            {
            type: ORIGIN
            coordinates: (-3.0, -1.0)
            children:
            [
            {
            type: POINT
            coordinates: (4.0, 4.0)
            }
            {
            type: POINT
            coordinates: (-5.0, 12.3)
            }
            ]
            }
            {
            type: POINT
            coordinates: (3.0, 3.0)
            }
            ]
            }
            {
            type: POINT
            coordinates: (0.0, 0.0)
            }
            {
            type: POINT
            coordinates: (1.0, 1.0)
            }
            ]
            }
            """;

    @Test
    public void ExportAsString_EmptyGraph_SuccessExport() {
        var space = ApiManager.defaultSpace();

        var actualString = DAGUtils.exportAsString(space);
        var expectedString = EMPTY_GRAPH;

        assertEquals(expectedString, actualString);
    }

    @Test
    public void ExportAsString_SomeGraph1_SuccessExport() {
        var space = buildGraph1();

        var actualString = DAGUtils.exportAsString(space);
        var expectedString = GRAPH_1;

        assertEquals(expectedString, actualString);
    }

    @Test
    public void ExportAsString_SomeGraph2_SuccessExport() {
        var space = buildGraph2();

        var actualString = DAGUtils.exportAsString(space);
        var expectedString = GRAPH_2;

        assertEquals(expectedString, actualString);
    }

    @Test
    public void ExportAsString_SomeGraph3_SuccessExport() {
        var space = buildGraph3();

        var actualString = DAGUtils.exportAsString(space);
        var expectedString = GRAPH_3;

        assertEquals(expectedString, actualString);
    }

    @Test
    public void ExportAsString_SomeGraph4_SuccessExport() {
        var space = buildGraph4();

        var actualString = DAGUtils.exportAsString(space);
        var expectedString = GRAPH_4;

        assertEquals(expectedString, actualString);
    }

    @Test
    public void ExportAsString_SomeGraph5_SuccessExport() {
        var space = buildGraph5();

        var actualString = DAGUtils.exportAsString(space);
        var expectedString = GRAPH_5;

        assertEquals(expectedString, actualString);
    }

    @Test
    public void ImportFromString_EmptyGraph_SuccessImport() {
        var string = EMPTY_GRAPH;

        var actualSpace = DAGUtils.importFromString(string);
        var expectedSpace = ApiManager.defaultSpace();

        assertEquals(expectedSpace, actualSpace);
    }

    @Test
    public void ImportFromString_SomeGraph1_SuccessImport() {
        var string = GRAPH_1;

        var actualSpace = DAGUtils.importFromString(string);
        var expectedSpace = buildGraph1();

        assertTrue(simpleEquals(expectedSpace, actualSpace));
    }

    @Test
    public void ImportFromString_SomeGraph2_SuccessImport() {
        var string = GRAPH_2;

        var actualSpace = DAGUtils.importFromString(string);
        var expectedSpace = buildGraph2();

        assertTrue(simpleEquals(expectedSpace, actualSpace));
    }

    @Test
    public void ImportFromString_SomeGraph3_SuccessImport() {
        var string = GRAPH_3;

        var actualSpace = DAGUtils.importFromString(string);
        var expectedSpace = buildGraph3();

        assertTrue(simpleEquals(expectedSpace, actualSpace));
    }

    @Test
    public void ImportFromString_SomeGraph4_SuccessImport() {
        var string = GRAPH_4;

        var actualSpace = DAGUtils.importFromString(string);
        var expectedSpace = buildGraph4();

        assertTrue(simpleEquals(expectedSpace, actualSpace));
    }

    @Test
    public void ImportFromString_SomeGraph5_SuccessImport() {
        var string = GRAPH_5;

        var actualSpace = DAGUtils.importFromString(string);
        var expectedSpace = buildGraph5();

        assertTrue(simpleEquals(expectedSpace, actualSpace));
    }

    @Test
    public void ImportFromString_InvalidGraph1_ThrowException() {
        var actualString = """
                {
                }
                """;

        assertThrows(DAGSerializationException.class, (() -> {
            DAGUtils.importFromString(actualString);
        }));
    }

    @Test
    public void ImportFromString_InvalidGraph2_ThrowException() {
        var actualString = """
                {
                type: ORIGIN
                coordinates: (0.0, 0.0)
                children:
                [
                ]
                }
                """;

        assertThrows(DAGSerializationException.class, (() -> {
            DAGUtils.importFromString(actualString);
        }));
    }

    @Test
    public void ImportFromString_InvalidGraph3_ThrowException() {
        var actualString = """
                {
                type: SPACE
                coordinates: (0.0, 0.0)
                children:
                [
                {
                type: POINT
                coordinatess: (0.0, 0.0)
                children:
                [
                ]
                }
                ]
                }
                """;

        assertThrows(DAGSerializationException.class, (() -> {
            DAGUtils.importFromString(actualString);
        }));
    }

    private Space buildGraph1() {
        var space = ApiManager.defaultSpace();
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(1, 2));
        var point3 = ApiManager.pointOf(new Coord2D(-32, 0.1));
        var point4 = ApiManager.pointOf(new Coord2D(123.321, 17));

        space.addPoint(point1);
        space.addPoint(point2);
        space.addPoint(point3);
        space.addPoint(point4);

        return space;
    }

    private Space buildGraph2() {
        var space = ApiManager.defaultSpace();
        var point1 = ApiManager.pointOf(new Coord2D(1, 1));
        var point2 = ApiManager.pointOf(new Coord2D(2, 2));
        var origin1 = ApiManager.originOf(new Coord2D(5, 5));
        var point3 = ApiManager.pointOf(new Coord2D(0, 0));

        space.addPoint(point1);
        space.addPoint(point2);
        space.addOrigin(origin1);
        origin1.addPoint(point3);

        return space;
    }

    private Space buildGraph3() {
        var space = ApiManager.spaceOf(new Coord2D(-100, 763));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var origin1 = ApiManager.originOf(new Coord2D(0, 0));
        var origin2 = ApiManager.originOf(new Coord2D(1, 1));
        var origin3 = ApiManager.originOf(new Coord2D(2, 2));
        var point2 = ApiManager.pointOf(new Coord2D(1, 0));
        var point3 = ApiManager.pointOf(new Coord2D(2, 0));
        var point4 = ApiManager.pointOf(new Coord2D(3, 0));
        var point5 = ApiManager.pointOf(new Coord2D(4, 0));
        var point6 = ApiManager.pointOf(new Coord2D(5, 0));

        space.addPoint(point1);
        space.addOrigin(origin1);
        space.addOrigin(origin2);
        space.addOrigin(origin3);
        origin1.addPoint(point2);
        origin1.addPoint(point3);
        origin1.addPoint(point4);
        origin1.addPoint(point5);
        origin1.addPoint(point6);
        origin2.addPoint(point6);

        return space;
    }

    private Space buildGraph4() {
        var space = ApiManager.defaultSpace();
        var origin1 = ApiManager.originOf(new Coord2D(0, 0));
        var origin2 = ApiManager.originOf(new Coord2D(2, 2));
        var origin3 = ApiManager.originOf(new Coord2D(3, 3));
        var origin4 = ApiManager.originOf(new Coord2D(0, 0));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(1, 1));

        space.addOrigin(origin1);
        space.addOrigin(origin2);
        space.addOrigin(origin3);
        origin3.addOrigin(origin4);
        origin1.addPoint(point1);
        origin1.addPoint(point2);
        origin2.addPoint(point1);
        origin2.addPoint(point2);
        origin4.addPoint(point1);
        origin4.addPoint(point2);

        return space;
    }

    private Space buildGraph5() {
        var space = ApiManager.defaultSpace();
        var origin1 = ApiManager.originOf(new Coord2D(1, 1));
        var origin2 = ApiManager.originOf(new Coord2D(5, 5));
        var origin3 = ApiManager.originOf(new Coord2D(6, 6));
        var origin4 = ApiManager.originOf(new Coord2D(-3, -1));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(1, 1));
        var point3 = ApiManager.pointOf(new Coord2D(2, 2));
        var point4 = ApiManager.pointOf(new Coord2D(3, 3));
        var point5 = ApiManager.pointOf(new Coord2D(4, 4));
        var point6 = ApiManager.pointOf(new Coord2D(-5, 12.3));

        space.addOrigin(origin1);
        space.addOrigin(origin2);
        space.addPoint(point1);
        space.addPoint(point2);
        origin1.addPoint(point1);
        origin1.addPoint(point3);
        origin1.addOrigin(origin3);
        origin2.addOrigin(origin4);
        origin2.addPoint(point4);
        origin3.addPoint(point5);
        origin3.addPoint(point6);
        origin4.addPoint(point5);
        origin4.addPoint(point6);

        return space;
    }

    private boolean simpleEquals(Space first, Space second) {
        if (!first.equals(second)) {
            return false;
        }
        return first.getChildren().equals(second.getChildren()) &&
                first.getBounds().equals(second.getBounds());
    }
}