package com.github.ferum_bot.twentyone.models.players.thread;

import com.github.ferum_bot.twentyone.core.util.RandomUtil;
import com.github.ferum_bot.twentyone.models.game.GameTable;
import com.github.ferum_bot.twentyone.models.players.Player;

/**
 * Base entity for thread player.
 * @author matvejpopov
 * @version 1.0.0
 * @see Player
 * @see GameTable
 * @see CommonThreadPlayer
 * @see CheaterThreadPlayer
 */
public abstract class ThreadPlayer extends Thread implements Player {

    protected final Object lock = new Object();

    protected GameTable table;

    protected int playerNumber = 0;

    protected int score = 0;


    public ThreadPlayer(GameTable table, int number) {
        this.table = table;
        playerNumber = number;
    }

    /**
     * Performs players action.
     * @see CommonThreadPlayer
     * @see CheaterThreadPlayer
     */
    abstract void action();

    @Override
    public void startPlaying() {
        start();
    }

    @Override
    public void stopPlaying() {
        interrupt();
    }

    @Override
    public void setGameTable(GameTable table) {
        this.table = table;
    }

    @Override
    public void run() {
        action();
    }

    protected boolean isRun() {
        return !isInterrupted();
    }

    protected int getSleepDuration() {
        return RandomUtil.getRandomSleepMillis();
    }
}
