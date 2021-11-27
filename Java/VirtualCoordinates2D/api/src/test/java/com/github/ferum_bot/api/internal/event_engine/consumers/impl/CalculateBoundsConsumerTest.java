package com.github.ferum_bot.api.internal.event_engine.consumers.impl;

import com.github.ferum_bot.api.internal.event_engine.consumers.EventConsumer;
import com.github.ferum_bot.api.internal.event_engine.models.effect.Effect;
import com.github.ferum_bot.api.internal.event_engine.models.effect.impl.CalculatedBounds;
import com.github.ferum_bot.api.internal.event_engine.models.event.Event;
import com.github.ferum_bot.api.internal.event_engine.models.event.impl.CalculateBounds;
import com.github.ferum_bot.api.manager.ApiManager;
import com.github.ferum_bot.api.models.Coord2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateBoundsConsumerTest {

    private CalculateBoundsConsumer consumer;

    @BeforeEach
    public void beforeTest() {
        consumer = new CalculateBoundsConsumer();
    }

    @AfterEach
    public void afterTest() {
        consumer = null;
    }

    @Test
    public void HandleEvent_OriginBounds_TrueBounds() {
        var origin = ApiManager.originOf(new Coord2D(0, 15));
        var point = ApiManager.pointOf(new Coord2D(0, 0));
        origin.addPoint(point);

        Event event = new CalculateBounds(origin);
        Effect actualEffect = consumer.handleEvent(event);

        assertInstanceOf(CalculatedBounds.class, actualEffect);
    }
}