package battleship.game.delegates.impl;

import battleship.game.GameState;
import battleship.game.delegates.GameStateControllerDelegate;
import battleship.game.settings.GameSettings;
import battleship.game.settings.HardwareSettings;
import battleship.models.field.GameField;
import battleship.models.info.InformationHolder;

public class FinishedGameController implements GameStateControllerDelegate {

    @Override
    public GameState handleState(
        HardwareSettings hardwareSettings, GameSettings gameSettings,
        GameState state, GameField field, InformationHolder informationHolder
    ) {
        var statistics = informationHolder.toStatistics();
        hardwareSettings.outputProvider().onGameWined(statistics);
        return GameState.EXIT;
    }
}
