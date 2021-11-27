package com.github.ferum_bot.api.internal.serialization.delegates;

import com.github.ferum_bot.api.exception.DAGSerializationException;
import com.github.ferum_bot.api.models.Space;
import jdk.jshell.spi.ExecutionControl;

public class DeserializationDelegate {

    public Space deserialize(String string) {
        Space space;
        try {
            space = tryToDeserialize(string);
        } catch (Exception ex) {
            throw new DAGSerializationException(ex.getMessage());
        }
        return space;
    }

    private Space tryToDeserialize(String string) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented yet");
    }
}
