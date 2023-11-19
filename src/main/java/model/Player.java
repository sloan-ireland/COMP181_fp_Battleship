package model;

public class Player {
    private String name;
    private Board shipBoard;
    private Board attackBoard;

    public Player(String name) {
        this.name = name;
        this.shipBoard = new ShipBoard();
        this.attackBoard = new AttackBoard();

    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getShipBoard() {
        return shipBoard;
    }

    public Board getAttackBoard() {
        return attackBoard;
    }

    // Additional methods...
}

