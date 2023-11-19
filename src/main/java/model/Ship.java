package model;

public abstract class Ship {

    protected int health = 100;
    protected int length;
    public ShipCell[] shipCells;
    protected boolean isSunk;
    public String name;

    public Ship() {

    }
    // Getters and Setters
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ShipCell[] getShipCells() {
        return shipCells;
    }

    public void setShipCells(ShipCell[] shipCells) {
        this.shipCells = shipCells;
    }

    public String getName() {
        return name;
    }
}
