package com.github.ferum_bot.api.internal.event_engine.models.effect;

import com.github.ferum_bot.api.internal.event_engine.models.event.Event;
import com.github.ferum_bot.api.internal.event_engine.EventEngine;

/**
 * Base unit for event result.
 * @author matvejpopov
 * @version 1.0.0
 * @see Event
 * @see EventEngine
 */
public interface Effect {

    /**
     * Getter for effect name.
     * @return effect name.
     */
    String getEffectName();
}
