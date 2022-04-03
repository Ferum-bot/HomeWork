package com.ferumbot.jigsaw.core.base;

public class BaseIdGenerator {

    public static final Character SEPARATOR = '$';

    public static String generateId(int first, int second) {
        return String.valueOf(first) + SEPARATOR + second;
    }

    public static Pair parseId(String string) {
        var separatorPos = string.indexOf(SEPARATOR);
        if (separatorPos == -1) {
            throw new IllegalArgumentException("Invalid Id string: " + string);
        }

        var first = Integer.parseInt(string.substring(0, separatorPos));
        var second = Integer.parseInt(string.substring(separatorPos + 1));
        return new Pair(first, second);
    }

    public static record Pair(int first, int second) {  }
}
