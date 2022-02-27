package com.ferumbot.mapper.impl.components.filter.object.impl;

import com.ferumbot.mapper.impl.components.filter.object.ObjectMapperFilter;
import com.ferumbot.mapper.impl.core.enums.ObjectType;
import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.di.Injector;
import com.ferumbot.mapper.impl.service.GraphNodeService;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;
import ru.hse.homework4.exceptions.UnSupportedClassException;

import java.lang.reflect.Constructor;

@SuppressWarnings("ClassCanBeRecord")
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

        visitor.visit(this::onEachNode);
    }

    private void onEachNode(GraphNode node) {
        if (node.type() != ObjectType.EXPORTED_CLASS) {
            return;
        }

        var constructs = graphNodeService.getConstructors(node);
        var hasEmptyConstructor = constructs.stream().anyMatch(this::isEmptyConstructor);

        if (!hasEmptyConstructor) {
            var message = "This class not supported: " + node.objectClass().getName() +
                    ". Class doesn't have empty constructs!";
            throw new UnSupportedClassException(message);
        }
    }

    private boolean isEmptyConstructor(Constructor<?> constructor) {
        return constructor.getParameterCount() == 0;
    }
}
