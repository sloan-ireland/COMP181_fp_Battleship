package model;
import java.util.ArrayList;

public abstract class Ship {

    protected int health = 100;
    protected int length;
    public ArrayList<int[]> coordinates = new ArrayList<>();
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

    public ArrayList<int[]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<int[]> coordinates) {
        this.coordinates = coordinates;
    }
    public String getName() {
        return name;
    }
}
