package com.ferumbot.mapper.impl.components.filter.object;

import com.ferumbot.mapper.impl.components.filter.SerializationFilterChain;
import com.ferumbot.mapper.impl.components.filter.DeserializationFilterChain;

/**
 * Filters mapper class or object.
 * @see SerializationFilterChain
 * @see DeserializationFilterChain
 * @author matvejpopov
 * @version 1.0.0
 */
public interface ObjectMapperFilter {

    /**
     * Filters object.
     * @param object object to filter.
     */
    void filter(Object object);

    /**
     * Filters class.
     * @param clazz class to filter.
     */
    void filter(Class<?> clazz);
}
