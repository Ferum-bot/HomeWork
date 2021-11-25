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

    private Map<String, EventConsumer> consumers = new HashMap<>();

    public static EventEngine getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventEngine();
        }

        return INSTANCE;
    }

    private EventEngine() {
        var onOriginAddName = OnOriginAdd.class.getName();
        var calculateBoundsName = CalculateBounds.class.getName();

        registerConsumerFor(new OnOriginAddConsumer(), onOriginAddName);
        registerConsumerFor(new CalculateBoundsConsumer(), calculateBoundsName);
    }

    public Effect onEventRaised(Event event) {
        var eventName = event.getClass().getName();
        var consumer = consumers.get(eventName);

        return consumer.handleEvent(event);
    }

    public void registerConsumerFor(EventConsumer consumer, String eventClassName) {
        consumers.put(eventClassName, consumer);
    }
}
