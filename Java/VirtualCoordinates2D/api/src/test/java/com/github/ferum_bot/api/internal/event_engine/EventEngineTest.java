package com.github.ferum_bot.api.internal.event_engine;

import com.github.ferum_bot.api.internal.event_engine.consumers.EventConsumer;
import com.github.ferum_bot.api.internal.event_engine.models.effect.Effect;
import com.github.ferum_bot.api.internal.event_engine.models.effect.impl.CalculatedBounds;
import com.github.ferum_bot.api.internal.event_engine.models.effect.impl.Empty;
import com.github.ferum_bot.api.internal.event_engine.models.event.Event;
import com.github.ferum_bot.api.internal.event_engine.models.event.impl.CalculateBounds;
import com.github.ferum_bot.api.internal.event_engine.models.event.impl.OnOriginAdd;
import com.github.ferum_bot.api.manager.ApiManager;
import com.github.ferum_bot.api.models.Coord2D;
import com.github.ferum_bot.api.models.Coordinatable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventEngineTest {

    @Test
    public void GetInstance_Empty_Success() {
        var instance = EventEngine.getInstance();

        assertEquals(instance, EventEngine.getInstance());
    }

    @Test
    public void OnEventRaised_CalculateBoundsEvent_BoundsCalculatedEffect() {
        var origin = ApiManager.originOf(new Coord2D(0, 0));
        var point = ApiManager.pointOf(new Coord2D(1, 1));
        origin.addPoint(point);

        Event event = new CalculateBounds(origin);
        var engine = EventEngine.getInstance();
        Effect actualResult = engine.onEventRaised(event);

        assertInstanceOf(CalculatedBounds.class, actualResult);
    }

    @Test
    public void OnEventRaised_OnOriginAdd_EmptyEffect() {
        var origin = ApiManager.originOf(new Coord2D(0, 0));
        var point = ApiManager.pointOf(new Coord2D(1, 1));
        var newOrigin = ApiManager.originOf(new Coord2D(23, 3));
        origin.addPoint(point);

        Event event = new OnOriginAdd(origin, newOrigin);
        var engine = EventEngine.getInstance();
        Effect actualResult = engine.onEventRaised(event);

        assertInstanceOf(Empty.class, actualResult);
    }

    @Test
    public void RegisterConsumerFor_TestConsumerForTestEvent_EventHandled() {
        var origin = ApiManager.originOf(new Coord2D(12, 12));
        var point = ApiManager.pointOf(new Coord2D(4, 4));
        origin.addPoint(point);

        Event event = new TestEvent(origin);
        var engine = EventEngine.getInstance();
        engine.registerConsumerFor(new TestConsumer(), TestEvent.class);
        var actualResult = engine.onEventRaised(event);

        assertInstanceOf(TestEffect.class, actualResult);
    }

    private static class TestEvent implements Event {

        private final Coordinatable entity;

        TestEvent(Coordinatable entity) {
            this.entity = entity;
        }

        @Override
        public String getEventName() {
            return "TestEvent";
        }

        @Override
        public Coordinatable getCausedEntity() {
            return entity;
        }
    }

    private static class TestEffect implements Effect {

        @Override
        public String getEffectName() {
            return "TestEffect";
        }
    }

    private static class TestConsumer implements EventConsumer {

        @Override
        public Effect handleEvent(Event event) {
            var testEvent = (TestEvent) event;
            return new TestEffect();
        }
    }
}