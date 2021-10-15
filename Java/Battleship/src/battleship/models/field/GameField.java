package battleship.models.field;

import battleship.models.field.coordinate.FieldCoordinate;
import battleship.models.ship.Ship;
import battleship.models.ship.ShipCoordinate;

import java.util.List;
import java.util.Optional;

import static battleship.models.field.coordinate.CoordinateStatus.*;

public class GameField {

    private List<List<FieldCoordinate>> field;

    private List<Ship> ships;

    public void applySettings(List<Ship> ships) {
        this.ships = ships;
        generateFieldByShips();
    }

    public List<List<FieldCoordinate>> getField() {
        return field;
    }

    public HitResult defaultShootAt(FieldCoordinate coordinate) {
        var shipCoordinate = coordinate.toShipCoordinate();
        var shootShip = findShootShip(shipCoordinate);

        if (shootShip.isEmpty()) {
            setEmptyShootAt(coordinate);
            return HitResult.MISSED;
        }

        var ship = shootShip.get();
        ship.hitShipAt(shipCoordinate);
        if (ship.isSunk()) {
            setShipIsSunk(ship);
            return HitResult.SUNK;
        } else {
            setShipIsShoot(coordinate, ship);
            return HitResult.HIT;
        }
    }

    public HitResult torpedoShootAt(FieldCoordinate coordinate) {
        var shipCoordinate = coordinate.toShipCoordinate();
        var shootShip = findShootShip(shipCoordinate);

        if (shootShip.isEmpty()) {
            setEmptyShootAt(coordinate);
            return HitResult.MISSED;
        }

        var ship = shootShip.get();
        setShipIsSunk(ship);
        return HitResult.SUNK;
    }

    public Boolean allShipsIsSunk() {
        return ships.stream().allMatch(Ship::isSunk);
    }

    public void refreshAllHitShips() {

    }

    private void generateFieldByShips() {

    }

    private void setEmptyShootAt(FieldCoordinate coordinate) {
        findShootCoordinate(coordinate).ifPresent(shootCoordinate -> {
            shootCoordinate.setStatus(EMPTY_SHOT);
        });
    }

    private void setShipIsSunk(Ship ship) {
        ship.sunkShip();
        ship.getCoordinates().forEach(coordinate -> {
            var fieldCoordinate = coordinate.toFieldCoordinate();
            findShootCoordinate(fieldCoordinate)
                    .ifPresent(shootCoordinate -> shootCoordinate.setStatus(SHIP_SUNK));
        });
    }

    private void setShipIsShoot(FieldCoordinate coordinate, Ship ship) {
        var shipCoordinate = coordinate.toShipCoordinate();
        findShootCoordinate(coordinate).ifPresent(shootCoordinate -> {
            shootCoordinate.setStatus(SHIP_SHOT);
            ship.hitShipAt(shipCoordinate);
        });
    }

    private Optional<Ship> findShootShip(ShipCoordinate coordinate) {
        return ships.stream()
                .filter(ship -> ship.isPlacedAt(coordinate))
                .findAny();
    }

    private Optional<FieldCoordinate> findShootCoordinate(FieldCoordinate coordinate) {
        var width = field.size();
        for (List<FieldCoordinate> fieldRow : field) {
            var shootCoordinate = fieldRow.stream()
                    .filter(fieldCoordinate -> fieldCoordinate.equals(coordinate))
                    .findAny();

            if (shootCoordinate.isPresent()) {
                return shootCoordinate;
            }
        }
        return Optional.empty();
    }
}
