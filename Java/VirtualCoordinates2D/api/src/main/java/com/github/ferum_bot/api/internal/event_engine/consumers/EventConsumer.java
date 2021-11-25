package com.github.ferum_bot.api.internal.event_engine.consumers;

import com.github.ferum_bot.api.internal.event_engine.models.effect.Effect;
import com.github.ferum_bot.api.internal.event_engine.models.event.Event;

public interface EventConsumer {

    Effect handleEvent(Event event);
}
