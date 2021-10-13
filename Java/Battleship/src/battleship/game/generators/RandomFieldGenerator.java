package battleship.game.generators;

import battleship.game.settings.GameSettings;
import battleship.models.ship.Ship;

import java.util.List;

public class RandomFieldGenerator implements FieldGenerator {

    @Override
    public Boolean fieldExistsWith(GameSettings settings) {
        return null;
    }

    @Override
    public List<Ship> generateShipsLocation(GameSettings settings) {
        return null;
    }
}
