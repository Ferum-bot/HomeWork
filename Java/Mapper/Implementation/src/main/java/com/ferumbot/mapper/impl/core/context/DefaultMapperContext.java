package com.ferumbot.mapper.impl.core.context;

import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.core.models.MappingSettings;
import ru.hse.homework4.Exported;

import java.util.Optional;

class DefaultMapperContext implements MapperContext {

    private MappingSettings mappingSettings = null;

    private GraphNode graphNode = null;

    DefaultMapperContext() {  }

    @Override
    public Optional<MappingSettings> getSettings() {
        if (mappingSettings == null) {
            return Optional.empty();
        } else {
            return Optional.of(mappingSettings);
        }
    }

    @Override
    public void setSettings(MappingSettings settings) {
        mappingSettings = settings;
    }

    @Override
    public Optional<GraphNode> getObjectGraph() {
        if (graphNode == null) {
            return Optional.empty();
        } else {
            return Optional.of(graphNode);
        }
    }

    @Override
    public void setObjectGraph(GraphNode node) {
        graphNode = node;
    }

    @Override
    public void clearObjectGraph() {
        graphNode = null;
    }
}
