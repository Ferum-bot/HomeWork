package com.ferumbot.mapper.impl.components.filter.object.impl;

import com.ferumbot.mapper.impl.components.filter.object.ObjectMapperFilter;
import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.di.Injector;
import com.ferumbot.mapper.impl.service.GraphNodeService;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;
import ru.hse.homework4.exceptions.UnSupportedClassException;

import java.lang.reflect.Constructor;

public class ClassConstructorFilter implements ObjectMapperFilter {

    private final ObjectGraphBuildService graphBuildService;

    private final GraphNodeService graphNodeService;

    public ClassConstructorFilter(
        ObjectGraphBuildService graphBuildService,
        GraphNodeService graphNodeService
    ) {
        this.graphBuildService = graphBuildService;
        this.graphNodeService = graphNodeService;
    }

    @Override
    public void filter(Object object) {
        var graph = graphBuildService.getFromContextOrBuild(object);
        checkConstructs(graph);
    }

    @Override
    public void filter(Class<?> clazz) {
        var graph = graphBuildService.getFromContextOrBuild(clazz);
        checkConstructs(graph);
    }

    private void checkConstructs(GraphNode rootNode) {
        var visitor = Injector.provideSimpleVisitor(rootNode);

        visitor.visit(node -> {
            var constructs = graphNodeService.getConstructors(node);
            var hasEmptyConstructor = constructs.stream().anyMatch(this::isEmptyConstructor);

            if (!hasEmptyConstructor) {
                var message = "This class not supported: " + node.objectClass().getName() +
                    ". Class doesn't have empty constructs!";
                throw new UnSupportedClassException(message);
            }
        });
    }

    private boolean isEmptyConstructor(Constructor<?> constructor) {
        return constructor.getParameterCount() == 0;
    }
}
