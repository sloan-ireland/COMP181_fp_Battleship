package model;

public class ShipCell extends Cell {
    private Ship occupantShip;
    private boolean isHit;

    public ShipCell(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.occupantShip = null;
        this.isHit = false;
    }

    public Ship getOccupantShip() {
        return occupantShip;
    }

    public void setOccupantShip(Ship occupantShip) {
        this.occupantShip = occupantShip;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }
}
