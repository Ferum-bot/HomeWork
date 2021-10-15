package battleship.game;

import battleship.game.delegates.GameStateControllerDelegate;
import battleship.game.delegates.impl.*;
import battleship.game.settings.GameSettings;
import battleship.game.settings.HardwareSettings;
import battleship.models.field.GameField;
import battleship.models.info.InformationHolder;

import static battleship.game.GameState.*;

public class GameController {

    private final GameStateControllerDelegate notStartedControllerDelegate;
    private final GameStateControllerDelegate configuringControllerDelegate;
    private final GameStateControllerDelegate playingControllerDelegate;
    private final GameStateControllerDelegate finishedControllerDelegate;

    private final HardwareSettings hardwareSettings;

    private final GameField field;

    private final InformationHolder informationHolder;

    private GameState state = NOT_STARTED;

    private GameSettings gameSettings;

    public GameController(HardwareSettings hardwareSettings) {
        this.hardwareSettings = hardwareSettings;

        notStartedControllerDelegate = new NotStartedGameController();
        configuringControllerDelegate = new ConfiguringGameController();
        playingControllerDelegate = new PlayingGameController();
        finishedControllerDelegate = new FinishedGameController();
        informationHolder = new InformationHolder();
        field = new GameField();
    }

    public void startGame() {
        launchGameEngine();
    }

    private void launchGameEngine() {
        while (state != EXIT) {
            switch (state) {
                case NOT_STARTED -> {
                    state = notStartedControllerDelegate.handleState(hardwareSettings, gameSettings, state, field, informationHolder);
                }
                case CONFIGURING -> {
                    state = configuringControllerDelegate.handleState(hardwareSettings, gameSettings, state, field, informationHolder);
                }
                case PLAYING -> {
                    state = playingControllerDelegate.handleState(hardwareSettings, gameSettings, state, field, informationHolder);
                }
                case FINISHED -> {
                    state = finishedControllerDelegate.handleState(hardwareSettings, gameSettings, state, field, informationHolder);
                }
            }
        }
    }
}
