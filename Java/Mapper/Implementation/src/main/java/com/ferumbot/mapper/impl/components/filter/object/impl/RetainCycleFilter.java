package com.ferumbot.mapper.impl.components.filter.object.impl;

import com.ferumbot.mapper.impl.components.filter.object.ObjectMapperFilter;
import com.ferumbot.mapper.impl.core.context.MapperContextHolder;
import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;

public class RetainCycleFilter implements ObjectMapperFilter {

    private final ObjectGraphBuildService graphBuildService;

    public RetainCycleFilter(ObjectGraphBuildService graphBuildService) {
        this.graphBuildService = graphBuildService;
    }

    @Override
    public void filter(Class<?> objectClass) {
        var context = MapperContextHolder.getContext();
        var graph = context.getObjectGraph();
        if (graph.isEmpty()) {
            graphBuildService.buildGraphFrom(objectClass);
        }
    }
}
