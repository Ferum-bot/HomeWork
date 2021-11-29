package com.github.ferum_bot.api.models;

import java.util.Set;
import com.github.ferum_bot.api.exception.DAGConstraintException;

/**
 * The origin of coordinates entity.
 * Contains the origin position.
 * @author matvejpopov
 * @version 1.0.0
 * @see Coordinatable
 * @see Point
 * @see BoundBox
 */
public interface Origin extends Coordinatable {

    /**
     * Getter for origin entities.
     * @return collection of internal entities.
     * @see Coordinatable
     * @apiNote It is important that the collection is immutable.
     */
    Set<Coordinatable> getChildren();

    /**
     * Getter for origin bounds. Contains bound of all children.
     * @return origin bounds.
     * @see BoundBox
     */
    BoundBox getBounds();

    /**
     * Adds point to origin children as a leaf.
     * @param point point to add.
     * @return true if point was added and false otherwise.
     * @see Point
     * @apiNote returns false if point is null or children already contains this point.
     */
    boolean addPoint(Point point);

    /**
     * Adds another origin to current origin children.
     * @param origin origin to add.
     * @return true if origin was added and false otherwise.
     * @see Point
     * @apiNote returns false if origin is null or children already contains this origin.
     * @throws DAGConstraintException if adding led to a violation of the acyclicity of the graph.
     * @implNote after DAGConstraintException was thrown, the graph remains in a valid state.
     */
    boolean addOrigin(Origin origin);

    /**
     * Removes point from current origin children.
     * @param point point to remove.
     * @return true if point was removed and false otherwise.
     * @see Point
     * @apiNote returns false if point is null or children don't contain this point.
     */
    boolean removePoint(Point point);

    /**
     * Removes origin from current origin children.
     * @param origin origin to remove.
     * @return true if origin was removed and false otherwise
     * @see Origin
     * @apiNote returns false if origin is null or children don't contain this origin.
     */
    boolean removeOrigin(Origin origin);
}
