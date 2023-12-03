package controller;
import model.*;
public class Game {
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


}
