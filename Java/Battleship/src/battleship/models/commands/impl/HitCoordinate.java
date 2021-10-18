package battleship.models.commands.impl;

import battleship.core.util.StringUtil;
import battleship.models.commands.UserCommand;

public record HitCoordinate(

    Integer x,

    Integer y,

    String args
) implements UserCommand {

    private static final String COMMAND_NAME = "Hit Coordinates";

    private static final Integer NO_POSITION = -1;

    public static Boolean matchesPattern(String userInput) {
        var formattedString = userInput.strip();
        var xCoordinate = getXCoordinate(formattedString);
        var yCoordinate = getYCoordinate(formattedString);

        return xCoordinate != null && yCoordinate != null && isCorrectGeneralPattern(formattedString);
    }

    public static HitCoordinate getFrom(String userInput) {
        if (!matchesPattern(userInput)) {
            return null;
        }

        var formattedString = userInput.strip();
        var xCoordinate = getXCoordinate(formattedString) - 1;
        var yCoordinate = getYCoordinate(formattedString) - 1;
        var args = getArgs(formattedString);
        return new HitCoordinate(xCoordinate, yCoordinate, args);
    }

    private static Boolean isCorrectGeneralPattern(String userInput) {
        var spacesCount = StringUtil.countSpaces(userInput);
        return spacesCount >= 1 && spacesCount <= 2;

    }

    private static Integer getXCoordinate(String userInput) {
        var firstSpaceIndex = userInput.indexOf(" ");
        if (firstSpaceIndex == NO_POSITION) {
            return null;
        }
        var actualString = userInput.substring(0, firstSpaceIndex);
        return StringUtil.convertToInteger(actualString);
    }

    private static Integer getYCoordinate(String userInput) {
        var firstSpaceIndex = userInput.indexOf(" ");
        var secondSpaceIndex = userInput.lastIndexOf(" ");
        if (firstSpaceIndex == NO_POSITION || secondSpaceIndex == NO_POSITION) {
            return null;
        }
        String actualString;
        if (secondSpaceIndex == firstSpaceIndex) {
            actualString = userInput.substring(firstSpaceIndex + 1);
        } else {
            actualString = userInput.substring(firstSpaceIndex + 1, secondSpaceIndex);
        }
        return StringUtil.convertToInteger(actualString);
    }

    private static String getArgs(String userInput) {
        var firstSpaceIndex = userInput.indexOf(" ");
        var secondSpaceIndex = userInput.lastIndexOf(" ");
        if (firstSpaceIndex == NO_POSITION || secondSpaceIndex == NO_POSITION) {
            return "";
        }
        if (firstSpaceIndex == secondSpaceIndex) {
            return "";
        } else {
            var actualString = userInput.substring(secondSpaceIndex + 1);
            return actualString.length() != 1 ? "" : actualString;
        }
    }

    @Override
    public String commandName() {
        return COMMAND_NAME;
    }
}
