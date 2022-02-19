package com.ferumbot.mapper.impl.components.filter;

import java.util.Collection;
import java.util.Collections;

public class SerializationFilterChain {

    private Collection<ObjectMapperFilter> filterChain;

    public SerializationFilterChain() {
        filterChain = Collections.emptyList();
    }

    public void addFilter(ObjectMapperFilter filter) {
        filterChain.add(filter);
    }

    public void invokeFilters(Object object) {
        filterChain.forEach(filter -> {
            filter.filter(object);
        });
    }
}
