package model;

public abstract class Ship {

    private int health;
    private int length;
    public ShipCell[] shipCells;
    private boolean isSunk;
    private String name;

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
}
