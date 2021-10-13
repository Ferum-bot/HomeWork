package battleship.core;

public class StringUtil {

    public static Integer countSpaces(String string) {
        var size = string.length();
        var counter = 0;
        for (int index = 0; index < size; index++) {
            var character = string.charAt(index);
            if (character == ' ') {
                counter++;
            }
        }
        return counter;
    }

    public static Integer convertToInteger(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static Integer getCharPosByNumber(String string, Character character, Integer number) {
        var size = string.length();
        var counter = 0;
        for (int index = 0; index < size; index++) {
            var currentCharacter = string.charAt(index);
            if (character.equals(currentCharacter)) {
                counter++;
            }
            if (counter == number) {
                return index;
            }
        }
        return null;
    }
}
