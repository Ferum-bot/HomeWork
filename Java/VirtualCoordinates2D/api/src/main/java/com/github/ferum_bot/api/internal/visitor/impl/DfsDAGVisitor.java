package com.github.ferum_bot.api.internal.visitor.impl;

import com.github.ferum_bot.api.internal.visitor.DAGVisitor;
import com.github.ferum_bot.api.models.Coordinatable;
import com.github.ferum_bot.api.models.Origin;
import com.github.ferum_bot.api.models.Point;
import com.github.ferum_bot.api.models.Space;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DfsDAGVisitor implements DAGVisitor {

    private Collection<Coordinatable> nodes;

    private Space spaceRoot;

    private Origin originRoot;

    public DfsDAGVisitor() {
        nodes = null;
        spaceRoot = null;
        originRoot = null;
    }

    public DfsDAGVisitor(Collection<Coordinatable> nodes) {
        this.nodes = nodes;
        spaceRoot = null;
        originRoot = null;
    }

    public DfsDAGVisitor(Space spaceRoot) {
        this.spaceRoot = spaceRoot;
        originRoot = null;
        nodes = null;
    }

    public DfsDAGVisitor(Origin originRoot) {
        this.originRoot = originRoot;
        spaceRoot = null;
        nodes = null;
    }

    @Override
    public void setNodes(Collection<Coordinatable> nodes) {
        this.nodes = nodes;
        spaceRoot = null;
        originRoot = null;
    }

    @Override
    public void setRootNode(Space root) {
        spaceRoot = root;
        originRoot = null;
        nodes = null;
    }

    @Override
    public void setRootNode(Origin root) {
        originRoot = root;
        spaceRoot = null;
        nodes = null;
    }

    @Override
    public void visit(Consumer<Coordinatable> action) {
        if (nodes != null) {
            iterate(nodes, action);
            return;
        }

        if (spaceRoot != null) {
            action.accept(spaceRoot);
            var children = spaceRoot.getChildren();
            iterate(children, action);
            return;
        }

        if (originRoot != null) {
            action.accept(originRoot);
            var children = originRoot.getChildren();
            iterate(children, action);
            return;
        }
    }

    @Override
    public void visit(
        Predicate<Coordinatable> needToVisitSubGraph,
        Consumer<Coordinatable> onNodeAction,
        Consumer<Coordinatable> afterSubGraphVisited
    ) {
        if (nodes != null) {
            iterate(nodes, needToVisitSubGraph, onNodeAction, afterSubGraphVisited);
            return;
        }

        if (spaceRoot != null) {
            iterate(spaceRoot, needToVisitSubGraph, onNodeAction, afterSubGraphVisited);
            return;
        }

        if (originRoot != null) {
            iterate(originRoot, needToVisitSubGraph, onNodeAction, afterSubGraphVisited);
            return;
        }
    }

    private void iterate(Collection<Coordinatable> collection, Consumer<Coordinatable> action) {
        collection.forEach(node -> {
            if (node instanceof Point point) {
                action.accept(point);
            }

            if (node instanceof Origin origin) {
                action.accept(origin);
                var children = origin.getChildren();
                iterate(children, action);
            }

            if (node instanceof Space space) {
                action.accept(space);
                var children = space.getChildren();
                iterate(children, action);
            }
        });
    }

    private void iterate(
        Collection<Coordinatable> collection,
        Predicate<Coordinatable> needToSubgraphVisit,
        Consumer<Coordinatable> onNodeAction,
        Consumer<Coordinatable> onSubgraphVisited
    ) {
        for (Coordinatable node: collection) {
            if (node instanceof Space space) {
                iterate(space, needToSubgraphVisit, onNodeAction, onSubgraphVisited);
                continue;
            }

            if (node instanceof Origin origin) {
                iterate(origin, needToSubgraphVisit, onNodeAction, onSubgraphVisited);
                continue;
            }

            if (node instanceof Point point) {
                iterate(point, needToSubgraphVisit, onNodeAction, onSubgraphVisited);
                continue;
            }
        }
    }

    private void iterate(
        Space space,
        Predicate<Coordinatable> needToSubgraphVisit,
        Consumer<Coordinatable> onNodeAction,
        Consumer<Coordinatable> onSubgraphVisited
    ) {
        if (!needToSubgraphVisit.test(space)) {
            return;
        }

        onNodeAction.accept(space);

        var children = space.getChildren();
        iterate(children, needToSubgraphVisit, onNodeAction, onSubgraphVisited);

        onSubgraphVisited.accept(space);
    }

    private void iterate(
        Origin origin,
        Predicate<Coordinatable> needToSubgraphVisit,
        Consumer<Coordinatable> onNodeAction,
        Consumer<Coordinatable> onSubgraphVisited
    ) {
        if (!needToSubgraphVisit.test(origin)) {
            return;
        }

        onNodeAction.accept(origin);

        var children = origin.getChildren();
        iterate(children, needToSubgraphVisit, onNodeAction, onSubgraphVisited);

        onSubgraphVisited.accept(origin);
    }

    private void iterate(
        Point point,
        Predicate<Coordinatable> needToSubgraphVisit,
        Consumer<Coordinatable> onNodeAction,
        Consumer<Coordinatable> onSubgraphVisited
    ) {
        if (!needToSubgraphVisit.test(point)) {
            return;
        }
        onNodeAction.accept(point);
        onSubgraphVisited.accept(point);
    }
}
