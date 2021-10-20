package battleship.models.field;

import battleship.game.action_result.FieldActionHolder;
import battleship.game.settings.GameSettings;
import battleship.models.field.coordinate.FieldCoordinate;
import battleship.models.ship.Ship;
import battleship.models.ship.ShipCoordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static battleship.models.field.coordinate.CoordinateStatus.*;

/**
 * Abstraction for battleship game field.
 * @author matvejpopov
 * @version 1.0.0
 */
public class GameField {

    private List<List<FieldCoordinate>> field;

    private List<Ship> ships;

    /**
     * Sets new ships and generates game field depending on game settings.
     * @param ships ships to place on game field
     * @param settings game settings
     */
    public void applySettings(List<Ship> ships, GameSettings settings) {
        this.ships = ships;
        generateEmptyField(settings);
    }

    public List<List<FieldCoordinate>> getField() {
        return field;
    }

    /**
     * Method to perform default shoot at field. Changes ship and cell status.
     * @param coordinate field coordinate.
     * @return result of shoot
     * @see FieldActionHolder
     */
    public FieldActionHolder defaultShootAt(FieldCoordinate coordinate) {
        var shipCoordinate = coordinate.toShipCoordinate();
        var shootShip = findShipAtField(shipCoordinate);

        if (shootShip.isEmpty()) {
            setEmptyShootAt(coordinate);
            return FieldActionHolder.missedHit();
        }

        var ship = shootShip.get();
        ship.hitShipAt(shipCoordinate);
        if (ship.isSunk()) {
            setShipIsSunk(ship);
            return FieldActionHolder.shipSunk(ship);
        } else {
            setShipIsShoot(coordinate, ship);
            return FieldActionHolder.shipHit(ship);
        }
    }

    /**
     * Method to perform torpedo shoot at field. Changes ship and cell status.
     * @param coordinate field coordinate
     * @return result of shoow
     * @see FieldActionHolder
     */
    public FieldActionHolder torpedoShootAt(FieldCoordinate coordinate) {
        var shipCoordinate = coordinate.toShipCoordinate();
        var shootShip = findShipAtField(shipCoordinate);

        if (shootShip.isEmpty()) {
            setEmptyShootAt(coordinate);
            return FieldActionHolder.missedHit();
        }

        var ship = shootShip.get();
        setShipIsSunk(ship);
        return FieldActionHolder.shipSunk(ship);
    }

    /**
     * Checks that all ships on field is sunk
     * @return true if all ships is sunk and false otherwise.
     */
    public Boolean allShipsIsSunk() {
        return ships.stream().allMatch(Ship::isSunk);
    }

    /**
     * Recovers all coordinates with this ship.
     * @param ship ship to coordinates recover.
     */
    public void recoverAllShipCoordinates(Ship ship) {
        var coordinates = ship.getCoordinates().stream().map(ShipCoordinate::toFieldCoordinate);
        coordinates.forEach(this::setCoordinateNotShoot);
    }

    private void setCoordinateNotShoot(FieldCoordinate coordinate) {
        findCoordinateAtField(coordinate).ifPresent(foundCoordinate -> {
            foundCoordinate.setStatus(NOT_SHOOT);
        });
    }

    private void generateEmptyField(GameSettings settings) {
        var height = settings.getFieldHeight();
        var width = settings.getFieldWidth();
        field = new ArrayList<>();
        for (int row = 0; row < height; row++) {
            var fieldRow = new ArrayList<FieldCoordinate>();
            for (int column = 0; column < width; column++) {
                var coordinate = new FieldCoordinate(column, row);
                fieldRow.add(coordinate);
            }
            field.add(fieldRow);
        }
    }

    private void setEmptyShootAt(FieldCoordinate coordinate) {
        findCoordinateAtField(coordinate).ifPresent(shootCoordinate -> {
            shootCoordinate.setStatus(EMPTY_SHOT);
        });
    }

    private void setShipIsSunk(Ship ship) {
        ship.sunkShip();
        ship.getCoordinates().forEach(coordinate -> {
            var fieldCoordinate = coordinate.toFieldCoordinate();
            findCoordinateAtField(fieldCoordinate)
                    .ifPresent(shootCoordinate -> shootCoordinate.setStatus(SHIP_SUNK));
        });
    }

    private void setShipIsShoot(FieldCoordinate coordinate, Ship ship) {
        var shipCoordinate = coordinate.toShipCoordinate();
        findCoordinateAtField(coordinate).ifPresent(shootCoordinate -> {
            shootCoordinate.setStatus(SHIP_SHOT);
            ship.hitShipAt(shipCoordinate);
        });
    }

    private Optional<Ship> findShipAtField(ShipCoordinate coordinate) {
        return ships.stream()
                .filter(ship -> ship.isPlacedAt(coordinate))
                .findAny();
    }

    private Optional<FieldCoordinate> findCoordinateAtField(FieldCoordinate coordinate) {
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
