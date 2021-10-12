package battleship.models.ship.impl;

import battleship.models.ship.Ship;

public class Battleship extends Ship {

    private static final Integer SHIP_LENGTH = 4;

    @Override
    public Integer getShipLength() {
        return SHIP_LENGTH;
    }
}
