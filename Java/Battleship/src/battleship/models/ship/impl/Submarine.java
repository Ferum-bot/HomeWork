package battleship.models.ship.impl;

import battleship.models.ship.Ship;

public class Submarine extends Ship {

    private static final Integer SHIP_LENGTH = 1;

    @Override
    public Integer getShipLength() {
        return SHIP_LENGTH;
    }
}
