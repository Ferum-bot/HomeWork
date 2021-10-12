package battleship.models.commands.impl;

import battleship.models.commands.UserCommand;

import java.util.Locale;

public class Exit implements UserCommand {

    private static final String COMMAND_NAME = "EXIT";

    public static Boolean matchesPattern(String userInput) {
        return userInput.toLowerCase(Locale.ROOT).equals("exit");
    }

    @Override
    public String commandName() {
        return COMMAND_NAME;
    }
}
