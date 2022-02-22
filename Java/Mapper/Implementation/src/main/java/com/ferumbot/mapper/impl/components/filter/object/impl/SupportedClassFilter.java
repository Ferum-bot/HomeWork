package com.ferumbot.mapper.impl.components.filter.object.impl;

import com.ferumbot.mapper.impl.components.filter.object.ObjectMapperFilter;
import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.di.Injector;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;
import ru.hse.homework4.exceptions.UnSupportedClassException;

import static com.ferumbot.mapper.impl.core.enums.ObjectType.UN_SUPPORTED;

public class SupportedClassFilter implements ObjectMapperFilter {

    private final ObjectGraphBuildService graphBuildService;

    public SupportedClassFilter(ObjectGraphBuildService graphBuildService) {
        this.graphBuildService = graphBuildService;
    }

    @Override
    public void filter(Class<?> objectClass) {
        var graph = graphBuildService.getFromContextOrBuild(objectClass);
        checkSupportedClasses(graph);
    }

    private void checkSupportedClasses(GraphNode rootNode) {
        var visitor = Injector.provideSimpleVisitor(rootNode);

        visitor.visit(node -> {
            if (node.type() == UN_SUPPORTED) {
                var message = "This class not supported: " + node.objectClass().getName();
                throw new UnSupportedClassException(message);
            }
        });
    }
}
