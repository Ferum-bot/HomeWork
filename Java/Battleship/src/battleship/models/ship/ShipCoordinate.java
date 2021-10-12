package battleship.models.ship;

public class ShipCoordinate {

    private Integer x;
    private Integer y;

    private Boolean isHit;

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
