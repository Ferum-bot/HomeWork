package battleship.models.commands.impl;

import battleship.models.commands.UserCommand;

public record HitCoordinate(

    Integer x,

    Integer y,

    String args
) implements UserCommand {

    private static final String COMMAND_NAME = "Hit Coordinates";

    public static Boolean matchesPattern(String userInput) {

    }

    public static HitCoordinate getFrom(String userInput) {

    }

    @Override
    public String commandName() {
        return COMMAND_NAME;
    }
}
