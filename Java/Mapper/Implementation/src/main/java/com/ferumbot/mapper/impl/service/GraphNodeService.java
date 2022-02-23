package com.ferumbot.mapper.impl.service;

import com.ferumbot.mapper.impl.core.models.GraphNode;
import ru.hse.homework4.enums.NullHandling;
import ru.hse.homework4.enums.UnknownPropertiesPolicy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

public interface GraphNodeService {

    NullHandling getNullHandlingPolicy(GraphNode node);

    UnknownPropertiesPolicy getUnknownPropertiesPolice(GraphNode node);

    Collection<Field> getFields(GraphNode node);

    Collection<Annotation> getAnnotations(GraphNode node);

    Collection<Constructor<?>> getConstructors(GraphNode node);

    Optional<String> getNameAlias(GraphNode node);

    Optional<String> getDateFormatValue(GraphNode node);
}
