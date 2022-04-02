package com.ferumbot.jigsaw.core.base;

public class BaseTimeFormatter {

    public static String formatSeconds(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int lastSeconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, lastSeconds);
    }
}
