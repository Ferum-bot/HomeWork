package com.github.ferum_bot.api.exception;

import com.github.ferum_bot.api.models.Space;
import com.github.ferum_bot.api.models.Origin;

/**
 * Represents violation of graph cyclicity.
 * @author matvejpopov
 * @version 1.0.0
 * @see Space
 * @see Origin
 */
public class DAGConstraintException extends IllegalStateException {

    public DAGConstraintException() {
        super();
    }

    public DAGConstraintException(String message) {
        super(message);
    }
}
