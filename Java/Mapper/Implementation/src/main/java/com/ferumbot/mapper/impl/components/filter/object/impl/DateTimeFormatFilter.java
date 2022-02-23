package com.ferumbot.mapper.impl.components.filter.object.impl;

import com.ferumbot.mapper.impl.components.filter.object.ObjectMapperFilter;
import com.ferumbot.mapper.impl.core.context.MapperContextHolder;
import com.ferumbot.mapper.impl.core.enums.ObjectType;
import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.di.Injector;
import com.ferumbot.mapper.impl.service.GraphNodeService;
import com.ferumbot.mapper.impl.service.ObjectGraphBuildService;
import ru.hse.homework4.DateFormat;
import ru.hse.homework4.exceptions.InvalidDateFormatException;

import java.lang.annotation.Annotation;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import static com.ferumbot.mapper.impl.core.enums.ObjectType.*;

public class DateTimeFormatFilter implements ObjectMapperFilter {

    private final ObjectGraphBuildService graphBuildService;

    private final GraphNodeService graphNodeService;

    public DateTimeFormatFilter(
        ObjectGraphBuildService graphBuildService,
        GraphNodeService graphNodeService
    ) {
        this.graphBuildService = graphBuildService;
        this.graphNodeService = graphNodeService;
    }

    @Override
    public void filter(Class<?> objectClass) {
        var graph = graphBuildService.getFromContextOrBuild(objectClass);
        checkDateTimeFormat(graph);
    }

    private void checkDateTimeFormat(GraphNode node) {
        var visitor = Injector.provideSimpleVisitor(node);

        visitor.visit(currentNode -> {
            var type = currentNode.type();
            if (type == LOCAL_DATE_TIME || type == LOCAL_TIME || type == LOCAL_DATE) {
                checkFormat(currentNode);
            }
        });
    }

    private void checkFormat(GraphNode node) {
        try {
            var annotations = graphNodeService.getAnnotations(node);
            annotations.stream()
                    .filter(annotation -> annotation.annotationType().equals(DateFormat.class))
                    .findFirst()
                    .map(annotation -> (DateFormat) annotation)
                    .ifPresent(annotation -> DateTimeFormatter.ofPattern(annotation.value()));
        } catch (Exception exception) {
            var message = "Invalid DateTime format: " + exception.getMessage();
            throw new InvalidDateFormatException(message);
        }
    }
}
