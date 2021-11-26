package com.github.ferum_bot.api.internal.serialization;

import com.github.ferum_bot.api.internal.serialization.delegates.DeserializationDelegate;
import com.github.ferum_bot.api.internal.serialization.delegates.SerializationDelegate;
import com.github.ferum_bot.api.models.Space;

public class CustomSerializationService implements SerializationService {

    private final SerializationDelegate serializationDelegate = new SerializationDelegate();
    private final DeserializationDelegate deserializationDelegate = new DeserializationDelegate();

    @Override
    public String serializeDAG(Space space) {
        return serializationDelegate.serialize(space);
    }

    @Override
    public Space deSerializeDAG(String string) {
        return deserializationDelegate.deserialize(string);
    }
}
