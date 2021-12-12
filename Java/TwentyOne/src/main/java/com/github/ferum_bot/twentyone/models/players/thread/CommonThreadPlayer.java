package com.github.ferum_bot.twentyone.models.players.thread;

import com.github.ferum_bot.twentyone.models.game.GameTable;
import com.github.ferum_bot.twentyone.models.players.PlayerInfo;

final public class CommonThreadPlayer extends ThreadPlayer {

    public CommonThreadPlayer(GameTable table, int number) {
        super(table, number);
    }

    @Override
    public PlayerInfo getInformation() {
        return new PlayerInfo(playerNumber, score, false);
    }

    @Override
    void action() {
        try {
            while (isRun()) {
                getCardAndIncrementScore();
                sleep(getSleepDuration());
            }
        } catch (InterruptedException ignored) { }
    }

    private void getCardAndIncrementScore() {
        synchronized (lock) {
            var newCard = table.getCard();
            score += newCard;
        }
    }
}
