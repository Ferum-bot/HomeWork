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

public class EventEngine {

    private static EventEngine INSTANCE = null;

    private Map<Class<?>, EventConsumer> consumers = new HashMap<>();

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

    public Effect onEventRaised(Event event) {
        var eventClass = event.getClass();
        var consumer = consumers.get(eventClass);

        return consumer.handleEvent(event);
    }

    public void registerConsumerFor(EventConsumer consumer, Class<?> eventClass) {
        consumers.put(eventClass, consumer);
    }
}
