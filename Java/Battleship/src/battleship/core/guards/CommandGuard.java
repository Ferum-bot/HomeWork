package battleship.core.guards;

import battleship.game.settings.GameSettings;
import battleship.models.commands.UserCommand;

/**
 * Checks that input command contains valid information.
 * @author matvejpopov
 * @version 1.0.0
 */
public interface CommandGuard {

    /**
     * Check that command is correct.
     * @param command: input user command
     * @param settings: game settings
     * @return check result
     * @see GuardResult
     */
    GuardResult checkForCorrectness(UserCommand command, GameSettings settings);
}
