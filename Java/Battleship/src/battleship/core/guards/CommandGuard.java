package battleship.core.guards;

import battleship.game.settings.GameSettings;
import battleship.models.commands.UserCommand;

public interface CommandGuard {

    GuardResult checkForCorrectness(UserCommand command, GameSettings settings);
}
