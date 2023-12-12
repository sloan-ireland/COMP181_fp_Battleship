package controller;
import model.*;
import view.ShipInput;


public class Game {
    public static int playerNumber;

    public static void startSetup() {
        playerNumber = 1;
        ShipInput.displaySetupWindow();
    }

    public static void setUpPlayerTwo() {
        playerNumber = 2;
        ShipInput.displaySetupWindow();
    }
    public static void printOutShipCoords() {
        ShipBoard shipBoard = PlayerOne.getShipBoard();
        ShipCell[][] shipCells = shipBoard.getShipBoard();
        for (int i = 0; i < shipCells.length; i++) {
            for (int j = 0; j < shipCells[i].length; j++) {
                if (shipCells[i][j].getOccupantShip() != null) {
                    System.out.println(shipCells[i][j].getOccupantShip().getName() + " " + shipCells[i][j].getXCoord() + " " + shipCells[i][j].getYCoord());
                }
            }
        }
    }

    //print out the coords of all cell that have a ship
    public static void printOutShipCoords(ShipBoard shipBoard) {
        ShipCell[][] shipCells = shipBoard.getShipBoard();
        for (int i = 0; i < shipCells.length; i++) {
            for (int j = 0; j < shipCells[i].length; j++) {
                if (shipCells[i][j].getOccupantShip() != null) {
                    System.out.println(shipCells[i][j].getOccupantShip().getName() + " " + shipCells[i][j].getXCoord() + " " + shipCells[i][j].getYCoord());
                }
            }
        }
    }


    public static void nextTurn() {
    }
}
