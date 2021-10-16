package battleship.game.generators;

import battleship.game.settings.GameSettings;
import battleship.models.ship.Ship;
import battleship.models.ship.ShipCoordinate;
import battleship.models.ship.impl.*;

import java.util.*;

public class RandomFieldGenerator implements FieldGenerator {

    private static final Integer CELLS_OFF_SET = 6;

    private static final Integer DURATION_COUNT = 4;
    private static final List<Integer> X_OFF_SETS = List.of(0, 0, 1, -1);
    private static final List<Integer> Y_OFF_SETS = List.of(1, -1, 0, 0);

    private List<ShipCoordinate> usedCoordinates = new LinkedList<>();

    @Override
    public Boolean fieldExistsWith(GameSettings settings) {
        var height = settings.fieldHeight();
        var width = settings.fieldWidth();
        var carrierCount = settings.carrierCount();
        var battleshipCount = settings.battleshipCount();
        var cruiserCount = settings.cruiserCount();
        var destroyerCount = settings.destroyerCount();
        var submarineCount = settings.submarineCount();

        var totalAvailableCells = height * width;
        var totalShipsSize = carrierCount * Carrier.SHIP_LENGTH + battleshipCount * Battleship.SHIP_LENGTH;
        totalShipsSize += cruiserCount * Cruiser.SHIP_LENGTH + destroyerCount * Destroyer.SHIP_LENGTH + submarineCount;

        return totalAvailableCells - CELLS_OFF_SET >= totalShipsSize;
    }

    @Override
    public List<Ship> generateShipsLocation(GameSettings settings) {
        List<Ship> ships = new LinkedList<>();
        ships.addAll(generateCarriers(settings));
        ships.addAll(generateBattleships(settings));
        ships.addAll(generateCruisers(settings));
        ships.addAll(generateDestroyers(settings));
        ships.addAll(generateSubmarines(settings));

        return ships;
    }

    private List<Ship> generateCarriers(GameSettings settings) {
        var ships = new LinkedList<Ship>();
        var length = Carrier.SHIP_LENGTH;
        var carrierCount = settings.carrierCount();
        for (int i = 0; i < carrierCount; i++) {
            var coordinates = generateCoordinatesWithLength(length, settings);
            var carrier = new Carrier(coordinates);
            ships.add(carrier);
        }

        return ships;
    }

    private List<Ship> generateBattleships(GameSettings settings) {
        var ships = new LinkedList<Ship>();
        var length = Battleship.SHIP_LENGTH;
        var battleshipCount = settings.battleshipCount();
        for (int i = 0; i < battleshipCount; i++) {
            var coordinates = generateCoordinatesWithLength(length, settings);
            var battleship = new Battleship(coordinates);
            ships.add(battleship);
        }

        return ships;
    }

    private List<Ship> generateCruisers(GameSettings settings) {
        var ships = new LinkedList<Ship>();
        var length = Cruiser.SHIP_LENGTH;
        var cruiserCount = settings.cruiserCount();
        for (int i = 0; i < cruiserCount; i++) {
            var coordinates = generateCoordinatesWithLength(length, settings);
            var cruiser = new Cruiser(coordinates);
            ships.add(cruiser);
        }

        return ships;
    }

    private List<Ship> generateDestroyers(GameSettings settings) {
        var ships = new LinkedList<Ship>();
        var length = Destroyer.SHIP_LENGTH;
        var destroyerCount = settings.destroyerCount();
        for (int i = 0; i < destroyerCount; i++) {
            var coordinates = generateCoordinatesWithLength(length, settings);
            var destroyer = new Destroyer(coordinates);
            ships.add(destroyer);
        }

        return ships;
    }

    private List<Ship> generateSubmarines(GameSettings settings) {
        var ships = new LinkedList<Ship>();
        var length = Submarine.SHIP_LENGTH;
        var submarineCount = settings.submarineCount();
        for (int i = 0; i < submarineCount; i++) {
            var coordinates = generateCoordinatesWithLength(length, settings);
            var submarine = new Submarine(coordinates);
            ships.add(submarine);
        }

        return ships;
    }

    private List<ShipCoordinate> generateCoordinatesWithLength(Integer length, GameSettings settings) {
        var height = settings.fieldHeight();
        var width = settings.fieldWidth();
        for (int row = 1; row <= height; row++) {
            for (int column = 1; column < width; column++) {
                if (notAvailable(column, row)) {
                    continue;
                }
                if (notEnoughSpace(column, row, length)) {
                    continue;
                }
                return generateCoordinatesFrom(column, row, length);
            }
        }

        return Collections.emptyList();
    }

    private Boolean notAvailable(Integer x, Integer y) {
        return usedCoordinates.stream().anyMatch(coordinate ->
            coordinate.getY().equals(y) && coordinate.getX().equals(x)
        );
    }

    private Boolean isAvailable(Integer x, Integer y) {
        return !notAvailable(x, y);
    }

    private Boolean notEnoughSpace(Integer x, Integer y, Integer length) {
        for (int duriation = 0; duriation < DURATION_COUNT; duriation++) {
            var isDurationAvailable = true;

            for (int additionalLength = 0; additionalLength < length; additionalLength++) {
                var currentX = x + X_OFF_SETS.get(duriation) * additionalLength;
                var currentY = y + Y_OFF_SETS.get(duriation) * additionalLength;

                if (notAvailable(currentX, currentY)) {
                    isDurationAvailable = false;
                    break;
                }
            }

            if (isDurationAvailable) {
                return true;
            }
        }

        return false;
    }

    private List<ShipCoordinate> generateCoordinatesFrom(Integer x, Integer y, Integer length) {
        for (int duriation = 0; duriation < DURATION_COUNT; duriation++) {
            var coordinates = new LinkedList<ShipCoordinate>();
            var isDurationAvailable = true;

            for (int additionalLength = 0; additionalLength < length; additionalLength++) {
                var currentX = x + X_OFF_SETS.get(duriation) * additionalLength;
                var currentY = y + Y_OFF_SETS.get(duriation) * additionalLength;

                if (notAvailable(currentX, currentY)) {
                    isDurationAvailable = false;
                    break;
                }
                var coordinate = new ShipCoordinate(currentX, currentY);
                coordinates.add(coordinate);
            }

            if (!isDurationAvailable) {
                continue;
            } else {
                return coordinates;
            }
        }

        return Collections.emptyList();
    }
}
