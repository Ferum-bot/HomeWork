package com.ferumbot.mapper.impl.components.visitor;

import com.ferumbot.mapper.impl.core.models.GraphNode;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface ManagedVisitor extends SimpleVisitor {

    void visit(
        Predicate<GraphNode> beforeVisit,
        Consumer<GraphNode> onNodeVisit,
        Consumer<GraphNode> afterVisit
    );
}
