package com.github.ferum_bot.api.internal.event_engine.models.event;

import com.github.ferum_bot.api.models.Coordinatable;

public interface Event {

    String getEventName();

    Coordinatable getCausedEntity();
}
