package com.github.ferum_bot.api.internal.event_engine.models.event.impl;

import com.github.ferum_bot.api.internal.event_engine.models.event.Event;
import com.github.ferum_bot.api.models.Coordinatable;
import com.github.ferum_bot.api.models.Origin;

public class OnOriginAdd implements Event {

    private static final String NAME = "OnOriginAddEvent";

    private final Origin origin;

    private final Coordinatable causedEntity;

    public OnOriginAdd(Coordinatable causedEntity, Origin originToAdd) {
        origin = originToAdd;
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

    public Origin getOrigin() {
        return origin;
    }
}
