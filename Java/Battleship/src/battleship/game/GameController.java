package battleship.game;

import battleship.game.delegates.GameStateControllerDelegate;
import battleship.game.delegates.impl.ConfiguringGameController;
import battleship.game.delegates.impl.FinishedGameController;
import battleship.game.delegates.impl.NotStartedGameController;
import battleship.game.delegates.impl.PlayingGameController;
import battleship.game.settings.HardwareSettings;
import battleship.models.field.GameField;
import battleship.ui.input.InputCommandsHandler;
import battleship.ui.output.OutputGameInfoProvider;

import static battleship.game.GameState.*;

public class GameController {

    private final HardwareSettings hardwareSettings;

    private final GameStateControllerDelegate notStartedControllerDelegate;
    private final GameStateControllerDelegate configuringControllerDelegate;
    private final GameStateControllerDelegate playingControllerDelegate;
    private final GameStateControllerDelegate finishedControllerDelegate;

    private GameState state = NOT_STARTED;

    private GameField field = new GameField();

    public GameController(HardwareSettings hardwareSettings) {
        this.hardwareSettings = hardwareSettings;

        notStartedControllerDelegate = new NotStartedGameController();
        configuringControllerDelegate = new ConfiguringGameController();
        playingControllerDelegate = new PlayingGameController();
        finishedControllerDelegate = new FinishedGameController();
    }

    public void startGame() {
        launchGameEngine();
    }

    private void launchGameEngine() {
        while (state != EXIT) {
            switch (state) {
                case NOT_STARTED -> {
                    state = notStartedControllerDelegate.handleState(hardwareSettings, state, field);
                }
                case CONFIGURING -> {
                    state = configuringControllerDelegate.handleState(hardwareSettings, state, field);
                }
                case PLAYING -> {
                    state = playingControllerDelegate.handleState(hardwareSettings, state, field);
                }
                case FINISHED -> {
                    state = finishedControllerDelegate.handleState(hardwareSettings, state, field);
                }
            }
        }
    }
}
