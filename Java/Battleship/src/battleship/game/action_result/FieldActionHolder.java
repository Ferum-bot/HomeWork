package battleship.game.action_result;

import battleship.models.ship.Ship;

import java.util.Optional;

public record FieldActionHolder(

    HitResult hitResult,

    Optional<Ship> effectedShip
) {

    public static FieldActionHolder missedHit() {
        return new FieldActionHolder(HitResult.MISSED, Optional.empty());
    }

    public static FieldActionHolder shipSunk(Ship ship) {
        return new FieldActionHolder(HitResult.SUNK, Optional.of(ship));
    }

    public static FieldActionHolder shipHit(Ship ship) {
        return new FieldActionHolder(HitResult.HIT, Optional.of(ship));
    }
}
