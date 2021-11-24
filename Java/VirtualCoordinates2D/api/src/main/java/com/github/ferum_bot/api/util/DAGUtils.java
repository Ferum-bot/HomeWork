package com.github.ferum_bot.api.util;

import com.github.ferum_bot.api.internal.serialization.CustomSerializationService;
import com.github.ferum_bot.api.internal.serialization.SerializationService;
import com.github.ferum_bot.api.models.Space;
import com.github.ferum_bot.api.exception.DAGSerializationException;

/**
 * Api graph util class.
 * Used to serialization/deserialization features.
 * @author matvejpopov
 * @version 1.0.0
 */
public class DAGUtils {

    private static final SerializationService serializationService = new CustomSerializationService();

    /**
     * Used to serialize the DAG graph.
     * @param space DAG graph to serialize.
     * @return serialized DAG graph.
     * @see Space
     * @throws NullPointerException if space is null.
     */
    public static String exportAsString(Space space) {
        if (space == null) {
            throw new NullPointerException("Space can't be null!");
        }
        return serializationService.serializeDAG(space);
    }

    /**
     * Used to deserialize the DAG graph.
     * @param string from which deserialize DAG graph.
     * @return deserialized DAG graph
     * @see Space
     * @throws DAGSerializationException if string contains invalid DAG graph.
     * @throws NullPointerException if string is null
     */
    public static Space importFromString(String string) {
        if (string == null) {
            throw new NullPointerException("String to deserialize can't be null!");
        }
        return serializationService.deSerializeDAG(string);
    }
}
