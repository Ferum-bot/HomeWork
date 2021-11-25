package com.github.ferum_bot.api.internal.event_engine.models.event.impl;

import com.github.ferum_bot.api.internal.event_engine.models.event.Event;
import com.github.ferum_bot.api.models.Coordinatable;

public class CalculateBounds implements Event {

    private static final String NAME = "CalculateBoundsEvent";

    private final Coordinatable causedEntity;

    public CalculateBounds(Coordinatable causedEntity) {
        this.causedEntity = causedEntity;
    }

    @Override
    public String getEventName() {
        return NAME;
    }

    @Override
    public Coordinatable getCausedEntity() {
        return causedEntity;
    }
}
