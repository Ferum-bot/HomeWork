package battleship.game.action_result;

/**
 * Enum for torpedo hit.
 * @author matvejpopov
 * @version 1.0.0
 */
public enum TorpedoHitResult {
    /**
     * The torpedo shoot missed.
     */
    MISSED,

    /**
     * The torpedo shoot sunk the ship.
     */
    SUNK,

    /**
     * The torpedo shoot can't perform.
     * No available torpedoes.
     */
    NO_AVAILABLE_TORPEDOES;

    private Integer availableTorpedoCount;

    private String shipName;

    /**
     * Set available torpedoes count.
     * @param availableTorpedoCount available torpedoes count.
     */
    public void setAvailableTorpedoCount(Integer availableTorpedoCount) {
        this.availableTorpedoCount = availableTorpedoCount;
    }

    /**
     * Set affected ship name.
     * @param shipName affected ship name.
     */
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    /**
     * Get current available torpedoes count.
     * @return available torpedoes count.
     */
    public Integer getAvailableTorpedoCount() {
        return availableTorpedoCount;
    }

    /**
     * Get affected ship name.
     * @return affected ship name.
     */
    public String getShipName() {
        return shipName;
    }
}
