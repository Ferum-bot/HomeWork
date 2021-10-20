package battleship.core.guards;

/**
 * Enum for guard check result.
 * @author matvejpopov
 * @version 1.0.0
 * @see CommandGuard
 */
public enum GuardResult {
    /**
     * The command doesn't handled.
     * May be the implementation checks another command.
     */
    NOT_HANDLED,

    /**
     * The command contains correct data.
     */
    SUCCESS,

    /**
     * The command contains incorrect data.
     */
    FAILURE;
}
