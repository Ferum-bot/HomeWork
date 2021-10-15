package battleship.models.statistics;

public record Statistics(

    Integer userScore,

    Integer generalShipsSunk,

    Integer missedHits,

    Integer carrierSunkCount,

    Integer battleshipSunkCount,

    Integer cruiserSunkCount,

    Integer destroyerSunkCount,

    Integer submarineSunkCount
) {
}
