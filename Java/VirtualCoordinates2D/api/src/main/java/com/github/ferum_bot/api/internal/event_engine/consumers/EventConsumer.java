package com.github.ferum_bot.api.internal.event_engine.consumers;

import com.github.ferum_bot.api.internal.event_engine.models.effect.Effect;
import com.github.ferum_bot.api.internal.event_engine.models.event.Event;
import com.github.ferum_bot.api.internal.event_engine.EventEngine;

/**
 * Base unit for handling engine events.
 * @author matvejpopov
 * @version 1.0.0
 * @see Event
 * @see Effect
 * @see EventEngine
 */
public interface EventConsumer {

    /**
     * Method for handling event.
     * @param event event to handle.
     * @return result of event handling.
     */
    Effect handleEvent(Event event);
}
