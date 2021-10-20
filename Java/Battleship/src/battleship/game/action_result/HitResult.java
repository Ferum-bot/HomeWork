package battleship.game.action_result;

/**
 * Enum for default hit.
 * @author matvejpopov
 * @version 1.0.0
 */
public enum HitResult {
    /**
     * The shoot was messed.
     */
    MISSED,

    /**
     * The shoot hits the ship.
     */
    HIT,

    /**
     * The shoot sunk the ship.
     */
    SUNK;

    private String shipName;

    /**
     * Get affected ship name.
     * @return ship name.
     */
    public String getShipName() {
        return shipName;
    }

    /**
     * Set affected ship name.
     * @param shipName affected ship name.
     */
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
}
