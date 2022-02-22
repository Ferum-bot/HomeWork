package com.ferumbot.mapper.impl.processor;

import com.ferumbot.mapper.impl.components.objectwriter.ObjectWriter;
import com.ferumbot.mapper.impl.core.context.MapperContextHolder;
import com.ferumbot.mapper.impl.di.Injector;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;

public class SerializationProcessor {

    private final ObjectGraphBuildService graphBuildService;

    public SerializationProcessor(ObjectGraphBuildService graphBuildService) {
        this.graphBuildService = graphBuildService;
    }

    public void serialize(Object object, ObjectWriter<?> objectWriter) {
        var graph = graphBuildService.getFromContextOrBuild(object);
        var settings = MapperContextHolder.getContext().getSettings();
        var visitor = Injector.provideManagedVisitor(graph);


    }
}
