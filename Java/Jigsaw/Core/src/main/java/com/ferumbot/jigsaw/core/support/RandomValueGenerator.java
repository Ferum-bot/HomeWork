package com.ferumbot.jigsaw.core.support;

import java.util.concurrent.ThreadLocalRandom;

public class RandomValueGenerator {

    public static int generateInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }
}
