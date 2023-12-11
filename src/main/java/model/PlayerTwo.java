package model;

public class PlayerTwo {
    private static String name;
    private static ShipBoard shipBoard = new ShipBoard();
    private static AttackBoard attackBoard;

    public PlayerTwo() {

    }

    // Getters and Setters (static)
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        PlayerTwo.name = name;
    }

    public static ShipBoard getShipBoard() {
        return shipBoard;
    }

    public static void setShipBoard(ShipBoard shipBoard) {
        PlayerTwo.shipBoard = shipBoard;
    }

    public static AttackBoard getAttackBoard() {
        return attackBoard;
    }

    public static void setAttackBoard(AttackBoard attackBoard) {
        PlayerTwo.attackBoard = attackBoard;
    }


}

