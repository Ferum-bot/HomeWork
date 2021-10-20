package battleship.game;

/**
 * Enum that shows current game state.
 * @author matvejpopov
 * @version 1.0.0
 * @see GameController
 * @see battleship.game.delegates.GameStateControllerDelegate
 */
public enum GameState {
    /**
     * Initial game state.
     */
    NOT_STARTED,

    /**
     * Game is configuring, the user input game settings.
     */
    CONFIGURING,

    /**
     * Game is playing, the user input hit coordinates.
     */
    PLAYING,

    /**
     * Game is win/canceled. The program ends.
     */
    EXIT;
}
