package battleship.ui.input;

import battleship.models.commands.UserCommand;

public interface InputCommandsHandler {

    UserCommand awaitUserInput();
}
