package battleship.game;

import battleship.game.settings.GameSettings;
import battleship.models.commands.impl.HitCoordinate;
import battleship.models.field.GameField;
import battleship.models.field.HitResult;
import battleship.models.field.coordinate.FieldCoordinate;
import battleship.models.info.InformationHolder;
import battleship.models.statistics.Statistics;

public class GameService {

    private static final String TORPEDO_MODE_ARG = "T";

    private final GameSettings gameSettings;

    private final GameField gameField;

    private final InformationHolder informationHolder;

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

    public HitResult performUserHit(HitCoordinate coordinate) {
        informationHolder.increaseScore();
        if (isTorpedoHit(coordinate)) {
            return handleTorpedoHit(coordinate);
        } else {
            return handleDefaultHit(coordinate);
        }
    }

    private Boolean isTorpedoHit(HitCoordinate coordinate) {
        return coordinate.args().equals(TORPEDO_MODE_ARG);
    }

    private HitResult handleTorpedoHit(HitCoordinate hitCoordinate) {

    }

    private HitResult handleDefaultHit(HitCoordinate hitCoordinate) {
        var fieldCoordinate = new FieldCoordinate(hitCoordinate.x(), hitCoordinate.y());
        var result = gameField.defaultShootAt(fieldCoordinate);

    }
}
