package com.ferumbot.mapper.impl.service;

import com.ferumbot.mapper.impl.core.models.GraphNode;
import com.ferumbot.mapper.impl.processor.SerializationProcessor;
import ru.hse.homework4.enums.NullHandling;
import ru.hse.homework4.enums.UnknownPropertiesPolicy;
import ru.hse.homework4.PropertyName;
import ru.hse.homework4.DateFormat;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

/**
 * Used to provide additional information of graph node.
 * @see GraphNode
 * @see SerializationProcessor
 * @author matvejpopov
 * @version 1.0.0
 */
public interface GraphNodeService {

    /**
     * Gets the null handling policy from node.
     * @see NullHandling
     * @param node the node to invoke from.
     * @return null handling policy.
     */
    NullHandling getNullHandlingPolicy(GraphNode node);

    /**
     * Gets the unknown property policy from node.
     * @see UnknownPropertiesPolicy
     * @param node the node to invoke from.
     * @return unknown properties policy.
     */
    UnknownPropertiesPolicy getUnknownPropertiesPolice(GraphNode node);

    /**
     * Gets fields of object from graph node.
     * @see Field
     * @param node the node to invoke from.
     * @return fields of object from graph node.
     */
    Collection<Field> getFields(GraphNode node);

    /**
     * Gets declared field annotations of object from graph node.
     * @see Annotation
     * @param node the node to invoke from.
     * @return field annotations of object.
     */
    Collection<Annotation> getFieldAnnotations(GraphNode node);

    /**
     * Gets declared class annotations of object from graph node.
     * @see Annotation
     * @param node the node to invoke from.
     * @return class annotations of object.
     */
    Collection<Annotation> getClassAnnotations(GraphNode node);

    /**
     * Gets public constructors of object from graph node.
     * @see Constructor
     * @param node the node to invoke from.
     * @return class constructors of object.
     */
    Collection<Constructor<?>> getConstructors(GraphNode node);

    /**
     * Gets name alias of object from graph node.
     * @see GraphNode
     * @see PropertyName
     * @param node the node to invoke from.
     * @return name alias value.
     * @apiNote returns empty optional if node is top level object of the object graph.
     */
    Optional<String> getNameAlias(GraphNode node);

    /**
     * Gets date/time format value from field ob object from graph node.
     * @see GraphNode
     * @see DateFormat
     * @param node the node to invoke from.
     * @return the date/time format value.
     * @apiNote returns empty optional if {@link DateFormat} is not presented.
     */
    Optional<String> getDateFormatValue(GraphNode node);
}
