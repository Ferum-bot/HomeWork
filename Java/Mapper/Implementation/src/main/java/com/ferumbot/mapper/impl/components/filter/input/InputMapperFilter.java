package com.ferumbot.mapper.impl.components.filter.input;

import com.ferumbot.mapper.impl.components.inputreader.InputReader;
import com.ferumbot.mapper.impl.components.filter.DeserializationFilterChain;
import com.ferumbot.mapper.impl.components.filter.SerializationFilterChain;

import java.io.File;
import java.io.InputStream;

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
     * @param input to get input from.
     */
    void filter(InputReader<?> input);
}
