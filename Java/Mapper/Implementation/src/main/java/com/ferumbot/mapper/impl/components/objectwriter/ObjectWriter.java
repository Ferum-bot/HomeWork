package com.ferumbot.mapper.impl.components.objectwriter;

/**
 * Abstraction for mapper output types.
 * @author matvejpopov
 * @version 1.0.0
 * @param <T> the writer type
 */
public interface ObjectWriter <T> {

    /**
     * Writes string to end of output.
     * @param string the string to write.
     */
    void writeToEnd(String string);

    /**
     * Gets output type object.
     * @return the output.
     */
    T getWriter();
}
