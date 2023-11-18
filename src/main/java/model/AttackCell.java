package model;

public class AttackCell extends Cell {
    boolean shipHit;
    boolean shipSunk;
    int roundSinceHit;

    public AttackCell(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.shipHit = false;
        this.shipSunk = false;
        this.roundSinceHit = 0;
    }
}
