package battleship.models.ship.impl;

import battleship.models.ship.Ship;
import battleship.models.ship.ShipCoordinate;

import java.util.List;

public class Submarine extends Ship {

    public static final Integer SHIP_LENGTH = 1;

    public Submarine(List<ShipCoordinate> coordinates) {
        super(coordinates);
    }

    @Override
    public Integer getShipLength() {
        return SHIP_LENGTH;
    }

    @Override
    public String getShipName() {
        return this.getClass().getSimpleName();
    }
}
