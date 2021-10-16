package battleship.game.action_result;

public enum HitResult {

    MISSED, HIT, SUNK;

    private String shipName;

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
}
