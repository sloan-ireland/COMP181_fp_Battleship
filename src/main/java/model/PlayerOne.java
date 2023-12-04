package model;

public class PlayerOne {
    private static String name;
    private static ShipBoard shipBoard = new ShipBoard();
    private static AttackBoard attackBoard;

    public PlayerOne() {

    }

    // Getters and Setters (static)
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        PlayerOne.name = name;
    }

    public static ShipBoard getShipBoard() {
        return shipBoard;
    }

    public static void setShipBoard(ShipBoard shipBoard) {
        PlayerOne.shipBoard = shipBoard;
    }

    public static AttackBoard getAttackBoard() {
        return attackBoard;
    }

    public static void setAttackBoard(AttackBoard attackBoard) {
        PlayerOne.attackBoard = attackBoard;
    }


}

