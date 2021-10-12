package battleship.game.delegates.impl;

import battleship.game.GameState;
import battleship.game.delegates.GameStateControllerDelegate;
import battleship.game.settings.HardwareSettings;
import battleship.models.commands.impl.*;
import battleship.models.field.GameField;

public class ConfiguringGameController implements GameStateControllerDelegate {

    @Override
    public GameState handleState(HardwareSettings hardwareSettings, GameState state, GameField field) {
        hardwareSettings.outputProvider().showGameSettingsHint();

        return launchConfiguringProcess(hardwareSettings, state, field);
    }

    private GameState launchConfiguringProcess(HardwareSettings hardwareSettings, GameState state, GameField field) {
        var currentState = GameState.CONFIGURING;
        while (currentState == GameState.CONFIGURING) {
            var command = hardwareSettings.inputHandler().awaitUserInput();

            if (command instanceof Exit) {
                currentState = GameState.EXIT;
                continue;
            }
            if (command instanceof Undefined) {
                hardwareSettings.outputProvider().undefinedCommand();
                continue;
            }
            if (command instanceof HowToPlay) {
                hardwareSettings.outputProvider().showHowToPlay();
                hardwareSettings.outputProvider().showGameSettingsHint();
                continue;
            }
            if (command instanceof HitCoordinate) {
                hardwareSettings.outputProvider().undefinedCommand();
                hardwareSettings.outputProvider().showGameSettingsHint();
                continue;
            }
            if (command instanceof SettingsInput settingsInput) {
                var gameSettings = settingsInput.toGameSettings();
                field.applySettings(gameSettings);
                currentState = GameState.PLAYING;
                continue;
            }
        }

        return currentState;
    }
}
