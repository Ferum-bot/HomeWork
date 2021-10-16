package battleship.ui.output;

import battleship.game.action_result.HitResult;
import battleship.game.action_result.TorpedoHitResult;
import battleship.models.field.GameField;
import battleship.models.statistics.Statistics;

public interface OutputGameInfoProvider {

    void onGameStarted();

    void onGameBegins();

    void onGameCanceled(Statistics statistics);

    void onGameWined(Statistics statistics);

    void showGameSettingsHint();

    void showHowToPlay();

    void undefinedCommand();

    void incorrectCommand();

    void incorrectGameSettings();

    void showHitResult(HitResult result);

    void showTorpedoHitResult(TorpedoHitResult result);

    void showGameField(GameField field);
}
