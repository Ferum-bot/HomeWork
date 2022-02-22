package com.ferumbot.mapper.impl.components.visitor;

import com.ferumbot.mapper.impl.core.models.GraphNode;

import java.util.function.Consumer;

public interface SimpleVisitor {

    void visit(Consumer<GraphNode> action);
}
