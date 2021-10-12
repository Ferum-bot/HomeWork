package battleship.models.ship.impl;

import battleship.models.ship.Ship;

public class Destroyer extends Ship {

    private static final Integer SHIP_LENGTH = 2;

    @Override
    public Integer getShipLength() {
        return SHIP_LENGTH;
    }
}
