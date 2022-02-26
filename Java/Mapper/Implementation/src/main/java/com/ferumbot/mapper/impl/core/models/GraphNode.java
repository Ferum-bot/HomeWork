package com.ferumbot.mapper.impl.core.models;

import com.ferumbot.mapper.impl.core.enums.ObjectType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

public record GraphNode(

    Long id,

    Class<?> objectClass,

    Object object,

    Optional<Field> fieldInParent,

    Optional<Class<?>> parentClass,

    ObjectType type,

    Collection<GraphNode> children
) {
}
