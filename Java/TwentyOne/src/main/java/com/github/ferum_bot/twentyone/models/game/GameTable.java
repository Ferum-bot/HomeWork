package com.github.ferum_bot.twentyone.models.game;

import com.github.ferum_bot.twentyone.core.util.RandomUtil;
import com.github.ferum_bot.twentyone.service.GameService;

/**
 * Class represents tha game table.
 * @author matvejpopov
 * @version 1.0.0
 * @see RandomUtil
 * @see GameService
 */
public class GameTable {

    /**
     * Method to get game card.
     * @return the number of game card.
     * @see RandomUtil
     * @apiNote this method is synchronized.
     */
    public synchronized int getCard() {
        return RandomUtil.getRandomCardNumber();
    }
}
