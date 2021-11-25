package com.github.ferum_bot.api.manager;

import com.github.ferum_bot.api.enums.EntityType;
import com.github.ferum_bot.api.exception.NullCoordinatesException;
import com.github.ferum_bot.api.models.Coord2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiManagerTest {

    @Test
    public void PointOf_Coordinates_SuccessInstance() {
        var coordinates = new Coord2D(0.0, 0.0);

        var actualPoint = ApiManager.pointOf(coordinates);
        var actualType = actualPoint.getEntityType();
        var actualPosition = actualPoint.getPosition();

        var expectedType = EntityType.POINT;
        var expectedPosition = new Coord2D(0,0);

        assertEquals(expectedType, actualType);
        assertEquals(actualPosition, expectedPosition);
    }

    @Test
    public void PointOf_NullCoordinates_ThrowException() {
        Coord2D coordinates = null;

        assertThrows(NullCoordinatesException.class, (() -> {
            ApiManager.pointOf(coordinates);
        }));
    }

    @Test
    public void OriginOf_Coordinates_SuccessInstance() {
        var coordinates = new Coord2D(1, 1);

        var actualOrigin = ApiManager.originOf(coordinates);
        var actualType = actualOrigin.getEntityType();
        var actualPosition = actualOrigin.getPosition();

        var expectedType = EntityType.ORIGIN;
        var expectedPosition = new Coord2D(1, 1);

        assertEquals(expectedType, actualType);
        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void OriginOf_NullCoordinates_ThrowException() {
        Coord2D coordinates = null;

        assertThrows(NullCoordinatesException.class, (() -> {
            ApiManager.originOf(coordinates);
        }));
    }

    @Test
    public void SpaceOf_Coordinates_SuccessInstance() {
        var coordinates = new Coord2D(12, -123);

        var actualSpace = ApiManager.spaceOf(coordinates);
        var actualType = actualSpace.getEntityType();
        var actualPosition = actualSpace.getPosition();

        var expectedType = EntityType.SPACE;
        var expectedPosition = new Coord2D(12, -123);

        assertEquals(expectedType, actualType);
        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void SpaceOf_NullCoordinates_ThrowException() {
        Coord2D coordinates = null;

        assertThrows(NullCoordinatesException.class, (() -> {
            ApiManager.spaceOf(coordinates);
        }));
    }

    @Test
    public void DefaultSpace_DefaultState_SuccessInstance() {
        var actualSpace = ApiManager.defaultSpace();
        var actualType = actualSpace.getEntityType();
        var actualPosition = actualSpace.getPosition();

        var expectedType = EntityType.SPACE;
        var expectedPosition = new Coord2D(0, 0);

        assertEquals(expectedType, actualType);
        assertEquals(expectedPosition, actualPosition);
    }
}