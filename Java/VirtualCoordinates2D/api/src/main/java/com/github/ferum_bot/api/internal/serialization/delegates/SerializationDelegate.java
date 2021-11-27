package com.github.ferum_bot.api.internal.serialization.delegates;

import com.github.ferum_bot.api.internal.serialization.model.SerializationAliases;
import com.github.ferum_bot.api.internal.visitor.DAGVisitor;
import com.github.ferum_bot.api.internal.visitor.impl.DfsDAGVisitor;
import com.github.ferum_bot.api.models.Coordinatable;
import com.github.ferum_bot.api.models.Origin;
import com.github.ferum_bot.api.models.Point;
import com.github.ferum_bot.api.models.Space;

public class SerializationDelegate {

    private final DAGVisitor visitor = new DfsDAGVisitor();

    public String serialize(Space space) {
        var serializedString = new StringBuilder();
        visitor.setRootNode(space);

        visitor.visit(
            coordinatable -> beforeSubGraphVisit(serializedString),
            coordinatable -> onEntityAction(coordinatable, serializedString),
            coordinatable -> afterSubGraphVisited(coordinatable, serializedString)
        );

        return serializedString.toString();
    }

    private boolean beforeSubGraphVisit(StringBuilder serializedString) {
        serializedString.append("{\n");
        return true;
    }

    private void onEntityAction(Coordinatable entity, StringBuilder serializedString) {
        var entityType = SerializationAliases.TYPE_ALIAS.getAlias() + ": " + entity.getEntityType() + "\n";
        var position = entity.getPosition();
        var x = position.x();
        var y = position.y();
        var coordinates = SerializationAliases.COORDINATES_ALIAS.getAlias() + ": " + "(" + x + ", " + y + ")\n";

        serializedString.append(entityType);
        serializedString.append(coordinates);

        if (!(entity instanceof Point)) {

            var children = SerializationAliases.CHILDREN_ALIAS.getAlias() + ":\n[\n";
            serializedString.append(children);
        }
    }

    private void afterSubGraphVisited(Coordinatable entity, StringBuilder serializedString) {
        if (entity instanceof Origin) {
            serializedString.append("]\n");
        }

        if (entity instanceof Space) {
            serializedString.append("]\n");
        }

        serializedString.append("}\n");
    }
}
