package battleship.game.action_result;

public enum TorpedoHitResult {

    MISSED, SUNK, NO_AVAILABLE_TORPEDOES;

    private Integer availableTorpedoCount;

    private String shipName;

    public void setAvailableTorpedoCount(Integer availableTorpedoCount) {
        this.availableTorpedoCount = availableTorpedoCount;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Integer getAvailableTorpedoCount() {
        return availableTorpedoCount;
    }

    public String getShipName() {
        return shipName;
    }
}
