package battleship.models.ship;

import java.util.List;

abstract public class Ship {

    private Integer shipLength;

    private List<ShipCoordinate> coordinates;

    public abstract Integer getShipLength();

    public List<ShipCoordinate> getCoordinates() {
        return coordinates;
    }

    public Boolean isSunk() {
        return coordinates.stream().allMatch(ShipCoordinate::getIsHit);
    }

    public Boolean isPlacedAt(ShipCoordinate coordinate) {
        return coordinates.stream().anyMatch(currentCoordinate ->
            coordinate.getX().equals(currentCoordinate.getX()) &&
            coordinate.getY().equals(currentCoordinate.getY())
        );
    }

    public void hitShipAt(ShipCoordinate coordinate) {
        var hitCoordinate = coordinates.stream().filter(currentCoordinate ->
            coordinate.getX().equals(currentCoordinate.getX()) &&
            coordinate.getY().equals(currentCoordinate.getY())
        ).findFirst();

        hitCoordinate.ifPresent(value -> value.setIsHit(true));
    }
}
