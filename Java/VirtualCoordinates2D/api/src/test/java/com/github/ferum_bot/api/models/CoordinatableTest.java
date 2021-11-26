package com.github.ferum_bot.api.models;

import com.github.ferum_bot.api.enums.EntityType;
import com.github.ferum_bot.api.manager.ApiManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatableTest {

    @Test
    public void GetPosition_SomePosition_PositionMatches() {
        var coordinates = new Coord2D(0, 0);
        Coordinatable actualPoint = ApiManager.pointOf(coordinates);
        Coordinatable actualOrigin = ApiManager.originOf(coordinates);
        Coordinatable actualSpace = ApiManager.spaceOf(coordinates);

        var actualPointPosition = actualPoint.getPosition();
        var actualOriginPosition = actualOrigin.getPosition();
        var actualSpacePosition = actualSpace.getPosition();

        var expectedPosition = new Coord2D(0, 0);

        assertEquals(expectedPosition, actualPointPosition);
        assertEquals(expectedPosition, actualOriginPosition);
        assertEquals(expectedPosition, actualSpacePosition);
    }

    @Test
    public void SetPosition_SomePosition_PositionMatches() {
        var initCoordinates = new Coord2D(1, 1);
        Coordinatable actualPoint = ApiManager.pointOf(initCoordinates);
        Coordinatable actualOrigin = ApiManager.originOf(initCoordinates);
        Coordinatable actualSpace = ApiManager.spaceOf(initCoordinates);

        var newCoordinates = new Coord2D(23, -11);
        actualPoint.setPosition(newCoordinates);
        actualOrigin.setPosition(newCoordinates);
        actualSpace.setPosition(newCoordinates);

        var actualPointPosition = actualPoint.getPosition();
        var actualOriginPosition = actualOrigin.getPosition();
        var actualSpacePosition = actualSpace.getPosition();

        var expectedPosition = new Coord2D(23, -11);

        assertEquals(expectedPosition, actualPointPosition);
        assertEquals(expectedPosition, actualOriginPosition);
        assertEquals(expectedPosition, actualSpacePosition);
    }

    @Test
    public void GetEntityType_PointEntity_TypeMatches() {
        var coordinates = new Coord2D(0, 0);
        Coordinatable point = ApiManager.pointOf(coordinates);

        var actualType = point.getEntityType();

        var expectedType = EntityType.POINT;

        assertEquals(expectedType, actualType);
    }

    @Test
    public void GetEntityType_OriginEntity_TypeMatches() {
        var coordinates = new Coord2D(0, 0);
        Coordinatable point = ApiManager.originOf(coordinates);

        var actualType = point.getEntityType();

        var expectedType = EntityType.ORIGIN;

        assertEquals(expectedType, actualType);
    }

    @Test
    public void GetEntityType_SpaceEntity_TypeMatches() {
        var coordinates = new Coord2D(0, 0);
        Coordinatable point = ApiManager.spaceOf(coordinates);

        var actualType = point.getEntityType();

        var expectedType = EntityType.SPACE;

        assertEquals(expectedType, actualType);
    }
}