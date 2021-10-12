package battleship.models.field;

import battleship.game.GameState;
import battleship.game.settings.GameSettings;
import battleship.models.ship.ShipCoordinate;

import java.util.List;

public class GameField {

    private List<List<ShipCoordinate>> field;

    public void applySettings(GameSettings settings) {

    }

    public List<List<ShipCoordinate>> getField() {
        return field;
    }
}
