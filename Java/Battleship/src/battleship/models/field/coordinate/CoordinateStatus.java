package battleship.models.field.coordinate;

/**
 * Enum for current cell status.
 * @author matvejpopov
 * @version 1.0.0
 */
public enum CoordinateStatus {
    NOT_SHOOT, EMPTY_SHOT,
    SHIP_SHOT, SHIP_SUNK;
}
