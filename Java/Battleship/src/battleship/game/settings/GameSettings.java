package battleship.game.settings;

public record GameSettings(

    Boolean isTorpedoModeEnabled,

    Boolean isRecoveryModeEnabled,

    Integer fieldWidth,

    Integer fieldHeight,

    Integer carrierCount,

    Integer battleshipCount,

    Integer cruiserCount,

    Integer destroyerCount,

    Integer submarineCount
) {
}
