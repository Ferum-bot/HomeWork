package com.github.ferum_bot.api.internal.event_engine.models.event;

import com.github.ferum_bot.api.models.Coordinatable;
import com.github.ferum_bot.api.internal.event_engine.EventEngine;

/**
 * Base event unit for event engine.
 * @author matvejpopov
 * @version 1.0.0
 * @see EventEngine
 * @see Coordinatable
 */
public interface Event {

    /**
     * Getter for event name.
     * @return event name.
     */
    String getEventName();

    /**
     * Getter for caused event entity.
     * @return caused event entity.
     * @see Coordinatable
     */
    Coordinatable getCausedEntity();
}
