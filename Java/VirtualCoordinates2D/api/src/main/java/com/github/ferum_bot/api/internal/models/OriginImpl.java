package com.github.ferum_bot.api.internal.models;

import com.github.ferum_bot.api.enums.EntityType;
import com.github.ferum_bot.api.exception.NullCoordinatesException;
import com.github.ferum_bot.api.models.*;

import java.util.Set;

public class OriginImpl implements Origin {

    private Coord2D coordinates;

    public OriginImpl(Coord2D coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public Coord2D getPosition() {
        return null;
    }

    @Override
    public void setPosition(Coord2D position) {
        if (position == null){
            throw new NullCoordinatesException("Position can't be null!");
        }
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.ORIGIN;
    }

    @Override
    public Set<Coordinatable> getChildren() {
        return null;
    }

    @Override
    public BoundBox getBounds() {
        return null;
    }

    @Override
    public boolean addPoint(Point point) {
        return false;
    }

    @Override
    public boolean addOrigin(Origin origin) {
        return false;
    }

    @Override
    public boolean removePoint(Point point) {
        return false;
    }

    @Override
    public boolean removeOrigin(Origin origin) {
        return false;
    }
}
