package com.ferumbot.mapper.impl.components.filter.object;

public interface ObjectMapperFilter {

    void filter(Object object);

    void filter(Class<?> clazz);
}
