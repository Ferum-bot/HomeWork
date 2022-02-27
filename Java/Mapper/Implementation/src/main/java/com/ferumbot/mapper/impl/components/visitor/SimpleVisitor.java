package com.ferumbot.mapper.impl.components.visitor;

import com.ferumbot.mapper.impl.core.models.GraphNode;

import java.util.function.Consumer;

/**
 * Used to visit object graph.
 * @see GraphNode
 * @author matvejpopov
 * @version 1.0.0
 */
public interface SimpleVisitor {

    /**
     * Starts bypass of object graph.
     * @param action the action to invoke on each graph node.
     */
    void visit(Consumer<GraphNode> action);
}
