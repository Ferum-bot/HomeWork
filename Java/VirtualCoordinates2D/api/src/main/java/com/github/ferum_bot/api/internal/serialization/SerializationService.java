package com.github.ferum_bot.api.internal.serialization;

import com.github.ferum_bot.api.models.Space;
import com.github.ferum_bot.api.util.DAGUtils;
import com.github.ferum_bot.api.exception.DAGSerializationException;

/**
 * Service that allows to serialize/deserialize api graph.
 * @author matvejpopov
 * @version 1.0.0
 * @see Space
 * @see DAGUtils
 */
public interface SerializationService {

    /**
     * Serialize DAG.
     * @param space entity to serialize from.
     * @return serialized DAG string.
     * @see Space
     */
    String serializeDAG(Space space);

    /**
     * Deserialize DAG.
     * @param string object to deserialize from.
     * @return deserialized DAG space entity.
     * @throws DAGSerializationException if input string contains invalid DAG.
     * @see Space
     */
    Space deSerializeDAG(String string);
}
