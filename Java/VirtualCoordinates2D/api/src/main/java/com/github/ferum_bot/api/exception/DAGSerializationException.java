package com.github.ferum_bot.api.exception;

import com.github.ferum_bot.api.util.DAGUtils;

/**
 * Represents DAG graph deserialization error.
 * @author matvejpopov
 * @version 1.0.0
 * @see DAGUtils
 */
public class DAGSerializationException extends IllegalStateException {

    public DAGSerializationException() {
        super();
    }

    public DAGSerializationException(String message) {
        super(message);
    }
}
