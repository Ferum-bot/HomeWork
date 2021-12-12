package com.github.ferum_bot.twentyone.core.guard;

import com.github.ferum_bot.twentyone.controllers.GameController;

/**
 * Check value to correctness.
 * @author matvejpopov
 * @version 1.0.0
 * @param <T> type of value to obtain.
 * @see GameController
 * @see GuardResult
 */
public interface Guard <T> {

    /**
     * Process the value.
     * @param value value to process.
     * @return the result of processing.
     * @see GuardResult
     */
    GuardResult obtainValue(T value);
}
