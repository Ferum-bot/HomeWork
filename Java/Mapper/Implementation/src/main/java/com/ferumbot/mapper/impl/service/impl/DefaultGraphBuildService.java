package com.ferumbot.mapper.impl.service.impl;

import com.ferumbot.mapper.impl.core.context.MapperContextHolder;
import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;

public class DefaultGraphBuildService implements ObjectGraphBuildService {

    @Override
    public GraphNode getFromContextOrBuild(Class<?> clazz) {
        var context = MapperContextHolder.getContext();
        var graphHolder = context.getObjectGraph();

        if (graphHolder.isEmpty())  {
            var graph = buildGraphFrom(clazz);
            context.setObjectGraph(graph);
            return graph;
        } else {
            return graphHolder.get();
        }
    }

    @Override
    public GraphNode getFromContextOrBuild(Object object) {
        var context = MapperContextHolder.getContext();
        var graphHolder = context.getObjectGraph();

        if (graphHolder.isPresent()) {
            return graphHolder.get();
        } else {
            var graph = buildGraphFrom(object);
            context.setObjectGraph(graph);
            return graph;
        }
    }

    @Override
    public GraphNode buildGraphFrom(Class<?> clazz) {
        return null;
    }

    @Override
    public GraphNode buildGraphFrom(Object object) {
        return null;
    }
}
