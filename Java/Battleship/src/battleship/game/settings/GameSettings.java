package battleship.game.settings;

public record GameSettings(

    Integer fieldWidth,

    Integer fieldHeight,

    Integer carrierCount,

    Integer battleshipCount,

    Integer cruiserCount,

    Integer destroyerCount,

    Integer submarineCount,

    Integer torpedoCount,

    Boolean isRecoveryModeEnabled
) {
}
