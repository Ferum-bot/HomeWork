package com.github.ferum_bot.api.internal.event_engine.models.effect.impl;

import com.github.ferum_bot.api.internal.event_engine.models.effect.Effect;

public class Empty implements Effect {

    private static final String NAME = "EmptyEffect";

    @Override
    public String getEffectName() {
        return NAME;
    }
}
