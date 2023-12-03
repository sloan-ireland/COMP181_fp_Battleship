package controller;

import model.*;
import view.ShipInput;

import java.util.List;
public class InitializeGame {
    public void initializePlayerOne(String name, Ship[] ships) {

        //Game.player1.getShipBoard().setShips(ships);
    }

    public static void initializeBoard() {

        //do the shipboard
        ShipBoard shipBoard = new ShipBoard();
        //cycle through the ships and add them to the board
        for (Ship ship : PlayerOne.getShipBoard().getShips()) {
            for (int[] coord : ship.getCoordinates()) {
                shipBoard.getShipBoard()[coord[0]][coord[1]].setOccupantShip(ship);
            }
        }
        PlayerOne.setShipBoard(shipBoard);
        
    }




}
