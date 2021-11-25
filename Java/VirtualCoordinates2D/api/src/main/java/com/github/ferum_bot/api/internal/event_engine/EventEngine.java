package com.github.ferum_bot.api.internal.event_engine;

import com.github.ferum_bot.api.internal.event_engine.consumers.EventConsumer;
import com.github.ferum_bot.api.internal.event_engine.consumers.impl.CalculateBoundsConsumer;
import com.github.ferum_bot.api.internal.event_engine.consumers.impl.OnOriginAddConsumer;
import com.github.ferum_bot.api.internal.event_engine.models.effect.Effect;
import com.github.ferum_bot.api.internal.event_engine.models.event.Event;
import com.github.ferum_bot.api.internal.event_engine.models.event.impl.CalculateBounds;
import com.github.ferum_bot.api.internal.event_engine.models.event.impl.OnOriginAdd;
import java.util.HashMap;
import java.util.Map;

/**
 * Api library event engine. Uses to handling different events.
 * @author matvejpopov
 * @version 1.0.0
 * @see Event
 * @see Effect
 * @see EventConsumer
 */
public class EventEngine {

    private static EventEngine INSTANCE = null;

    private final Map<Class<? extends Event>, EventConsumer> consumers = new HashMap<>();

    /**
     * EventEngine class accessor.
     * @return EventEngine instance.
     */
    public static EventEngine getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventEngine();
        }

        return INSTANCE;
    }

    private EventEngine() {
        var onOriginAddClass = OnOriginAdd.class;
        var calculateBoundsClass = CalculateBounds.class;

        registerConsumerFor(new OnOriginAddConsumer(), onOriginAddClass);
        registerConsumerFor(new CalculateBoundsConsumer(), calculateBoundsClass);
    }

    /**
     * Main engine method. Uses for handling events.
     * @param event event to handle.
     * @return result of handled event.
     * @see Event
     * @see Effect
     * @see EventConsumer
     */
    public Effect onEventRaised(Event event) {
        var eventClass = event.getClass();
        var consumer = consumers.get(eventClass);

        return consumer.handleEvent(event);
    }

    /**
     * Allows adding custom consumers for events.
     * @param consumer event consumer for current event.
     * @param eventClass which event consumer will handle.
     * @see EventConsumer
     * @see Event
     */
    public void registerConsumerFor(EventConsumer consumer, Class<? extends Event> eventClass) {
        consumers.put(eventClass, consumer);
    }
}
