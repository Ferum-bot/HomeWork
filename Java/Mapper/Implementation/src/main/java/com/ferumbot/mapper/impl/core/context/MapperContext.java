package com.ferumbot.mapper.impl.core.context;

import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.core.models.MappingSettings;
import com.ferumbot.mapper.impl.interactor.MapperInteractor;

import java.util.Optional;

/**
 * Holds the current general information of mapping process.
 * @see MapperContextHolder
 * @see MapperInteractor
 * @author matvejpopov
 * @version 1.0.0
 */
public interface MapperContext {

    /**
     * Provides current mapping processes settings.
     * @see MappingSettings
     * @return the current mapping settings.
     * @apiNote returns empty optional if settings don't present.
     */
    Optional<MappingSettings> getSettings();

    /**
     * Sets current mapping process settings.
     * @see MappingSettings
     * @param settings current mapping process settings.
     */
    void setSettings(MappingSettings settings);

    /**
     * Provides current mapping process object graph.
     * @see GraphNode
     * @return the current object graph.
     * @apiNote returns empty optional if object graph wasn't built.
     */
    Optional<GraphNode> getObjectGraph();

    /**
     * Sets current mapping process object graph.
     * @see GraphNode
     * @param node current mapping process root graph node.
     */
    void setObjectGraph(GraphNode node);

    /**
     * Clears current mapping process object graph.
     * @apiNote Do not invoke manual.
     */
    void clearObjectGraph();
}
