package battleship.game.delegates;

import battleship.game.GameState;
import battleship.game.settings.GameSettings;
import battleship.game.settings.HardwareSettings;
import battleship.models.field.GameField;
import battleship.models.info.InformationHolder;

public interface GameStateControllerDelegate {

    GameState handleState(
        HardwareSettings hardwareSettings, GameSettings gameSettings,
        GameState state, GameField field, InformationHolder informationHolder
    );
}
