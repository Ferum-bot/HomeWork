package battleship.models.info;

import battleship.models.statistics.Statistics;

public class InformationHolder {

    private Integer userScore = 0;

    private Integer missedHits = 0;

    private Integer carrierSunkCount = 0;
    private Integer battleshipSunkCount = 0;
    private Integer cruiserSunkCount = 0;
    private Integer destroyerSunkCount = 0;
    private Integer submarineSunkCount = 0;

    private Integer availableTorpedoCount = 0;

    public void increaseScore() {
        userScore++;
    }

    public void increaseMissedHits() {
        missedHits++;
    }

    public void increaseCarrierSunkCount() {
        carrierSunkCount++;
    }

    public void increaseBattleshipSunkCount() {
        battleshipSunkCount++;
    }

    public void increaseCruiserSunkCount() {
        cruiserSunkCount++;
    }

    public void increaseDestroyerSunkCount() {
        destroyerSunkCount++;
    }

    public void increaseSubmarineSunkCount() {
        submarineSunkCount++;
    }

    public Integer getGeneralShipsSunkCount() {
        return carrierSunkCount + battleshipSunkCount + cruiserSunkCount +
                destroyerSunkCount + submarineSunkCount;
    }

    public void setTorpedoCount(Integer count) {
        availableTorpedoCount = count;
    }

    public void decreaseTorpedoCount() {
        availableTorpedoCount--;
    }

    public Boolean isTorpedoAvailable() {
        return availableTorpedoCount != null;
    }

    public Statistics toStatistics() {
        return new Statistics(
            userScore, getGeneralShipsSunkCount(), missedHits, carrierSunkCount,
            battleshipSunkCount, cruiserSunkCount, destroyerSunkCount, submarineSunkCount
        );
    }
}
