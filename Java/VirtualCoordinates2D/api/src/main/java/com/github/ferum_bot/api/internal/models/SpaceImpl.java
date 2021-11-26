package com.github.ferum_bot.api.internal.models;

import com.github.ferum_bot.api.enums.EntityType;
import com.github.ferum_bot.api.exception.NullCoordinatesException;
import com.github.ferum_bot.api.internal.event_engine.EventEngine;
import com.github.ferum_bot.api.internal.event_engine.models.effect.impl.CalculatedBounds;
import com.github.ferum_bot.api.internal.event_engine.models.event.impl.CalculateBounds;
import com.github.ferum_bot.api.internal.event_engine.models.event.impl.OnOriginAdd;
import com.github.ferum_bot.api.models.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SpaceImpl implements Space {

    private final Set<Coordinatable> children = new HashSet<>();

    private final EventEngine eventEngine = EventEngine.getInstance();

    private Coord2D coordinates;

    public SpaceImpl(Coord2D coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public Coord2D getPosition() {
        return coordinates;
    }

    @Override
    public void setPosition(Coord2D position) {
        if (position == null) {
            throw new NullCoordinatesException("Coordinates can't be null!");
        }
        coordinates = position;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.SPACE;
    }

    @Override
    public Set<Coordinatable> getChildren() {
        return Collections.unmodifiableSet(children);
    }

    @Override
    public BoundBox getBounds() {
        var event = new CalculateBounds(this);
        var result = (CalculatedBounds) eventEngine.onEventRaised(event);

        return result.getBounds();
    }

    @Override
    public boolean addPoint(Point point) {
        if (point == null) {
            return false;
        }
        return children.add(point);
    }

    @Override
    public boolean addOrigin(Origin origin) {
        if (origin == null) {
            return false;
        }

        var event = new OnOriginAdd(this, origin);
        eventEngine.onEventRaised(event);

        return children.add(origin);
    }

    @Override
    public boolean removePoint(Point point) {
        if (point == null) {
            return false;
        }
        return children.remove(point);
    }

    @Override
    public boolean removeOrigin(Origin origin) {
        if (origin == null) {
            return false;
        }
        return children.remove(origin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceImpl space = (SpaceImpl) o;
        return Objects.equals(coordinates, space.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    @Override
    public String toString() {
        return "SpaceImpl{" +
                "children=" + children +
                ", coordinates=" + coordinates +
                '}';
    }
}
