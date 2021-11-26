package com.github.ferum_bot.api.internal.visitor;

import com.github.ferum_bot.api.models.Coordinatable;
import com.github.ferum_bot.api.internal.event_engine.consumers.EventConsumer;
import com.github.ferum_bot.api.manager.ApiManager;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * Simple implementation of pattern Iterator.
 * Allows to visit all graph nodes.
 * @author matvejpopov
 * @version 1.0.0
 * @see Coordinatable
 * @see EventConsumer
 * @see ApiManager
 */
public interface DAGVisitor {

    /**
     * Sets new graph on which iterate.
     * @param nodes graph by which to iterate.
     * @see Coordinatable
     */
    void setNodes(Collection<Coordinatable> nodes);

    /**
     * Performs the graph visiting.
     * @param action lambda consumer that invokes on each graph node.
     * @see Coordinatable
     */
    void visit(Consumer<Coordinatable> action);
}
