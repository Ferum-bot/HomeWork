package com.ferumbot.mapper.impl.core.util;

public class IdentifierProvider {

    private static final ThreadLocal<Long> sequence;

    static {
        sequence = ThreadLocal.withInitial(() -> 0L);
    }

    public static Long nextId() {
        var current = sequence.get();
        sequence.set(current + 1);
        return current;
    }
}