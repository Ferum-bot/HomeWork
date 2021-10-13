package battleship.game.generators;

import battleship.game.settings.GameSettings;
import battleship.models.field.GameField;
import battleship.models.ship.Ship;

import java.util.List;

public interface FieldGenerator {

    Boolean fieldExistsWith(GameSettings settings);

    List<Ship> generateShipsLocation(GameSettings settings);
}
