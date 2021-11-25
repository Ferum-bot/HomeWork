package com.github.ferum_bot.api.internal.event_engine.models.effect.impl;

import com.github.ferum_bot.api.internal.event_engine.models.effect.Effect;
import com.github.ferum_bot.api.models.BoundBox;

public class CalculatedBounds implements Effect {

    private static final String NAME = "CalculatedBoundsEffect";

    private final BoundBox bounds;

    public CalculatedBounds(BoundBox bounds) {
        this.bounds = bounds;
    }

    @Override
    public String getEffectName() {
        return NAME;
    }

    public BoundBox getBounds() {
        return bounds;
    }
}
