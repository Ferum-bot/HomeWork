package com.github.ferum_bot.api.internal.visitor;

import com.github.ferum_bot.api.models.Coordinatable;
import com.github.ferum_bot.api.models.Origin;
import com.github.ferum_bot.api.models.Point;
import com.github.ferum_bot.api.models.Space;
import java.util.Collection;
import java.util.function.Consumer;

public class DfsDAGVisitor implements DAGVisitor {

    private Collection<Coordinatable> nodes;

    public DfsDAGVisitor(Collection<Coordinatable> nodes) {
        this.nodes = nodes;
    }

    @Override
    public void setNodes(Collection<Coordinatable> nodes) {
        this.nodes = nodes;
    }

    @Override
    public void visit(Consumer<Coordinatable> action) {
        if (nodes == null) {
            return;
        }
        iterate(nodes, action);
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
