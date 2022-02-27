package com.ferumbot.mapper.impl.components.visitor;

import com.ferumbot.mapper.impl.core.models.GraphNode;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Used to visit graph node with extra management opportunities.
 * @see GraphNode
 * @author matvejpopov
 * @version 1.0.0
 */
public interface ManagedVisitor extends SimpleVisitor {

    /**
     * Starts managed bypass of object graph.
     * @param beforeVisit the action to invoke before visit graph node.
     * @param onNodeVisit the action to invoke on node visit.
     * @param afterVisit the action to invoke after node visit.
     */
    void visit(
        Predicate<GraphNode> beforeVisit,
        Consumer<GraphNode> onNodeVisit,
        Consumer<GraphNode> afterVisit
    );
}
