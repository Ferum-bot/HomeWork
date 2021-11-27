package com.github.ferum_bot.api.models;

import com.github.ferum_bot.api.enums.EntityType;
import com.github.ferum_bot.api.exception.DAGConstraintException;
import com.github.ferum_bot.api.manager.ApiManager;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {

    @Test
    public void GetPosition_SomeSpace_PositionMatches() {
        var space = ApiManager.spaceOf(new Coord2D(1, 1));

        var actualPosition = space.getPosition();
        var expectedPosition = new Coord2D(1, 1);

        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void SetPosition_SomeSpace_PositionMatches() {
        var space = ApiManager.spaceOf(new Coord2D(1, 1));
        space.setPosition(new Coord2D(2, 2));

        var actualPosition = space.getPosition();
        var expectedPosition = new Coord2D(2, 2);

        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void GetEntityType_SomeSpace_TypeOrigin() {
        var space = ApiManager.spaceOf(new Coord2D(2, 2));

        var actualType = space.getEntityType();
        var expectedType = EntityType.SPACE;

        assertEquals(expectedType, actualType);
    }

    @Test
    public void GetChildren_SomeSpace_ChildrenMatches() {
        var space = ApiManager.spaceOf(new Coord2D(-2.3, 5));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(1, 1));
        var point3 = ApiManager.pointOf(new Coord2D(2, 2));

        space.addPoint(point1);
        space.addPoint(point2);
        space.addPoint(point3);

        var actualChildren = space.getChildren();
        var expectedChildren = new HashSet<Coordinatable>();
        expectedChildren.add(point1);
        expectedChildren.add(point2);
        expectedChildren.add(point3);

        assertEquals(expectedChildren, actualChildren);
    }

    @Test
    public void GetBounds_SomeSpace1_BoundsMatches() {
        var space = ApiManager.spaceOf(new Coord2D(0, 0));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(5, 5));
        var point3 = ApiManager.pointOf(new Coord2D(-1, -2));

        space.addPoint(point1);
        space.addPoint(point2);
        space.addPoint(point3);

        var actualBounds = space.getBounds();
        var expectedBounds = new BoundBox(
                new Coord2D(-1, 5), new Coord2D(5, -2)
        );

        assertEquals(expectedBounds, actualBounds);
        assertEquals(expectedBounds.topLeftCoordinate(), actualBounds.topLeftCoordinate());
        assertEquals(expectedBounds.bottomRightCoordinate(), actualBounds.bottomRightCoordinate());
    }

    @Test
    public void GetBounds_SomeSpace2_BoundsMatches() {
        var space = ApiManager.spaceOf(new Coord2D(-1, -1));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(1.3, 20));
        var origin1 = ApiManager.originOf(new Coord2D(9.7, 2));
        var point3 = ApiManager.pointOf(new Coord2D(6, 6));

        space.addPoint(point1);
        space.addPoint(point2);
        space.addOrigin(origin1);
        origin1.addPoint(point3);

        var actualBounds = space.getBounds();
        var expectedBounds = new BoundBox(
                new Coord2D(-1.0, 19.0), new Coord2D(14.7, -1.0)
        );

        assertEquals(expectedBounds, actualBounds);
        assertEquals(expectedBounds.topLeftCoordinate(), actualBounds.topLeftCoordinate());
        assertEquals(expectedBounds.bottomRightCoordinate(), actualBounds.bottomRightCoordinate());
    }

    @Test
    public void GetBounds_SomeSpace3_BoundsMatches() {
        var space = ApiManager.spaceOf(new Coord2D(0, 0));
        var point1 = ApiManager.pointOf(new Coord2D(0, 0));
        var point2 = ApiManager.pointOf(new Coord2D(-13, 3));
        var origin1 = ApiManager.originOf(new Coord2D(-123, 19));
        var origin2 = ApiManager.originOf(new Coord2D(17, 22));
        var point3 = ApiManager.pointOf(new Coord2D(72.2, 0));
        var point4 = ApiManager.pointOf(new Coord2D(89, 1));

        space.addPoint(point1);
        space.addPoint(point2);
        space.addOrigin(origin1);
        space.addOrigin(origin2);
        origin1.addPoint(point1);
        origin1.addPoint(point3);
        origin1.addPoint(point4);
        origin2.addPoint(point2);
        origin2.addPoint(point3);

        var actualBounds = space.getBounds();
        var expectedBounds = new BoundBox(
                new Coord2D(-123.0, 25.0), new Coord2D(89.2, 0)
        );

        assertEquals(expectedBounds, actualBounds);
        assertEquals(expectedBounds.topLeftCoordinate(), actualBounds.topLeftCoordinate());
        assertEquals(expectedBounds.bottomRightCoordinate(), actualBounds.bottomRightCoordinate());
    }

    @Test
    public void AddPoint_SomeSpace_ChildrenContainsPoint() {
        var space = ApiManager.spaceOf(new Coord2D(-123.2, 0));
        var point = ApiManager.pointOf(new Coord2D(12, 12));

        assertTrue(space.addPoint(point));

        var actualChildren = space.getChildren();

        assertTrue(actualChildren.contains(point));
    }

    @Test
    public void AddOrigin_SomeSpace_ChildrenContainsOrigin() {
        var space = ApiManager.spaceOf(new Coord2D(2, 2));
        var newOrigin = ApiManager.originOf(new Coord2D(-99, 32));

        assertTrue(space.addOrigin(newOrigin));

        var actualChildren = space.getChildren();

        assertTrue(actualChildren.contains(newOrigin));
    }

    @Test
    public void AddOrigin_SomeSpace1_ThrowsConstraintException() {
        var space = ApiManager.spaceOf(new Coord2D(0, 0));
        var point1 = ApiManager.pointOf(new Coord2D(1, 1));
        var point2 = ApiManager.pointOf(new Coord2D(3, 3));
        var origin1 = ApiManager.originOf(new Coord2D(-3.2, -3.9));
        var origin2 = ApiManager.originOf(new Coord2D(7, 7));

        space.addPoint(point1);
        space.addPoint(point2);
        space.addOrigin(origin1);
        space.addOrigin(origin2);
        origin1.addPoint(point1);
        origin1.addPoint(point2);
        origin2.addPoint(point1);
        origin2.addPoint(point2);

        assertThrows(DAGConstraintException.class, (() -> {
            origin2.addOrigin(origin2);
        }));

        assertThrows(DAGConstraintException.class, (() -> {
            origin1.addOrigin(origin1);
        }));
    }

    @Test
    public void AddOrigin_SomeSpace2_ThrowsConstraintException() {
        var space = ApiManager.spaceOf(new Coord2D(0, 0));
        var origin1 = ApiManager.originOf(new Coord2D(2, 2));
        var origin2 = ApiManager.originOf(new Coord2D(5, 5));
        var origin3 = ApiManager.originOf(new Coord2D(-1, 1));
        var origin4 = ApiManager.originOf(new Coord2D(2, 12));

        space.addOrigin(origin1);
        origin1.addOrigin(origin2);
        origin2.addOrigin(origin3);
        origin3.addOrigin(origin4);

        assertThrows(DAGConstraintException.class, (() -> {
            origin1.addOrigin(origin1);
        }));

        assertThrows(DAGConstraintException.class, (() -> {
            origin2.addOrigin(origin2);
        }));

        assertThrows(DAGConstraintException.class, (() -> {
            origin3.addOrigin(origin3);
        }));

        assertThrows(DAGConstraintException.class, (() -> {
            origin4.addOrigin(origin4);
        }));
    }

    @Test
    public void RemovePoint_SomeSpace_ChildrenWithoutPoint() {
        var space = ApiManager.spaceOf(new Coord2D(0, 0));
        var point = ApiManager.pointOf(new Coord2D(1, 1));

        space.addPoint(point);

        assertTrue(space.removePoint(point));

        var children = space.getChildren();

        assertFalse(children.contains(point));
    }

    @Test
    public void RemoveOrigin_SomeSpace_ChildrenWithoutOrigin() {
        var space = ApiManager.spaceOf(new Coord2D(-2, 1));
        var newOrigin = ApiManager.originOf(new Coord2D(2, 2));

        space.addOrigin(newOrigin);

        assertTrue(space.removeOrigin(newOrigin));

        var children = space.getChildren();

        assertFalse(children.contains(newOrigin));
    }
}