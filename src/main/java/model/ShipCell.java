package model;

public class ShipCell extends Cell {
    private Ship occupantShip;
    private boolean isHit;

    int index;
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

    public boolean isShipPresent() {
        return occupantShip != null;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }
    
    public int getindex() {
        return index;
    }
    
    public void setindex(int index) {
        this.index = index;
    }
}
