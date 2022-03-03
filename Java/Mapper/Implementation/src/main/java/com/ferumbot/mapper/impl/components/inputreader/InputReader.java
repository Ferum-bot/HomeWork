package com.ferumbot.mapper.impl.components.inputreader;

import java.io.IOException;

/**
 * Abstraction for mapper input types.
 * @author matvejpopov
 * @version 1.0.0
 * @param <T> the reader type.
 */
public interface InputReader <T> {

    /**
     * Reads all data from input.
     * @return all data from input.
     * @throws IOException in case of input-output errors.
     */
    String readAll();

    /**
     * Gets the reader.
     * @return the reader.
     */
    T getReader();
}
