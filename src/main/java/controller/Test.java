package controller;

import model.PlayerOne;
import model.ShipBoard;
import model.ShipCell;

public class Test {
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

    //method that opens a stage and displays the shipboard of both players with labels
    //that show the name of the ship and the coords of the ship
    public static void displayShipBoard() {
        //code the emthod
        
    }
}
