package battleship.models.ship.impl;

import battleship.models.ship.Ship;

public class Carrier extends Ship {

    private static final Integer SHIP_LENGTH = 5;

    @Override
    public Integer getShipLength() {
        return SHIP_LENGTH;
    }
}
