package com.github.ferum_bot.twentyone.core.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class provides support methods for random operations.
 * @author matvejpopov
 * @version 1.0.0
 */
public class RandomUtil {

    private static final int MIN_CARD_NUMBER = 1;
    private static final int MAX_CARD_NUMBER = 11;

    private static final int MIN_SLEEP_MILLIS = 100;
    private static final int MAX_SLEEP_MILLIS = 201;

    /**
     * Provides random card number.
     * @return random card number.
     */
    public static int getRandomCardNumber() {
        return ThreadLocalRandom.current().nextInt(MIN_CARD_NUMBER, MAX_CARD_NUMBER);
    }

    /**
     * Provides random sleep millis.
     * @return random sleep millis.
     */
    public static int getRandomSleepMillis() {
        return ThreadLocalRandom.current().nextInt(MIN_SLEEP_MILLIS, MAX_SLEEP_MILLIS);
    }

    /**
     * Provides random digit. Between 0 and 9.
     * @return random digit.
     */
    public static int getRandomDigit() {
        return ThreadLocalRandom.current().nextInt(10);
    }

    /**
     * Provides random int till bounds.
     * @param bounds up border.
     * @return random int.
     */
    public static int getRandomInt(int bounds) {
        return ThreadLocalRandom.current().nextInt(bounds);
    }

    /**
     * Provides random number.
     * @param from number from(inclusive).
     * @param to number to(exclusive).
     * @return random number.
     */
    public static int getRandomInt(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }
}
