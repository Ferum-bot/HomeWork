package com.github.ferum_bot.api.models;

import com.github.ferum_bot.api.enums.EntityType;
import com.github.ferum_bot.api.manager.ApiManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    public void GetBounds_SomePoint_SuccessGet() {
        var coordinates = new Coord2D(0, 0);
        var point = ApiManager.pointOf(coordinates);

        var actualBounds = point.getBounds();
        var expectedBounds = new BoundBox(
            new Coord2D(0, 0), new Coord2D(0, 0)
        );

        assertEquals(expectedBounds, actualBounds);
        assertEquals(expectedBounds.bottomRightCoordinate(), actualBounds.bottomRightCoordinate());
        assertEquals(expectedBounds.topLeftCoordinate(), actualBounds.topLeftCoordinate());
    }

    @Test
    public void SetPosition_SomePoint_PositionMatches() {
        var coordinates = new Coord2D(0, 0);
        var point = ApiManager.pointOf(coordinates);

        var newCoordinates = new Coord2D(1, 1);
        point.setPosition(newCoordinates);

        var actualPosition = point.getPosition();
        var expectedPosition = new Coord2D(1, 1);

        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void GetPosition_SomePoint_PositionMatches() {
        var coordinates = new Coord2D(0, 0);
        var point = ApiManager.pointOf(coordinates);

        var actualPosition = point.getPosition();
        var expectedPosition = point.getPosition();

        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void GetEntityType_SomePoint_TypeMatches() {
        var coordinates = new Coord2D(0, 0);
        var point = ApiManager.pointOf(coordinates);

        var actualType = point.getEntityType();
        var expectedType = EntityType.POINT;

        assertEquals(expectedType, actualType);
    }
}