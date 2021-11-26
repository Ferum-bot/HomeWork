package com.github.ferum_bot.api.internal.serialization.delegates;

import com.github.ferum_bot.api.internal.visitor.DAGVisitor;
import com.github.ferum_bot.api.models.Coordinatable;
import com.github.ferum_bot.api.models.Space;

public class SerializationDelegate {

    private DAGVisitor visitor;

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
        serializedString.append("{");
        return true;
    }

    private void onEntityAction(Coordinatable entity, StringBuilder serializedString) {

    }

    private void afterSubGraphVisited(Coordinatable entity, StringBuilder serializedString) {

    }
}
