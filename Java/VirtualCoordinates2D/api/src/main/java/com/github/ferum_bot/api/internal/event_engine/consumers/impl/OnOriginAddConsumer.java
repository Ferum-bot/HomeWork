package com.github.ferum_bot.api.internal.event_engine.consumers.impl;

import com.github.ferum_bot.api.internal.event_engine.consumers.EventConsumer;
import com.github.ferum_bot.api.internal.event_engine.models.effect.Effect;
import com.github.ferum_bot.api.internal.event_engine.models.effect.impl.Empty;
import com.github.ferum_bot.api.internal.event_engine.models.event.Event;
import com.github.ferum_bot.api.internal.event_engine.models.event.impl.OnOriginAdd;
import com.github.ferum_bot.api.models.Coordinatable;

import java.util.HashSet;
import java.util.Set;

public class OnOriginAddConsumer implements EventConsumer {

    private final Set<Coordinatable> visitedEntities = new HashSet<>();

    @Override
    public Effect handleEvent(Event event) {
        var inComeEvent = (OnOriginAdd) event;
        visitedEntities.clear();
        checkCyclicityOrThrow(inComeEvent);

        return new Empty();
    }

    private void checkCyclicityOrThrow(OnOriginAdd event) {
        var origin = event.getOrigin();

    }
}
