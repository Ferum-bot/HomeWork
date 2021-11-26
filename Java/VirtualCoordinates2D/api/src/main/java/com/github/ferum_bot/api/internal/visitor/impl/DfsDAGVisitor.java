package com.github.ferum_bot.api.internal.visitor.impl;

import com.github.ferum_bot.api.internal.visitor.DAGSimpleVisitor;
import com.github.ferum_bot.api.models.Coordinatable;
import com.github.ferum_bot.api.models.Origin;
import com.github.ferum_bot.api.models.Point;
import com.github.ferum_bot.api.models.Space;
import java.util.Collection;
import java.util.function.Consumer;

public class DfsDAGVisitor implements DAGSimpleVisitor {

    private Collection<Coordinatable> nodes;

    private Space spaceRoot;

    private Origin originRoot;

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
    }

    @Override
    public void setRootNode(Space root) {
        spaceRoot = root;
    }

    @Override
    public void setRootNode(Origin root) {
        originRoot = root;
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
}
