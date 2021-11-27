package com.github.ferum_bot.api.internal.event_engine.consumers.impl;

import com.github.ferum_bot.api.exception.DAGConstraintException;
import com.github.ferum_bot.api.internal.event_engine.EventEngine;
import com.github.ferum_bot.api.internal.event_engine.consumers.EventConsumer;
import com.github.ferum_bot.api.internal.event_engine.models.effect.Effect;
import com.github.ferum_bot.api.internal.event_engine.models.effect.impl.Empty;
import com.github.ferum_bot.api.internal.event_engine.models.event.Event;
import com.github.ferum_bot.api.internal.event_engine.models.event.impl.OnOriginAdd;
import com.github.ferum_bot.api.manager.ApiManager;
import com.github.ferum_bot.api.models.Coord2D;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnOriginAddConsumerTest {

    private OnOriginAddConsumer consumer;

    @BeforeEach
    public void beforeTest() {
        consumer = new OnOriginAddConsumer();
    }

    @AfterEach
    public void afterTest() {
        consumer = null;
    }

    @Test
    public void HandleEvent_ValidOrigin_EmptyResult() {
        var origin = ApiManager.originOf(new Coord2D(0, 0));
        var point = ApiManager.pointOf(new Coord2D(1, 1));
        var newOrigin = ApiManager.originOf(new Coord2D(12, 12));

        origin.addPoint(point);
        Event event = new OnOriginAdd(origin, newOrigin);
        Effect actualEffect = consumer.handleEvent(event);

        assertInstanceOf(Empty.class, actualEffect);
    }

    @Test
    public void HandleEvent_InValidOrigin_ThrowException() {
        var origin = ApiManager.originOf(new Coord2D(0, 0));
        var point = ApiManager.pointOf(new Coord2D(1, 1));
        var newOrigin = ApiManager.originOf(new Coord2D(12, 12));

        origin.addPoint(point);
        origin.addOrigin(newOrigin);
        assertThrows(DAGConstraintException.class, (() -> {
            Event event = new OnOriginAdd(newOrigin, origin);
            consumer.handleEvent(event);
        }));
    }
}