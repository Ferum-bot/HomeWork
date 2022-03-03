package com.ferumbot.mapper.impl.components.filter.input;

import com.ferumbot.mapper.impl.components.filter.DeserializationFilterChain;
import com.ferumbot.mapper.impl.components.filter.SerializationFilterChain;
import com.ferumbot.mapper.impl.components.inputreader.InputReader;

/**
 * Filters mapper input.
 * @see InputReader
 * @see DeserializationFilterChain
 * @see SerializationFilterChain
 * @author matvejpopov
 * @version 1.0.0
 */
public interface InputMapperFilter {

    /**
     * Filters input.
     * @see InputReader
     * @param inputReader to get input from.
     */
    void filter(InputReader<?> inputReader);
}
