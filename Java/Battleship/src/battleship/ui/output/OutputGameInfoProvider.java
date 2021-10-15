package battleship.ui.output;

import battleship.models.field.HitResult;
import battleship.models.field.GameField;
import battleship.models.statistics.Statistics;

public interface OutputGameInfoProvider {

    void onGameStarted();

    void showGameSettingsHint();

    void showHowToPlay();

    void undefinedCommand();

    void incorrectCommand();

    void incorrectGameSettings();

    void showHitResult(HitResult result);

    void noAvailableTorpedo();

    void torpedoMissed(Integer availableTorpedoCount);

    void torpedoSunkShip(Integer availableTorpedoCount, HitResult result);

    void showGameField(GameField field);

    void onGameCanceled(Statistics statistics);

    void onGameWined(Statistics statistics);
}
