package battleship.core.guards.impl;

import battleship.core.guards.CommandGuard;
import battleship.core.guards.GuardResult;
import battleship.game.settings.GameSettings;
import battleship.models.commands.UserCommand;
import battleship.models.commands.impl.HitCoordinate;

public class HitCommandGuard implements CommandGuard {

    @Override
    public GuardResult checkForCorrectness(UserCommand command, GameSettings settings) {
        if (!(command instanceof HitCoordinate hitCommand)) {
            return GuardResult.NOT_HANDLED;
        }

        var xCoordinate = hitCommand.x();
        var yCoordinate = hitCommand.y();
        if (xCoordinate < 1 || yCoordinate < 1) {
            return GuardResult.FAILURE;
        }
        if (xCoordinate > settings.fieldWidth() || yCoordinate > settings.fieldHeight()) {
            return GuardResult.FAILURE;
        }

        return GuardResult.SUCCESS;
    }
}
