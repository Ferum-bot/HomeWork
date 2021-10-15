package battleship.ui.input;

import battleship.models.commands.UserCommand;
import battleship.models.commands.impl.*;
import java.util.Scanner;

public class ConsoleInputHandler implements InputCommandsHandler {

    @Override
    public UserCommand awaitUserInput() {
        var scanner = new Scanner(System.in);
        var userInput = scanner.nextLine();
        scanner.close();
        return parseUserCommand(userInput);
    }

    private UserCommand parseUserCommand(String userCommand) {
        if (Exit.matchesPattern(userCommand)) {
            return new Exit();
        }
        if (HowToPlay.matchesPattern(userCommand)) {
            return new HowToPlay();
        }
        if (HitCoordinate.matchesPattern(userCommand)) {
            return HitCoordinate.getFrom(userCommand);
        }
        if (SettingsInput.matchesPattern(userCommand)) {
            return new SettingsInput(userCommand);
        }
        return new Undefined();
    }
}
