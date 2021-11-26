package com.github.ferum_bot.api.internal.visitor;

import com.github.ferum_bot.api.models.Coordinatable;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Modified graph visitor. Adds some control to graph visiting.
 * @author matvejpopov
 * @version 1.0.0
 * @see DAGSimpleVisitor
 * @see Coordinatable
 */
public interface DAGVisitor extends DAGSimpleVisitor {

    /**
     * Performs graph visiting.
     * @param needToVisitSubGraph predicate that invokes before visit subgraph of current node.
     * @param onNodeAction action that invokes on current node.
     * @param afterSubGraphVisited action that invokes after subgraph of current node was visited.
     * @see Coordinatable
     * @see Predicate
     * @see Consumer
     */
    void visit(
        Predicate<Coordinatable> needToVisitSubGraph,
        Consumer<Coordinatable> onNodeAction,
        Consumer<Coordinatable> afterSubGraphVisited
    );
}
