package com.ferumbot.mapper.impl.processor;

import com.ferumbot.mapper.impl.components.inputreader.InputReader;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;

@SuppressWarnings("ClassCanBeRecord")
public class DeserializationProcessor {

    private final ObjectGraphBuildService graphBuildService;

    public DeserializationProcessor(
        ObjectGraphBuildService graphBuildService
    ) {
        this.graphBuildService = graphBuildService;
    }

    public <T> T deserialize(Class<T> clazz, InputReader<?> inputReader) {
        var graph = graphBuildService.getFromContextOrBuild(clazz);

        return null;
    }
}
