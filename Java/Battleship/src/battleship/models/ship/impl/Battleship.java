package battleship.models.ship.impl;

import battleship.models.ship.Ship;
import battleship.models.ship.ShipCoordinate;

import java.util.List;

public class Battleship extends Ship {

    public static final Integer SHIP_LENGTH = 4;

    public Battleship(List<ShipCoordinate> coordinates) {
        super(coordinates);
    }

    @Override
    public Integer getShipLength() {
        return SHIP_LENGTH;
    }
}
