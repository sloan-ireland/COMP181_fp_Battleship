package model;


public abstract class Cell {
    public int xCoord;
    public int yCoord;


    //accesor and mutator methods
    public int getXCoord() {
        return this.xCoord;
    }

    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getYCoord() {
        return this.yCoord;
    }

    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }

}
