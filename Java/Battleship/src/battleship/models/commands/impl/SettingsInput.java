package battleship.models.commands.impl;

import battleship.core.util.StringUtil;
import battleship.game.settings.GameSettings;
import battleship.models.commands.UserCommand;

import java.util.Arrays;
import java.util.Objects;

public record SettingsInput(
    String input
) implements UserCommand {

    private static final String COMMAND_NAME = "User Settings input";

    private static final Character SPACE = ' ';

    private static final String TORPEDO_FLAG = "T";
    private static final String RECOVERY_FLAG = "R";

    public static Boolean matchesPattern(String userInput) {
        var formattedString = userInput.strip();
        var fieldWidth = getFieldWidth(formattedString);
        var fieldHeight = getFieldHeight(formattedString);
        var carrierCount = getCarrierCount(formattedString);
        var battleshipCount = getBattleshipCount(formattedString);
        var cruiserCount = getCruiserCount(formattedString);
        var destroyerCount = getDestroyerCount(formattedString);
        var submarineCount = getSubmarineCount(formattedString);
        var torpedoesCount = getTorpedoesCount(formattedString);
        var isRecoveryModeEnabled = isRecoveryModeEnabled(formattedString);
        Object[] objects = {
                fieldWidth, fieldHeight, carrierCount, battleshipCount, cruiserCount,
                destroyerCount, submarineCount, torpedoesCount, isRecoveryModeEnabled
        };

        return Arrays.stream(objects).allMatch(Objects::nonNull) && isCorrectGeneralPattern(formattedString);
    }

    private static Boolean isCorrectGeneralPattern(String userInput) {
        var spaceCount = StringUtil.countSpaces(userInput);
        return spaceCount >= 6 && spaceCount <= 9;
    }

    private static Integer getFieldWidth(String userInput) {
        var firstSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 1);
        if (firstSpaceIndex == null) {
            return null;
        }
        var actualString = userInput.substring(0, firstSpaceIndex);
        return StringUtil.convertToInteger(actualString);
    }

    private static Integer getFieldHeight(String userInput) {
        var firstSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 1);
        var secondSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 2);
        if (firstSpaceIndex == null || secondSpaceIndex == null) {
            return null;
        }
        var actualString = userInput.substring(firstSpaceIndex + 1, secondSpaceIndex);
        return StringUtil.convertToInteger(actualString);
    }

    private static Integer getCarrierCount(String userInput) {
        var secondSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 2);
        var thirdSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 3);
        if (secondSpaceIndex == null || thirdSpaceIndex == null) {
            return null;
        }
        var actualString = userInput.substring(secondSpaceIndex + 1, thirdSpaceIndex);
        return StringUtil.convertToInteger(actualString);
    }

    private static Integer getBattleshipCount(String userInput) {
        return getShipCountFrom(userInput, 3, 4);
    }

    private static Integer getCruiserCount(String userInput) {
        return getShipCountFrom(userInput, 4, 5);
    }

    private static Integer getDestroyerCount(String userInput) {
        return getShipCountFrom(userInput, 5, 6);
    }

    private static Integer getSubmarineCount(String userInput) {
        var sixSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 6);
        var sevSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 7);
        if (sixSpaceIndex == null) {
            return null;
        }
        String actualString;
        if (sixSpaceIndex.equals(sevSpaceIndex) || sevSpaceIndex == null) {
            actualString = userInput.substring(sixSpaceIndex + 1);
        } else {
            actualString = userInput.substring(sixSpaceIndex + 1, sevSpaceIndex);
        }
        return StringUtil.convertToInteger(actualString);
    }

    private static Integer getShipCountFrom(String userInput, Integer firstSpaceNumber, Integer secondSpaceNumber) {
        var firstSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, firstSpaceNumber);
        var secondSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, secondSpaceNumber);
        if (firstSpaceIndex == null || secondSpaceIndex == null) {
            return null;
        }
        var actualString = userInput.substring(firstSpaceIndex + 1, secondSpaceIndex);
        return StringUtil.convertToInteger(actualString);
    }

    private static Integer getTorpedoesCount(String userInput) {
        var sevenSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 7);
        var eightSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 8);
        if (sevenSpaceIndex == null || eightSpaceIndex == null) {
            return 0;
        }
        var actualString = userInput.substring(sevenSpaceIndex + 1, eightSpaceIndex);
        if (!actualString.equals(TORPEDO_FLAG)) {
            return 0;
        }
        var nineSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 9);
        String countString;
        if (nineSpaceIndex == null) {
            countString = userInput.substring(eightSpaceIndex + 1);
        } else {
            countString = userInput.substring(eightSpaceIndex + 1, nineSpaceIndex);
        }
        var convertedInteger = StringUtil.convertToInteger(countString);
        return convertedInteger == null ? 0 : convertedInteger;
    }

    private static Boolean isRecoveryModeEnabled(String userInput) {
        var firstSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 7);
        var secondSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 8);
        if (firstSpaceIndex == null) {
            return false;
        }
        String actualString;
        if (secondSpaceIndex == null) {
            actualString = userInput.substring(firstSpaceIndex + 1);
        } else {
            actualString = userInput.substring(firstSpaceIndex + 1, secondSpaceIndex);
        }
        if (actualString.equals(RECOVERY_FLAG)) {
            return true;
        }
        firstSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 9);
        if (firstSpaceIndex == null) {
            return false;
        }
        actualString = userInput.substring(firstSpaceIndex + 1);
        return actualString.equals(RECOVERY_FLAG);
    }

    public SettingsInput(String input) {
        this.input = input.strip();
    }

    @Override
    public String commandName() {
        return COMMAND_NAME;
    }

    public GameSettings toGameSettings() {
        if (!matchesPattern(input)) {
            return null;
        }
        var fieldWidth = getFieldWidth(input);
        var fieldHeight = getFieldHeight(input);
        var carrierCount = getCarrierCount(input);
        var battleshipCount = getBattleshipCount(input);
        var cruiserCount = getCruiserCount(input);
        var destroyerCount = getDestroyerCount(input);
        var submarineCount = getSubmarineCount(input);
        var torpedoesCount = getTorpedoesCount(input);
        var isRecoveryModeEnabled = isRecoveryModeEnabled(input);

        return new GameSettings(
                fieldWidth, fieldHeight, carrierCount, battleshipCount, cruiserCount,
                destroyerCount, submarineCount, torpedoesCount, isRecoveryModeEnabled
        );
    }
}
