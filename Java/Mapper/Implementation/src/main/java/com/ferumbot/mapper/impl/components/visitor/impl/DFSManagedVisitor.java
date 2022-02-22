package com.ferumbot.mapper.impl.components.visitor.impl;

import com.ferumbot.mapper.impl.components.visitor.ManagedVisitor;
import com.ferumbot.mapper.impl.core.models.GraphNode;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class DFSManagedVisitor implements ManagedVisitor {

    private final GraphNode rootNode;

    public DFSManagedVisitor(GraphNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public void visit(
        Predicate<GraphNode> beforeVisit, Consumer<GraphNode> onNodeVisit, Consumer<GraphNode> afterVisit
    ) {
        managedVisit(rootNode, beforeVisit, onNodeVisit, afterVisit);
    }

    @Override
    public void visit(Consumer<GraphNode> action) {
        simpleVisit(rootNode, action);
    }

    private void managedVisit(
        GraphNode node, Predicate<GraphNode> beforeVisit, Consumer<GraphNode> onNodeVisit, Consumer<GraphNode> afterVisit
    ) {
        var needVisit = beforeVisit.test(node);
        if (!needVisit) {
            return;
        }

        onNodeVisit.accept(node);

        node.children().forEach(childNode -> managedVisit(childNode, beforeVisit, onNodeVisit, afterVisit));

        afterVisit.accept(node);
    }

    private void simpleVisit(GraphNode node, Consumer<GraphNode> action) {
        action.accept(node);
        node.children().forEach(childNode -> simpleVisit(childNode, action));
    }
}
