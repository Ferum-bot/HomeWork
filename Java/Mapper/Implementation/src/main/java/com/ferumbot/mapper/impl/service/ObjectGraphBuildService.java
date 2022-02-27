package com.ferumbot.mapper.impl.service;

import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.processor.SerializationProcessor;

/**
 * Builds graph from object or class.
 * @see GraphNode
 * @see SerializationProcessor
 * @author matvejpopov
 * @version 1.0.0
 */
public interface ObjectGraphBuildService {

    /**
     * Gets root node from context or build new graph.
     * @param clazz start class to build from.
     * @return root node of the graph.
     * @apiNote returns node without object, only class structure.
     */
    GraphNode getFromContextOrBuild(Class<?> clazz);

    /**
     * Gets root node from context or build new graph.
     * @param object start object to build from.
     * @return root node of the graph.
     */
    GraphNode getFromContextOrBuild(Object object);

    /**
     * Build new graph.
     * @param clazz start class to build from.
     * @return root node of the graph.
     * @apiNote returns node without object, only class strucutre.
     */
    GraphNode buildGraphFrom(Class<?> clazz);

    /**
     * Build new graph.
     * @param object start object to build from.
     * @return root node of the graph.
     */
    GraphNode buildGraphFrom(Object object);
}
