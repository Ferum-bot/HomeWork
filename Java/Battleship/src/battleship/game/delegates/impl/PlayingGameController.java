package battleship.game.delegates.impl;

import battleship.game.GameService;
import battleship.game.GameState;
import battleship.game.delegates.GameStateControllerDelegate;
import battleship.game.settings.GameSettings;
import battleship.game.settings.HardwareSettings;
import battleship.models.commands.impl.*;
import battleship.models.field.GameField;
import battleship.models.info.InformationHolder;

public class PlayingGameController implements GameStateControllerDelegate  {

    private HardwareSettings hardwareSettings;

    private GameState gameState;

    private GameService gameService;

    @Override
    public GameState handleState(
        HardwareSettings hardwareSettings, GameSettings gameSettings,
        GameState state, GameField field, InformationHolder informationHolder
    ) {
        gameService = new GameService(gameSettings, field, informationHolder);
        this.hardwareSettings = hardwareSettings;
        this.gameState = gameState;

        return launchPlaying();
    }

    private GameState launchPlaying() {
        while (gameState == GameState.PLAYING) {
            var userCommand = hardwareSettings.inputHandler().awaitUserInput();

            if (userCommand instanceof Exit) {
                exitActionDelegate();
                continue;
            }
            if (userCommand instanceof HowToPlay) {
                hardwareSettings.outputProvider().showHowToPlay();
                continue;
            }
            if (userCommand instanceof HitCoordinate hitCoordinate) {
                hitActionDelegate(hitCoordinate);
                continue;
            }
            if (userCommand instanceof SettingsInput) {
                hardwareSettings.outputProvider().incorrectCommand();
                continue;
            }
            if (userCommand instanceof Undefined) {
                hardwareSettings.outputProvider().undefinedCommand();
                continue;
            }
        }

        return gameState;
    }

    private void exitActionDelegate() {
        var statistic = gameService.getGameStatistic();
        hardwareSettings.outputProvider().onGameCanceled(statistic);
        gameState = GameState.EXIT;
    }

    private void hitActionDelegate(HitCoordinate hitCoordinate) {
        var actionResult = gameService.performUserHit(hitCoordinate);
        var gameField = gameService.getGameField();
        hardwareSettings.outputProvider().showGameField(gameField);
        hardwareSettings.outputProvider().showHitResult(actionResult);

        if (gameService.isGameWin()) {
            var statistics = gameService.getGameStatistic();
            hardwareSettings.outputProvider().onGameWined(statistics);
            gameState = GameState.EXIT;
        }
    }
}
