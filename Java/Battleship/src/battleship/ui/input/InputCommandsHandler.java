package battleship.ui.input;

import battleship.models.commands.UserCommand;

/**
 * Provides interface to input commands.
 * @author matvejpopov
 * @version 1.0.0
 */
public interface InputCommandsHandler {

    /**
     * Provides new user command.
     * @return user command
     * @see UserCommand
     */
    UserCommand awaitUserInput();
}
