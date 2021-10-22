package battleship.core.guards.impl;

import battleship.core.guards.CommandGuard;
import battleship.core.guards.GuardResult;
import battleship.game.settings.GameSettings;
import battleship.models.commands.UserCommand;
import battleship.models.commands.impl.SettingsInput;

import java.lang.reflect.Field;

public class SettingsInputGuard implements CommandGuard {

    @Override
    public GuardResult checkForCorrectness(UserCommand command, GameSettings settings) {
        if (!(command instanceof SettingsInput settingsInput)) {
            return GuardResult.NOT_HANDLED;
        }
        var currentSettings = settingsInput.toGameSettings();
        try {
            return tryToCheckSettings(currentSettings);
        } catch (NullPointerException exception) {
            return GuardResult.FAILURE;
        }
    }

    private GuardResult tryToCheckSettings(GameSettings settings){
        if (settings.getFieldWidth() <= 0 || settings.getFieldHeight() <= 0) {
            return GuardResult.FAILURE;
        }
        if (settings.getBattleshipCount() < 0 || settings.getCruiserCount() < 0) {
            return GuardResult.FAILURE;
        }
        if (settings.getCarrierCount() < 0 || settings.getDestroyerCount() < 0) {
            return GuardResult.FAILURE;
        }
        if (settings.getSubmarineCount() < 0 || settings.getTorpedoCount() < 0) {
            return GuardResult.FAILURE;
        }
        return GuardResult.SUCCESS;
    }
}
