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

    public static GameSettings empty() {
        return new GameSettings(
            0, 0, 0, 0, 0, 0, 0, 0, false
        );
    }
}
