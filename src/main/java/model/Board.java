package model;

//import all classes from java.Model package


public class Board {
    public static final int BOARD_SIZE = 20;
    public static final char EMPTY = '-';
    public static final char SHIP = '$';
    public static final char HIT = '*';
    public static final char MISS = 'O';

    private Cell[][] board;


    public Board() {

    }


    private Cell[][] board;
    private int shipCount; //track the number of ships

    public Board() {
        // Initialize board and shipCount
    }

    // Method to decrease ship count
    public void decreaseShipCount() {
        if (shipCount > 0) {
            shipCount--;
        }
    }

    // Method to check if all ships are destroyed
    public boolean areAllShipsDestroyed() {
        return shipCount == 0;
    }

}
