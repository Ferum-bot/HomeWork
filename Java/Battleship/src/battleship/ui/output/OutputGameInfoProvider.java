package battleship.ui.output;

import battleship.models.field.GameField;
import battleship.models.statistics.Statistics;

public interface OutputGameInfoProvider {

    void onGameStarted();

    void showGameSettingsHint();

    void showHowToPlay();

    void undefinedCommand();

    void incorrectCommand();

    void onAwaitingHitCoordinate(GameField field);

    void onGameCanceled(Statistics statistics);

    void onGameWined(Statistics statistics);
}
