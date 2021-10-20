package battleship.game.delegates;

import battleship.game.GameState;
import battleship.game.settings.GameSettings;
import battleship.game.settings.HardwareSettings;
import battleship.models.field.GameField;
import battleship.models.info.InformationHolder;

/**
 * Delegates controller logic to specific state controller.
 * @author matvejpopov
 * @version 1.0.0
 */
public interface GameStateControllerDelegate {

    /**
     * Delegates handling of current state.
     * @param hardwareSettings provides access to hardware.
     * @param gameSettings provides access to game settings.
     * @param state current game state.
     * @param field game field.
     * @param informationHolder current game data.
     * @return new game state.
     * @see GameState
     */
    GameState handleState(
        HardwareSettings hardwareSettings, GameSettings gameSettings,
        GameState state, GameField field, InformationHolder informationHolder
    );
}
