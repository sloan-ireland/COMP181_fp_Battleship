package model;

//import all classes from java.Model package


public class Board {
    public static final int BOARD_SIZE = 20;
    public static final char EMPTY = '#';
    public static final char SHIP = '$';
    public static final char HIT = '*';
    public static final char MISS = 'O';

    private Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];


    public Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; i++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

}
