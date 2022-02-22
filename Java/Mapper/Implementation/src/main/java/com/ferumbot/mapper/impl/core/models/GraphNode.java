package com.ferumbot.mapper.impl.core.models;

import com.ferumbot.mapper.impl.core.enums.ObjectType;

import java.lang.annotation.Annotation;
import java.util.Collection;

public record GraphNode(

    long id,

    Class<?> objectClass,

    ObjectType type,

    Collection<Annotation> annotations,

    Collection<GraphNode> children
) {
}
