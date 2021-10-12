package battleship.models.commands.impl;

import battleship.models.commands.UserCommand;

import java.util.Locale;

public class HowToPlay implements UserCommand {

    private static final String COMMAND_NAME = "How to play";

    public static Boolean matchesPattern(String userInput) {
        return userInput.toLowerCase(Locale.ROOT).equals("help");
    }

    @Override
    public String commandName() {
        return null;
    }
}
