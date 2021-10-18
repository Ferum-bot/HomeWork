package battleship.game.settings;

import battleship.models.commands.impl.SettingsInput;

public class GameSettings {

    private Integer fieldWidth = 0;

    private Integer fieldHeight = 0;

    private Integer carrierCount = 0;

    private Integer battleshipCount = 0;

    private Integer cruiserCount = 0;

    private Integer destroyerCount = 0;

    private Integer submarineCount = 0;

    private Integer torpedoCount = 0;

    private Boolean isRecoveryModeEnabled = false;

    public static GameSettings empty() {
        return new GameSettings();
    }

    public GameSettings() {

    }

    public GameSettings(
        Integer fieldWidth, Integer fieldHeight, Integer carrierCount,
        Integer battleshipCount, Integer cruiserCount, Integer destroyerCount,
        Integer submarineCount, Integer torpedoCount, Boolean isRecoveryModeEnabled
    ) {
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        this.carrierCount = carrierCount;
        this.battleshipCount = battleshipCount;
        this.cruiserCount = cruiserCount;
        this.destroyerCount = destroyerCount;
        this.submarineCount = submarineCount;
        this.torpedoCount = torpedoCount;
        this.isRecoveryModeEnabled = isRecoveryModeEnabled;
    }

    public Integer getFieldWidth() {
        return fieldWidth;
    }

    public Integer getFieldHeight() {
        return fieldHeight;
    }

    public Integer getCarrierCount() {
        return carrierCount;
    }

    public Integer getBattleshipCount() {
        return battleshipCount;
    }

    public Integer getCruiserCount() {
        return cruiserCount;
    }

    public Integer getDestroyerCount() {
        return destroyerCount;
    }

    public Integer getSubmarineCount() {
        return submarineCount;
    }

    public Integer getTorpedoCount() {
        return torpedoCount;
    }

    public Boolean isRecoveryModeEnabled() {
        return isRecoveryModeEnabled;
    }

    public void applySettings(SettingsInput settingsInput) {
        var anotherSettings = settingsInput.toGameSettings();
        fieldWidth = anotherSettings.fieldWidth;
        fieldHeight = anotherSettings.fieldHeight;
        carrierCount = anotherSettings.carrierCount;
        battleshipCount = anotherSettings.battleshipCount;
        cruiserCount = anotherSettings.cruiserCount;
        destroyerCount = anotherSettings.destroyerCount;
        submarineCount = anotherSettings.submarineCount;
        torpedoCount = anotherSettings.torpedoCount;
        isRecoveryModeEnabled = anotherSettings.isRecoveryModeEnabled;
    }
}
