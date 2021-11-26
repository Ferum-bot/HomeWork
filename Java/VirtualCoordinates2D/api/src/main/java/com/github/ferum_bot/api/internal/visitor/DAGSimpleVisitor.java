package com.github.ferum_bot.api.internal.visitor;

import com.github.ferum_bot.api.models.Coordinatable;
import com.github.ferum_bot.api.internal.event_engine.consumers.EventConsumer;
import com.github.ferum_bot.api.manager.ApiManager;
import com.github.ferum_bot.api.models.Origin;
import com.github.ferum_bot.api.models.Space;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Simple implementation of pattern Iterator.
 * Allows to visit all graph nodes.
 * @author matvejpopov
 * @version 1.0.0
 * @see Coordinatable
 * @see Space
 * @see Origin
 * @see EventConsumer
 * @see ApiManager
 */
public interface DAGSimpleVisitor {

    /**
     * Sets new graph on which iterate.
     * @param nodes graph by which to iterate.
     * @apiNote if this setter is used, root nodes assign to null, so visit will perform on this collection.
     * @see Coordinatable
     */
    void setNodes(Collection<Coordinatable> nodes);

    /**
     * Set new graph root on which iterate.
     * @param root the graph root.
     * @apiNote if this setter is used, origin root and nodes assign to null, so visit will start from this entity.
     * @see Space
     */
    void setRootNode(Space root);

    /**
     * Set new graph root on which iterate.
     * @param root the graph root.
     * @apiNote if this setter is used, space root and nodes assign to null, so visit start from this entity.
     * @see Origin
     */
    void setRootNode(Origin root);

    /**
     * Performs the graph visiting.
     * @param action lambda consumer that invokes on each graph node.
     * @see Coordinatable
     * @see Consumer
     */
    void visit(Consumer<Coordinatable> action);
}
