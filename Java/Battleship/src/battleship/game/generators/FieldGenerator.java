package battleship.game.generators;

import battleship.game.settings.GameSettings;
import battleship.models.field.GameField;
import battleship.models.ship.Ship;

import java.util.List;

/**
 * Used to generate game field. Generates ships coordinates by game settings.
 * @author matvejpopov
 * @version 1.0.0
 */
public interface FieldGenerator {

    /**
     * Checks if field exists with current game settings.
     * @param settings: game settings
     * @return true if field exists and false otherwise.
     */
    Boolean fieldExistsWith(GameSettings settings);

    /**
     * Generates ships location on the field with current game settings.
     * @param settings: game settings.
     * @return Collection of generated ships.
     * @see Ship
     */
    List<Ship> generateShipsLocation(GameSettings settings);
}
