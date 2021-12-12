package com.github.ferum_bot.twentyone.models.players.thread;

import com.github.ferum_bot.twentyone.core.util.RandomUtil;
import com.github.ferum_bot.twentyone.models.game.GameTable;
import com.github.ferum_bot.twentyone.models.players.PlayerInfo;

import java.util.Collection;

final public class CheaterThreadPlayer extends ThreadPlayer {

    private static final int MIN_CHEAT_SLEEP_DURATION = 180;
    private static final int MAX_CHEAT_SLEEP_DURATION = 301;

    private static final int MAX_CHEAT_CARD_SCORE = 9;

    private final Collection<ThreadPlayer> commonPlayers;

    public CheaterThreadPlayer(GameTable table, int number, Collection<ThreadPlayer> commonPlayers) {
        super(table, number);
        this.commonPlayers = commonPlayers;
    }

    @Override
    public PlayerInfo getInformation() {
        return new PlayerInfo(playerNumber, score, true);
    }

    @Override
    void action() {
        try {
            while (isRun()) {
                getCardFromTableOrCheat();
            }
        } catch (InterruptedException ignored) { }
    }

    private void getCardFromTableOrCheat() throws InterruptedException {
        var value = RandomUtil.getRandomDigit();
        if (value < 4) {
            performCheat();
        } else {
            getFromTable();
        }
    }

    private void performCheat() throws InterruptedException {
        var randomPlayer = getRandomCommonPlayer();

        synchronized(randomPlayer.lock) {
            var playerScore = randomPlayer.score;
            var offSet = calculateScoreOffset(playerScore);
            score += offSet;
            randomPlayer.score -= offSet;
        }

        sleep(getCheatSleepDuration());
    }

    private void getFromTable() throws InterruptedException {
        var newCard = table.getCard();
        score += newCard;

        sleep(getSleepDuration());
    }

    private ThreadPlayer getRandomCommonPlayer() {
        var randomIndex = RandomUtil.getRandomInt(commonPlayers.size());
        return (ThreadPlayer) commonPlayers.toArray()[randomIndex];
    }

    private int calculateScoreOffset(int currentScore) {
        var randomOffset = RandomUtil.getRandomInt(MAX_CHEAT_CARD_SCORE);
        return Math.min(randomOffset, currentScore);
    }

    private int getCheatSleepDuration() {
        return RandomUtil.getRandomInt(MIN_CHEAT_SLEEP_DURATION, MAX_CHEAT_SLEEP_DURATION);
    }
}
