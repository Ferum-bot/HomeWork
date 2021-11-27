package com.github.ferum_bot.api.models;

import com.github.ferum_bot.api.enums.EntityType;
import com.github.ferum_bot.api.exception.DAGConstraintException;
import com.github.ferum_bot.api.manager.ApiManager;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class OriginTest {

    @Test
    public void GetPosition_SomeOrigin_PositionMatches() {
        var origin = ApiManager.originOf(new Coord2D(1, 1));

        var actualPosition = origin.getPosition();
        var expectedPosition = new Coord2D(1, 1);

        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void SetPosition_SomeOrigin_PositionMatches() {
        var origin = ApiManager.originOf(new Coord2D(1, 1));
        origin.setPosition(new Coord2D(2, 2));

        var actualPosition = origin.getPosition();
        var expectedPosition = new Coord2D(2, 2);

        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void GetEntityType_SomeOrigin_TypeOrigin() {
        var origin = ApiManager.originOf(new Coord2D(2, 2));

        var actualType = origin.getEntityType();
        var expectedType = EntityType.ORIGIN;

        assertEquals(expectedType, actualType);
    }

    @Test
    public void GetChildren_SomeOrigin_ChildrenMatches() {
        var origin = ApiManager.originOf(new Coord2D(-2.3, 5));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(1, 1));
        var point3 = ApiManager.pointOf(new Coord2D(2, 2));

        origin.addPoint(point1);
        origin.addPoint(point2);
        origin.addPoint(point3);

        var actualChildren = origin.getChildren();
        var expectedChildren = new HashSet<Coordinatable>();
        expectedChildren.add(point1);
        expectedChildren.add(point2);
        expectedChildren.add(point3);

        assertEquals(expectedChildren, actualChildren);
    }

    @Test
    public void GetBounds_SomeOrigin1_BoundsMatches() {
        var origin = ApiManager.originOf(new Coord2D(0, 0));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(5, 5));
        var point3 = ApiManager.pointOf(new Coord2D(-1, -2));

        origin.addPoint(point1);
        origin.addPoint(point2);
        origin.addPoint(point3);

        var actualBounds = origin.getBounds();
        var expectedBounds = new BoundBox(
            new Coord2D(-1, 5), new Coord2D(5, -2)
        );

        assertEquals(expectedBounds, actualBounds);
        assertEquals(expectedBounds.topLeftCoordinate(), actualBounds.topLeftCoordinate());
        assertEquals(expectedBounds.bottomRightCoordinate(), actualBounds.bottomRightCoordinate());
    }

    @Test
    public void GetBounds_SomeOrigin2_BoundsMatches() {
        var origin = ApiManager.originOf(new Coord2D(-1, -1));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(1.3, 20));
        var origin1 = ApiManager.originOf(new Coord2D(9.7, 2));
        var point3 = ApiManager.pointOf(new Coord2D(6, 6));

        origin.addPoint(point1);
        origin.addPoint(point2);
        origin.addOrigin(origin1);
        origin1.addPoint(point3);

        var actualBounds = origin.getBounds();
        var expectedBounds = new BoundBox(
            new Coord2D(-1.0, 19.0), new Coord2D(14.7, -1.0)
        );

        assertEquals(expectedBounds, actualBounds);
        assertEquals(expectedBounds.topLeftCoordinate(), actualBounds.topLeftCoordinate());
        assertEquals(expectedBounds.bottomRightCoordinate(), actualBounds.bottomRightCoordinate());
    }

    @Test
    public void GetBounds_SomeOrigin3_BoundsMatches() {
        var origin = ApiManager.originOf(new Coord2D(0, 0));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(-13, 3));
        var origin1 = ApiManager.originOf(new Coord2D(-123, 19));
        var origin2 = ApiManager.originOf(new Coord2D(17, 22));
        var point3 = ApiManager.pointOf(new Coord2D(72.2, 0));
        var point4 = ApiManager.pointOf(new Coord2D(89, 1));

        origin.addPoint(point1);
        origin.addPoint(point2);
        origin.addOrigin(origin1);
        origin.addOrigin(origin2);
        origin1.addPoint(point1);
        origin1.addPoint(point3);
        origin1.addPoint(point4);
        origin2.addPoint(point2);
        origin2.addPoint(point3);

        var actualBounds = origin.getBounds();
        var expectedBounds = new BoundBox(
            new Coord2D(-123.0, 25.0), new Coord2D(89.2, 0)
        );

        assertEquals(expectedBounds, actualBounds);
        assertEquals(expectedBounds.topLeftCoordinate(), actualBounds.topLeftCoordinate());
        assertEquals(expectedBounds.bottomRightCoordinate(), actualBounds.bottomRightCoordinate());
    }

    @Test
    public void AddPoint_SomeOrigin_ChildrenContainsPoint() {
        var origin = ApiManager.originOf(new Coord2D(-123.2, 0));
        var point = ApiManager.pointOf(new Coord2D(12, 12));

        assertTrue(origin.addPoint(point));

        var actualChildren = origin.getChildren();

        assertTrue(actualChildren.contains(point));
    }

    @Test
    public void AddOrigin_SomeOrigin_ChildrenContainsOrigin() {
        var origin = ApiManager.originOf(new Coord2D(2, 2));
        var newOrigin = ApiManager.originOf(new Coord2D(-99, 32));

        assertTrue(origin.addOrigin(newOrigin));

        var actualChildren = origin.getChildren();

        assertTrue(actualChildren.contains(newOrigin));
    }

    @Test
    public void AddOrigin_SomeOrigin1_ThrowsConstraintException() {
        var origin = ApiManager.originOf(new Coord2D(0, 0));
        var point1 = ApiManager.pointOf(new Coord2D(1, 1));
        var point2 = ApiManager.pointOf(new Coord2D(3, 3));
        var origin1 = ApiManager.originOf(new Coord2D(-3.2, -3.9));
        var origin2 = ApiManager.originOf(new Coord2D(7, 7));

        origin.addPoint(point1);
        origin.addPoint(point2);
        origin.addOrigin(origin1);
        origin.addOrigin(origin2);
        origin1.addPoint(point1);
        origin1.addPoint(point2);
        origin2.addPoint(point1);
        origin2.addPoint(point2);

        assertThrows(DAGConstraintException.class, (() -> {
            origin2.addOrigin(origin);
        }));

        assertThrows(DAGConstraintException.class, (() -> {
            origin1.addOrigin(origin);
        }));
    }

    @Test
    public void AddOrigin_SomeOrigin2_ThrowsConstraintException() {
        var origin = ApiManager.originOf(new Coord2D(0, 0));
        var origin1 = ApiManager.originOf(new Coord2D(2, 2));
        var origin2 = ApiManager.originOf(new Coord2D(5, 5));
        var origin3 = ApiManager.originOf(new Coord2D(-1, 1));
        var origin4 = ApiManager.originOf(new Coord2D(2, 12));

        origin.addOrigin(origin1);
        origin1.addOrigin(origin2);
        origin2.addOrigin(origin3);
        origin3.addOrigin(origin4);

        assertThrows(DAGConstraintException.class, (() -> {
            origin1.addOrigin(origin);
        }));

        assertThrows(DAGConstraintException.class, (() -> {
            origin2.addOrigin(origin);
        }));

        assertThrows(DAGConstraintException.class, (() -> {
            origin3.addOrigin(origin);
        }));

        assertThrows(DAGConstraintException.class, (() -> {
            origin4.addOrigin(origin);
        }));
    }

    @Test
    public void RemovePoint_SomeOrigin_ChildrenWithoutPoint() {
        var origin = ApiManager.originOf(new Coord2D(0, 0));
        var point = ApiManager.pointOf(new Coord2D(1, 1));

        origin.addPoint(point);

        assertTrue(origin.removePoint(point));

        var children = origin.getChildren();

        assertFalse(children.contains(point));
    }

    @Test
    public void RemoveOrigin_SomeOrigin_ChildrenWithoutOrigin() {
        var origin = ApiManager.originOf(new Coord2D(-2, 1));
        var newOrigin = ApiManager.originOf(new Coord2D(2, 2));

        origin.addOrigin(newOrigin);

        assertTrue(origin.removeOrigin(newOrigin));

        var children = origin.getChildren();

        assertFalse(children.contains(newOrigin));
    }
}