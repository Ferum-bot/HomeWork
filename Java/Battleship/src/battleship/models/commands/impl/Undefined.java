package battleship.models.commands.impl;

import battleship.models.commands.UserCommand;

public class Undefined implements UserCommand {

    private static final String COMMAND_NAME = "Undefined";

    @Override
    public String commandName() {
        return COMMAND_NAME;
    }
}
