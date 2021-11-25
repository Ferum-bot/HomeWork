package com.github.ferum_bot.api.internal.event_engine.consumers.impl;

import com.github.ferum_bot.api.internal.event_engine.consumers.EventConsumer;
import com.github.ferum_bot.api.internal.event_engine.models.effect.Effect;
import com.github.ferum_bot.api.internal.event_engine.models.effect.impl.CalculatedBounds;
import com.github.ferum_bot.api.internal.event_engine.models.event.Event;
import com.github.ferum_bot.api.internal.event_engine.models.event.impl.CalculateBounds;
import com.github.ferum_bot.api.internal.util.MathUtil;
import com.github.ferum_bot.api.models.*;
import java.util.Set;

public class CalculateBoundsConsumer implements EventConsumer {

    @Override
    public Effect handleEvent(Event event) {
        var inComeEvent = (CalculateBounds) event;
        var entity = inComeEvent.getCausedEntity();
        var calculatedBounds = obtainEntityBounds(entity, BoundsOffSet.initial());

        return new CalculatedBounds(calculatedBounds);
    }

    private BoundBox obtainEntityBounds(Coordinatable entity, BoundsOffSet offSet) {
        var type = entity.getEntityType();
        switch (type) {
            case POINT -> {
                var point = (Point) entity;
                return obtainPointBounds(point, offSet);
            }
            case SPACE -> {
                var space = (Space) entity;
                return obtainSpaceBounds(space, offSet);
            }
            case ORIGIN -> {
                var origin = (Origin) entity;
                return obtainOriginBounds(origin, offSet);
            }
        };

        return null;
    }

    private BoundBox obtainPointBounds(Point point, BoundsOffSet offSet) {
        var xCoordinate = point.getPosition().x() + offSet.xOffSet();
        var yCoordinate = point.getPosition().y() + offSet.yOffSet();
        var coordinate = new Coord2D(xCoordinate, yCoordinate);
        return new BoundBox(coordinate, coordinate);
    }

    private BoundBox obtainOriginBounds(Origin origin, BoundsOffSet offSet) {
        var coordinates = origin.getPosition();
        var newOffSet = offSet.add(coordinates.x(), coordinates.y());
        var entities = origin.getChildren();
        return calculateBounds(entities, newOffSet);
    }

    private BoundBox obtainSpaceBounds(Space space, BoundsOffSet offSet) {
        var coordinates = space.getPosition();
        var newOffSet = offSet.add(coordinates.x(), coordinates.y());
        var entities = space.getChildren();
        return calculateBounds(entities, newOffSet);
    }

    private BoundBox calculateBounds(Set<Coordinatable> entities, BoundsOffSet offSet) {
        BoundBox currentBounds = null;

        for (Coordinatable entity: entities) {
            var bounds = obtainEntityBounds(entity, offSet);
            if (currentBounds == null) {
                currentBounds = bounds;
            } else {
                currentBounds = MathUtil.mergeBounds(currentBounds, bounds);
            }
        }

        return currentBounds;
    }

    private static record BoundsOffSet(
        double xOffSet,
        double yOffSet
    ) {

        private static BoundsOffSet initial() {
            return new BoundsOffSet(0.0, 0.0);
        }

        private BoundsOffSet add(double xOffSet, double yOffSet) {
            var newXOffSet = this.xOffSet + xOffSet;
            var newYOffSet = this.yOffSet + yOffSet;
            return new BoundsOffSet(newXOffSet, newYOffSet);
        }
    }
}
