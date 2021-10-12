package battleship.models.ship.impl;

import battleship.models.ship.Ship;

public class Cruiser extends Ship {

    private static final Integer SHIP_LENGTH = 3;

    @Override
    public Integer getShipLength() {
        return SHIP_LENGTH;
    }
}
