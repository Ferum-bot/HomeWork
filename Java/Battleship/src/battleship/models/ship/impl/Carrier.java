package battleship.models.ship.impl;

import battleship.models.ship.Ship;
import battleship.models.ship.ShipCoordinate;

import java.util.List;

public class Carrier extends Ship {

    public static final Integer SHIP_LENGTH = 5;

    public Carrier(List<ShipCoordinate> coordinates) {
        super(coordinates);
    }

    @Override
    public Integer getShipLength() {
        return SHIP_LENGTH;
    }
}
