package com.github.ferum_bot.api.internal.serialization;

import com.github.ferum_bot.api.exception.DAGSerializationException;
import com.github.ferum_bot.api.manager.ApiManager;
import com.github.ferum_bot.api.models.Coord2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerializationServiceTest {

    private SerializationService service;

    @BeforeEach
    public void beforeTest() {
        service = new CustomSerializationService();
    }

    @AfterEach
    public void AfterTest() {
        service = null;
    }

    @Test
    public void SerializeDAG_DAG1_ValidString() {
        var space = ApiManager.defaultSpace();

        var actualString = service.serializeDAG(space);
        var expectedString = """
            {
            type: SPACE
            coordinates: (0.0, 0.0)
            children:
            [
            ]
            }
            """;

        assertEquals(expectedString, actualString);
    }

    @Test
    public void SerializeDAG_DAG2_ValidString() {
        var space = ApiManager.defaultSpace();
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(1, 2));
        var point3 = ApiManager.pointOf(new Coord2D(-32, 0.1));
        var point4 = ApiManager.pointOf(new Coord2D(123.321, 17));

        space.addPoint(point1);
        space.addPoint(point2);
        space.addPoint(point3);
        space.addPoint(point4);

        var actualString = service.serializeDAG(space);
        var expectedString = """
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

        assertEquals(expectedString, actualString);
    }

    @Test
    public void DeSerializeDAG_DAG1_ValidDAG() {
        var string = """
            {
            type: SPACE
            coordinates: (0.0, 0.0)
            children:
            [
            ]
            }
            """;

        var actualSpace = service.deSerializeDAG(string);
        var expectedSpace = ApiManager.defaultSpace();

        assertEquals(expectedSpace, actualSpace);
    }

    @Test
    public void DeSerializeDAG_DAG2_ValidDAG() {
        var string = """
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

        var actualSpace = service.deSerializeDAG(string);
        var expectedSpace = ApiManager.defaultSpace();
        var point1 = ApiManager.pointOf(new Coord2D(-32, 0.1));
        var point2 = ApiManager.pointOf(new Coord2D(123.321, 17));
        var point3 = ApiManager.pointOf(new Coord2D(0, 0));
        var point4 = ApiManager.pointOf(new Coord2D(1, 2));
        expectedSpace.addPoint(point1);
        expectedSpace.addPoint(point2);
        expectedSpace.addPoint(point3);
        expectedSpace.addPoint(point4);


        assertEquals(expectedSpace, actualSpace);
        assertEquals(expectedSpace.getChildren(), actualSpace.getChildren());
    }

    @Test
    public void DeSerializeDAG_InValidDAG_ThrowException() {
        var string = "adsasdasd";

        assertThrows(DAGSerializationException.class, (() -> {
            service.deSerializeDAG(string);
        }));
    }

}