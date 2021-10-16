package battleship.game.delegates.impl;

import battleship.game.GameState;
import battleship.game.delegates.GameStateControllerDelegate;
import battleship.game.generators.FieldGenerator;
import battleship.game.generators.RandomFieldGenerator;
import battleship.game.settings.GameSettings;
import battleship.game.settings.HardwareSettings;
import battleship.models.commands.impl.*;
import battleship.models.field.GameField;
import battleship.models.info.InformationHolder;
import battleship.models.ship.Ship;

import java.util.List;

public class ConfiguringGameController implements GameStateControllerDelegate {

    private final FieldGenerator fieldGenerator = new RandomFieldGenerator();

    private HardwareSettings hardwareSettings;
    private GameSettings gameSettings;

    private GameState state;

    private GameField field;

    private InformationHolder informationHolder;

    @Override
    public GameState handleState(
        HardwareSettings hardwareSettings, GameSettings gameSettings,
        GameState state, GameField field, InformationHolder informationHolder
    ) {
        hardwareSettings.outputProvider().showGameSettingsHint();

        this.hardwareSettings = hardwareSettings;
        this.gameSettings = gameSettings;
        this.state = state;
        this.field = field;
        this.informationHolder = informationHolder;

        return launchConfiguringProcess();
    }

    private GameState launchConfiguringProcess() {
        while (state == GameState.CONFIGURING) {
            var command = hardwareSettings.inputHandler().awaitUserInput();

            if (command instanceof Exit) {
                state = GameState.EXIT;
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
                hardwareSettings.outputProvider().incorrectCommand();
                hardwareSettings.outputProvider().showGameSettingsHint();
                continue;
            }
            if (command instanceof SettingsInput settingsInput) {
                handleGameSettingsInput(settingsInput);
                continue;
            }
        }

        return state;
    }

    private void handleGameSettingsInput(SettingsInput settingsInput) {
        if (settingsIsCorrect(settingsInput)) {
            gameSettings = settingsInput.toGameSettings();
            var ships = generateField(settingsInput);
            field.applySettings(ships, gameSettings);
            informationHolder.setTorpedoCount(gameSettings.torpedoCount());
            hardwareSettings.outputProvider().onGameBegins();
            hardwareSettings.outputProvider().showGameField(field);
            state = GameState.PLAYING;
        } else {
            hardwareSettings.outputProvider().incorrectGameSettings();
        }
    }

    private Boolean settingsIsCorrect(SettingsInput settings) {
        return fieldGenerator.fieldExistsWith(settings.toGameSettings());
    }

    private List<Ship> generateField(SettingsInput settings) {
        return fieldGenerator.generateShipsLocation(settings.toGameSettings());
    }
}
