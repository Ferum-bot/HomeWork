package com.ferumbot.mapper.impl.components.filter.object.impl;

import com.ferumbot.mapper.impl.components.filter.object.ObjectMapperFilter;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;

public class RetainCycleFilter implements ObjectMapperFilter {

    private final ObjectGraphBuildService graphBuildService;

    public RetainCycleFilter(ObjectGraphBuildService graphBuildService) {
        this.graphBuildService = graphBuildService;
    }

    @Override
    public void filter(Object object) {
        graphBuildService.getFromContextOrBuild(object);
    }

    @Override
    public void filter(Class<?> clazz) {  }
}
