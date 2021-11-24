package com.github.ferum_bot.api.internal.models;

import com.github.ferum_bot.api.enums.EntityType;
import com.github.ferum_bot.api.models.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SpaceImpl implements Space {

    private Coord2D coodinates;

    private Set<Coordinatable> entities = new HashSet<>();

    public SpaceImpl(Coord2D coordinates) {
        this.coodinates = coordinates;
    }

    @Override
    public Coord2D getPosition() {
        return null;
    }

    @Override
    public void setPosition(Coord2D position) {

    }

    @Override
    public EntityType getEntityType() {
        return EntityType.SPACE;
    }

    @Override
    public Set<Coordinatable> getChildren() {
        return Collections.unmodifiableSet(entities);
    }

    @Override
    public BoundBox getBounds() {
        return null;
    }

    @Override
    public boolean addPoint(Point point) {
        if (point == null) {
            return false;
        }
        return false;
    }

    @Override
    public boolean addOrigin(Origin origin) {
        if (origin == null) {
            return false;
        }
        return false;
    }

    @Override
    public boolean removePoint(Point point) {
        if (point == null) {
            return false;
        }
        return false;
    }

    @Override
    public boolean removeOrigin(Origin origin) {
        if (origin == null) {
            return false;
        }
        return false;
    }
}
