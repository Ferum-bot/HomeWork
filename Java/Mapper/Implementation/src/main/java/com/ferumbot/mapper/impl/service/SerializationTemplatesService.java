package com.ferumbot.mapper.impl.service;

import com.ferumbot.mapper.impl.processor.SerializationProcessor;

/**
 * Used to substitute values in templates.
 * @see SerializationProcessor
 * @author matvejpopov
 * @version 1.0.0
 */
public interface SerializationTemplatesService {

    /**
     * Substitute the name alias of object in template.
     * @param nameAlias the alias of object.
     * @return name alias template.
     */
    String getNameAliasTemplate(String nameAlias);

    /**
     * Substitute the id value of object in template.
     * @param id the id of object.
     * @return id property template.
     */
    String getIdPropertyTemplate(Long id);
}
