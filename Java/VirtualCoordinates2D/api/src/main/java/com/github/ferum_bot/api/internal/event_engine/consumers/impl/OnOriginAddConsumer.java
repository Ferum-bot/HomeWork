package com.github.ferum_bot.api.internal.event_engine.consumers.impl;

import com.github.ferum_bot.api.exception.DAGConstraintException;
import com.github.ferum_bot.api.internal.event_engine.consumers.EventConsumer;
import com.github.ferum_bot.api.internal.event_engine.models.effect.Effect;
import com.github.ferum_bot.api.internal.event_engine.models.effect.impl.Empty;
import com.github.ferum_bot.api.internal.event_engine.models.event.Event;
import com.github.ferum_bot.api.internal.event_engine.models.event.impl.OnOriginAdd;
import com.github.ferum_bot.api.internal.visitor.DAGSimpleVisitor;
import com.github.ferum_bot.api.internal.visitor.impl.DfsDAGVisitor;
import com.github.ferum_bot.api.models.Coordinatable;
import com.github.ferum_bot.api.models.Origin;
import com.github.ferum_bot.api.models.Space;

import java.util.HashSet;
import java.util.Set;

public class OnOriginAddConsumer implements EventConsumer {

    private final Set<Coordinatable> visitedEntities = new HashSet<>();

    private DAGSimpleVisitor visitor;

    @Override
    public Effect handleEvent(Event event) {
        var inComeEvent = (OnOriginAdd) event;
        visitedEntities.clear();
        checkCyclicityOrThrow(inComeEvent);

        return new Empty();
    }

    private void checkCyclicityOrThrow(OnOriginAdd event) {
        var eventOrigin = event.getOrigin();
        visitor = new DfsDAGVisitor(eventOrigin);
        visitor.visit(visitedEntities::add);

        var causedEntity = event.getCausedEntity();
        switch (causedEntity.getEntityType()) {
            case SPACE -> {
                var space = (Space) causedEntity;
                var children = space.getChildren();
                checkChildrenNotContainsOrThrow(children);
            }
            case ORIGIN -> {
                var origin = (Origin) causedEntity;
                var children = origin.getChildren();
                checkChildrenNotContainsOrThrow(children);
            }
        }
    }

    private void checkChildrenNotContainsOrThrow(Set<Coordinatable> children) {
        visitor.setNodes(children);

        visitor.visit(entity -> {

            if (entity instanceof Space space) {
                var visitedSpace = visitedEntities.stream()
                        .filter(coordinatable -> coordinatable == space).findFirst();
                visitedSpace.ifPresent(this::throwConstraintException);
            }

            if (entity instanceof Origin origin) {
                var visitedOrigin = visitedEntities.stream()
                        .filter(coordinatable -> coordinatable == origin).findFirst();
                visitedOrigin.ifPresent(this::throwConstraintException);
            }
        });
    }

    private void throwConstraintException(Coordinatable entity) {
        throw new DAGConstraintException("Entity " + entity.toString() + "already presents in graph");
    }
}
