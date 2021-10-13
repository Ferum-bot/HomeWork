package battleship.models.commands.impl;

import battleship.core.StringUtil;
import battleship.game.settings.GameSettings;
import battleship.models.commands.UserCommand;

import java.util.List;
import java.util.Objects;

public class SettingsInput implements UserCommand {

    private static final String COMMAND_NAME = "User Settings input";

    private static final Character SPACE = ' ';

    public static Boolean matchesPattern(String userInput) {
        var formattedString = userInput.strip();
        var fieldWidth = getFieldWidth(formattedString);
        var fieldHeight = getFieldHeight(formattedString);
        var carrierCount = getCarrierCount(formattedString);
        var battleshipCount = getBattleshipCount(formattedString);
        var cruiserCount = getCruiserCount(formattedString);
        var destroyerCount = getDestroyerCount(formattedString);
        var submarineCount = getSubmarineCount(formattedString);
        var isTorpedoModeEnabled = isTorpedoModeEnabled(formattedString);
        var isRecoveryModeEnabled = isRecoveryModeEnabled(formattedString);
        var objects = List.of(
            fieldWidth, fieldHeight, carrierCount, battleshipCount, cruiserCount,
            destroyerCount, submarineCount, isTorpedoModeEnabled, isRecoveryModeEnabled
        );

        return objects.stream().allMatch(Objects::nonNull) && isCorrectGeneralPattern(formattedString);
    }

    private static Boolean isCorrectGeneralPattern(String userInput) {
        var spaceCount = StringUtil.countSpaces(userInput);
        return spaceCount >= 6 && spaceCount <= 8;
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
        var thirdSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 3);
        var forthSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 4);
        if (thirdSpaceIndex == null || forthSpaceIndex == null) {
            return null;
        }
        var actualString = userInput.substring(thirdSpaceIndex + 1, forthSpaceIndex);
        return StringUtil.convertToInteger(actualString);
    }

    private static Integer getCruiserCount(String userInput) {
        var forthSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 4);
        var fivesSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 5);
        if (fivesSpaceIndex == null || forthSpaceIndex == null) {
            return null;
        }
        var actualString = userInput.substring(forthSpaceIndex + 1, fivesSpaceIndex);
        return StringUtil.convertToInteger(actualString);
    }

    private static Integer getDestroyerCount(String userInput) {
        var fivesSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 5);
        var sixSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 6);
        if (fivesSpaceIndex == null || sixSpaceIndex == null) {
            return null;
        }
        var actualString = userInput.substring(fivesSpaceIndex + 1, sixSpaceIndex);
        return StringUtil.convertToInteger(actualString);
    }

    private static Integer getSubmarineCount(String userInput) {
        var sixSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 6);
        var sevSpaceIndex = StringUtil.getCharPosByNumber(userInput, SPACE, 7);
        if (sixSpaceIndex == null || sevSpaceIndex == null) {
            return null;
        }
        String actualString;
        if (sixSpaceIndex.equals(sevSpaceIndex)) {
            actualString = userInput.substring(sixSpaceIndex + 1);
        } else {
            actualString = userInput.substring(sixSpaceIndex + 1, sevSpaceIndex);
        }
        return StringUtil.convertToInteger(actualString);
    }

    private static Boolean isTorpedoModeEnabled(String userInput) {

    }

    private static Boolean isRecoveryModeEnabled(String userInput) {

    }

    private final String input;

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
        var isTorpedoModeEnabled = isTorpedoModeEnabled(input);
        var isRecoveryModeEnabled = isRecoveryModeEnabled(input);

        return new GameSettings(
            fieldWidth, fieldHeight, carrierCount, battleshipCount, cruiserCount,
            destroyerCount, submarineCount, isTorpedoModeEnabled, isRecoveryModeEnabled
        );
    }
}
