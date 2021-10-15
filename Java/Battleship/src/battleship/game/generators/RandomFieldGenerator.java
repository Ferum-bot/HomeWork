package battleship.game.generators;

import battleship.game.settings.GameSettings;
import battleship.models.ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class RandomFieldGenerator implements FieldGenerator {

    private static final Integer CELLS_OFF_SET = 6;

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
        var totalShipsSize = carrierCount * 5 + battleshipCount * 4 + cruiserCount * 3;
        totalShipsSize += destroyerCount * 2 + submarineCount;

        return totalAvailableCells - CELLS_OFF_SET >= totalShipsSize;
    }

    @Override
    public List<Ship> generateShipsLocation(GameSettings settings) {
        var ships = new ArrayList<Ship>();
        var height = settings.fieldHeight();
        var width = settings.fieldWidth();
        var carrierCount = settings.carrierCount();
        var battleshipCount = settings.battleshipCount();
        var cruiserCount = settings.cruiserCount();
        var destroyerCount = settings.destroyerCount();
        var submarineCount = settings.submarineCount();

        return ships;
    }
}
