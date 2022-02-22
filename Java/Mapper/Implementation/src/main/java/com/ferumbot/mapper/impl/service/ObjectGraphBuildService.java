package com.ferumbot.mapper.impl.service;

import com.ferumbot.mapper.impl.core.models.GraphNode;

public interface ObjectGraphBuildService {

    GraphNode getFromContextOrBuild(Class<?> clazz);

    GraphNode getFromContextOrBuild(Object object);

    GraphNode buildGraphFrom(Class<?> clazz);

    GraphNode buildGraphFrom(Object object);
}
