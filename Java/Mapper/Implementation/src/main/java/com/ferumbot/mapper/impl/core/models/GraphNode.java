package com.ferumbot.mapper.impl.core.models;

import com.ferumbot.mapper.impl.core.enums.ObjectType;

import java.util.Collection;

public record GraphNode(

    long id,

    Object object,

    ObjectType type,

    String nameAlias,

    Collection<GraphNode> children
) {
}
