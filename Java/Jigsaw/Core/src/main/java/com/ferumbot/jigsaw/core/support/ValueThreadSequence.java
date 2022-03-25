package com.ferumbot.jigsaw.core.support;

import java.util.concurrent.ThreadLocalRandom;

public class ValueThreadSequence {

    private static ThreadLocal<Integer> integerSequence;

    public static int generateInt() {
        if (integerSequence == null) {
            integerSequence = ThreadLocal.withInitial(() -> 0);
            integerSequence.set(0);
        }

        var value = integerSequence.get();
        integerSequence.set(value + 1);
        return value;
    }

    public static void flushToInitial() {
        integerSequence = ThreadLocal.withInitial(() -> 0);
        integerSequence.set(0);
    }
}
