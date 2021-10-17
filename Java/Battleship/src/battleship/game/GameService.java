package battleship.game;

import battleship.game.action_result.FieldActionHolder;
import battleship.game.action_result.TorpedoHitResult;
import battleship.game.settings.GameSettings;
import battleship.models.commands.impl.HitCoordinate;
import battleship.models.field.GameField;
import battleship.game.action_result.HitResult;
import battleship.models.field.coordinate.FieldCoordinate;
import battleship.models.info.InformationHolder;
import battleship.models.ship.Ship;
import battleship.models.ship.impl.*;
import battleship.models.statistics.Statistics;

import java.util.Optional;

public class GameService {

    private final GameSettings gameSettings;

    private final GameField gameField;

    private final InformationHolder informationHolder;

    private Optional<Ship> previosShipHit = Optional.empty();

    public GameService(GameSettings gameSettings, GameField gameField, InformationHolder informationHolder) {
        this.gameSettings = gameSettings;
        this.gameField = gameField;
        this.informationHolder = informationHolder;
    }

    public Statistics getGameStatistic() {
        return informationHolder.toStatistics();
    }

    public GameField getGameField() {
        return gameField;
    }

    public Boolean isGameWin() {
        return gameField.allShipsIsSunk();
    }

    public HitResult performDefaultUserHit(HitCoordinate coordinate) {
        informationHolder.increaseScore();

        var fieldCoordinate = parseHitCoordinate(coordinate);
        var actionResult = gameField.defaultShootAt(fieldCoordinate);
        handleRecoveryMode(actionResult);
        processFieldActionResult(actionResult);

        var hitResult = actionResult.hitResult();
        actionResult.effectedShip().ifPresent(ship -> hitResult.setShipName(ship.getShipName()));
        return hitResult;
    }

    public TorpedoHitResult performTorpedoUserHit(HitCoordinate coordinate) {
        if (!informationHolder.isTorpedoAvailable()) {
            return TorpedoHitResult.NO_AVAILABLE_TORPEDOES;
        }

        informationHolder.increaseScore();
        informationHolder.decreaseTorpedoCount();

        var fieldCoordinate = parseHitCoordinate(coordinate);
        var actionResult = gameField.torpedoShootAt(fieldCoordinate);
        handleRecoveryMode(actionResult);
        processFieldActionResult(actionResult);

        var hitResult = actionResult.hitResult();
        return switch (hitResult) {
            case MISSED -> torpedoMissedHitDelegate(actionResult);
            case HIT, SUNK -> torpedoHitDelegate(actionResult);
        };
    }

    private FieldCoordinate parseHitCoordinate(HitCoordinate coordinate) {
        return new FieldCoordinate(coordinate.x(), coordinate.y());
    }

    private void processFieldActionResult(FieldActionHolder fieldActionHolder) {
        var hitResult = fieldActionHolder.hitResult();
        if (hitResult == HitResult.MISSED) {
            informationHolder.increaseMissedHits();
            return;
        }
        if (hitResult == HitResult.HIT) {
            return;
        }
        var sunkShip = fieldActionHolder.effectedShip();
        sunkShip.ifPresent(ship -> {
            if (ship instanceof Carrier) {
                informationHolder.increaseCarrierSunkCount();
            }
            if (ship instanceof Battleship) {
                informationHolder.increaseBattleshipSunkCount();
            }
            if (ship instanceof Cruiser) {
                informationHolder.increaseCruiserSunkCount();
            }
            if (ship instanceof Destroyer) {
                informationHolder.increaseDestroyerSunkCount();
            }
            if (ship instanceof Submarine) {
                informationHolder.increaseSubmarineSunkCount();
            }
        });
    }

    private void handleRecoveryMode(FieldActionHolder fieldActionHolder) {
        if (!gameSettings.isRecoveryModeEnabled()) {
            return;
        }

        var actionResult = fieldActionHolder.hitResult();
        var currentShip = fieldActionHolder.effectedShip();
        if (actionResult == HitResult.MISSED) {
            recoverPreviousShip();
            return;
        }
        if (actionResult == HitResult.SUNK) {
            previosShipHit = Optional.empty();
            return;
        }
        if (previosShipHit.isEmpty()) {
            previosShipHit = currentShip;
            return;
        }

        currentShip.ifPresent(ship -> {
            var safePreviousShip = previosShipHit.get();
            if (!safePreviousShip.equals(ship)) {
                recoverPreviousShip();
                previosShipHit = currentShip;
            }
        });
    }

    private void recoverPreviousShip() {
        previosShipHit.ifPresent(ship -> {
            gameField.recoverAllShipCoordinates(ship);
            ship.recoverShip();
        });
        previosShipHit = Optional.empty();
    }

    private TorpedoHitResult torpedoMissedHitDelegate(FieldActionHolder fieldActionHolder) {
        var result = TorpedoHitResult.MISSED;
        result.setAvailableTorpedoCount(informationHolder.getAvailableTorpedoCount());
        return result;
    }

    private TorpedoHitResult torpedoHitDelegate(FieldActionHolder fieldActionHolder) {
        var result = TorpedoHitResult.SUNK;
        var sunkShip = fieldActionHolder.effectedShip();
        sunkShip.ifPresent(ship -> result.setShipName(ship.getShipName()));
        result.setAvailableTorpedoCount(informationHolder.getAvailableTorpedoCount());
        return result;
    }
}
