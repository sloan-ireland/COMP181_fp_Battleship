package model;

public class AttackCell extends Cell {
    boolean shipHit;
    boolean shipSunk;
    int roundSinceHit;

    String colorOfCell;


    public AttackCell(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.shipHit = false;
        this.shipSunk = false;
        this.roundSinceHit = 0;
        this.colorOfCell = "default";
    }

    public boolean getShipHit() {
        return shipHit;
    }

    public void setShipHit(boolean shipHit) {
        this.shipHit = shipHit;
    }

    public boolean getShipSunk() {
        return shipSunk;
    }

    public void setShipSunk(boolean shipSunk) {
        this.shipSunk = shipSunk;
    }

    public int getRoundSinceHit() {
        return roundSinceHit;
    }

    public void setRoundSinceHit(int roundSinceHit) {
        this.roundSinceHit = roundSinceHit;
    }

    public void incrementRoundSinceHit() {
        this.roundSinceHit++;
    }

    public void resetRoundSinceHit() {
        this.roundSinceHit = 0;
    }

    public String getColorOfCell() {
        return colorOfCell;
    }

    public void setColorOfCell(String colorOfCell) {
        this.colorOfCell = colorOfCell;
    }



}
