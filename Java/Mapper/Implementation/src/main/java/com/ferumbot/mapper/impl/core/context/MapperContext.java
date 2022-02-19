package com.ferumbot.mapper.impl.core.context;

import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.core.models.MappingSettings;

import java.util.Optional;

public interface MapperContext {

    Optional<MappingSettings> getSettings();

    void setSettings(MappingSettings settings);

    Optional<GraphNode> getObjectGraph();

    void setObjectGraph(GraphNode node);
}
