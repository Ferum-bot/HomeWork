package battleship.models.ship;

import java.util.List;
import java.util.Objects;

abstract public class Ship {

    private final List<ShipCoordinate> coordinates;

    public Ship(List<ShipCoordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public abstract Integer getShipLength();

    public abstract String getShipName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return coordinates.equals(ship.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

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

    public void sunkShip() {
        coordinates.forEach(coordinate -> {
            coordinate.setIsHit(true);
        });
    }

    public void recoverShip() {
        coordinates.forEach(coordinate -> {
            coordinate.setIsHit(false);
        });
    }
}
