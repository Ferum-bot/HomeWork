package battleship.models.field.coordinate;

import battleship.models.ship.ShipCoordinate;

import java.util.Objects;

public class FieldCoordinate {

    private final Integer x;
    private final Integer y;

    private CoordinateStatus status = CoordinateStatus.NOT_SHOOT;

    public FieldCoordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldCoordinate that = (FieldCoordinate) o;
        return x.equals(that.x) && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public ShipCoordinate toShipCoordinate() {
        return new ShipCoordinate(x, y);
    }

    public void setStatus(CoordinateStatus newStatus) {
        status = newStatus;
    }

    public CoordinateStatus getStatus() {
        return status;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
