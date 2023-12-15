package model;

//import all classes from java.Model package


import javafx.scene.control.Button;

public class Board {
    public static final int BOARD_SIZE = 20;
    public static final char EMPTY = '-';
    public static final char SHIP = '$';
    public static final char HIT = '*';
    public static final char MISS = 'O';

    private Cell[][] board;


    public Board() {

    }


}
