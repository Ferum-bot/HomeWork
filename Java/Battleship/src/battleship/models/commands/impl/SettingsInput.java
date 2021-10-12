package battleship.models.commands.impl;

import battleship.game.settings.GameSettings;
import battleship.models.commands.UserCommand;

public class SettingsInput implements UserCommand {

    private static final String COMMAND_NAME = "User Settings input";

    public static Boolean matchesPattern(String userInput) {

    }

    @Override
    public String commandName() {
        return COMMAND_NAME;
    }

    public GameSettings toGameSettings() {

    }
}
