package com.ferumbot.mapper.impl.service.impl;

import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.service.GraphNodeService;
import ru.hse.homework4.enums.NullHandling;
import ru.hse.homework4.enums.UnknownPropertiesPolicy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

public class CommonGraphNodeService implements GraphNodeService {

    @Override
    public NullHandling getNullHandlingPolicy(GraphNode node) {
        return null;
    }

    @Override
    public UnknownPropertiesPolicy getUnknownPropertiesPolice(GraphNode node) {
        return null;
    }

    @Override
    public Collection<Field> getFields(GraphNode node) {
        return null;
    }

    @Override
    public Collection<Annotation> getAnnotations(GraphNode node) {
        return null;
    }

    @Override
    public Collection<Constructor<?>> getConstructors(GraphNode node) {
        return null;
    }

    @Override
    public Optional<String> getNameAlias(GraphNode node) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getDateFormatValue(GraphNode node) {
        return Optional.empty();
    }
}
