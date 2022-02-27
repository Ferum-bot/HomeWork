package com.ferumbot.mapper.impl.components.filter;

import com.ferumbot.mapper.impl.components.filter.object.ObjectMapperFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SerializationFilterChain {

    private Collection<ObjectMapperFilter> filterChain;

    public SerializationFilterChain() {
        filterChain = new ArrayList<>();
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
