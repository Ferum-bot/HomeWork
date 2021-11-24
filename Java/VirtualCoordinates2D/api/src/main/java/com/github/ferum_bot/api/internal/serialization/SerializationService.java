package com.github.ferum_bot.api.internal.serialization;

import com.github.ferum_bot.api.models.Space;

public interface SerializationService {

    String serializeDAG(Space space);

    Space deSerializeDAG(String string);
}
