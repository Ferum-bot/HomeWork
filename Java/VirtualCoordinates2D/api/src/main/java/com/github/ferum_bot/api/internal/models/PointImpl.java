package com.github.ferum_bot.api.internal.models;

import com.github.ferum_bot.api.enums.EntityType;
import com.github.ferum_bot.api.exception.NullCoordinatesException;
import com.github.ferum_bot.api.models.BoundBox;
import com.github.ferum_bot.api.models.Coord2D;
import com.github.ferum_bot.api.models.Point;

public class PointImpl implements Point {

    private Coord2D coordinates;

    public PointImpl(Coord2D coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public Coord2D getPosition() {
        return coordinates;
    }

    @Override
    public void setPosition(Coord2D position) {
        if (position == null) {
            throw new NullCoordinatesException("Position can't be null");
        }
        this.coordinates = position;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.POINT;
    }

    @Override
    public BoundBox getBounds() {
        return new BoundBox(coordinates, coordinates);
    }
}
