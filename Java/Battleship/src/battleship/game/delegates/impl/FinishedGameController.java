package battleship.game.delegates.impl;

import battleship.game.GameState;
import battleship.game.delegates.GameStateControllerDelegate;
import battleship.game.settings.HardwareSettings;
import battleship.models.field.GameField;

public class FinishedGameController implements GameStateControllerDelegate {

    @Override
    public GameState handleState(HardwareSettings hardwareSettings, GameState state, GameField field) {
        return null;
    }
}
