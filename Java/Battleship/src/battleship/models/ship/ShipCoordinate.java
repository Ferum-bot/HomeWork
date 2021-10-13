package battleship.models.ship;

import battleship.models.field.coordinate.FieldCoordinate;

import java.util.Objects;

public class ShipCoordinate {

    private Integer x;
    private Integer y;

    private Boolean isHit;

    public ShipCoordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public FieldCoordinate toFieldCoordinate() {
        return new FieldCoordinate(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipCoordinate that = (ShipCoordinate) o;
        return x.equals(that.x) && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void setIsHit(Boolean isHit) {
        this.isHit = isHit;
    }

    public Boolean getIsHit() {
        return isHit;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
