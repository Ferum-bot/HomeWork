package battleship.game.delegates;

import battleship.game.GameState;
import battleship.game.settings.HardwareSettings;
import battleship.models.field.GameField;

public interface GameStateControllerDelegate {

    GameState handleState( HardwareSettings hardwareSettings, GameState state, GameField field);
}
