package com.github.ferum_bot.api.models;

import java.io.Serializable;

import com.github.ferum_bot.api.enums.EntityType;
import com.github.ferum_bot.api.exception.NullCoordinatesException;

/**
 * Base api interface. Interface allowed store and
 * serialize entity state.
 * @author matvejpopov
 * @version 1.0.0
 * @see Serializable
 * @see Coord2D
 */
public interface Coordinatable extends Serializable {

    /**
     * Coordinates getter.
     * @see Coord2D
     * @return Position of entity.
     */
    Coord2D getPosition();

    /**
     * Coordinates setter.
     * @param position new entity position.
     * @see Coord2D
     * @throws NullCoordinatesException If position is null.
     */
    void setPosition(Coord2D position);

    /**
     * Getter for entity type.
     * @return the type of entity.
     * @see EntityType
     */
    EntityType getEntityType();
}
