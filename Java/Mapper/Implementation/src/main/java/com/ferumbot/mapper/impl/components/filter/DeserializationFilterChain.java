package com.ferumbot.mapper.impl.components.filter;

import com.ferumbot.mapper.impl.components.filter.input.InputMapperFilter;
import com.ferumbot.mapper.impl.components.filter.object.ObjectMapperFilter;
import com.ferumbot.mapper.impl.components.inputreader.InputReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DeserializationFilterChain {

    private Collection<ObjectMapperFilter> classFilters;

    private Collection<InputMapperFilter> inputFilters;

    public DeserializationFilterChain() {
        classFilters = new ArrayList<>();
        inputFilters = new ArrayList<>();
    }

    public void addObjectFilter(ObjectMapperFilter filter) {
        classFilters.add(filter);
    }

    public void addInputFilter(InputMapperFilter filter) {
        inputFilters.add(filter);
    }

    public void filterClass(Class<?> clazz) {
        classFilters.forEach(filter -> {
            filter.filter(clazz);
        });
    }

    public void filterInput(InputReader<?> inputReader) {
        inputFilters.forEach(filter -> {
            filter.filter(inputReader);
        });
    }
}
