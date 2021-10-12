package battleship.game.settings;

import battleship.ui.input.InputCommandsHandler;
import battleship.ui.output.OutputGameInfoProvider;

public record HardwareSettings(

    InputCommandsHandler inputHandler,

    OutputGameInfoProvider outputProvider
) {
}
