package battleship.ui.util;

public class ConsoleUtil {

    private static final Integer SEPARATOR_LENGTH = 30;
    private static final Character SEPARATOR = '-';

    public static String getSeparator() {
        return "\n" + String.valueOf(SEPARATOR).repeat(Math.max(0, SEPARATOR_LENGTH)) + "\n";
    }
}
