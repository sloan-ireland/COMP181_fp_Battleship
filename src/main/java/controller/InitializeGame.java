package controller;

import model.*;
import view.MovementInput;


public class InitializeGame {

    public static void initializePlayerOneShipBoard() {
        // Ensure PlayerOne's ShipBoard is initialized
        if (PlayerOne.getShipBoard() == null) {
            PlayerOne.setShipBoard(new ShipBoard());
        }
        ShipBoard shipBoard = PlayerOne.getShipBoard();
        // Check if ships are set for PlayerOne
        if (shipBoard.getShips() != null) {
            // Cycle through the ships and add them to the board
            for (Ship ship : shipBoard.getShips()) {
                if (ship.getCoordinates() != null) {
                    for (int[] coord : ship.getCoordinates()) {
                        shipBoard.getShipBoard()[coord[0]][coord[1]].setOccupantShip(ship);
                    }
                }
            }
        }

        // Update PlayerOne's ShipBoard
        PlayerOne.setShipBoard(shipBoard);
    }

    public static void initializePlayerTwoShipBoard() {
        // Ensure PlayerOne's ShipBoard is initialized
        if (PlayerTwo.getShipBoard() == null) {
            PlayerTwo.setShipBoard(new ShipBoard());
        }
        ShipBoard shipBoard = PlayerTwo.getShipBoard();
        // Check if ships are set for PlayerTwo
        if (shipBoard.getShips() != null) {
            // Cycle through the ships and add them to the board
            for (Ship ship : shipBoard.getShips()) {
                if (ship.getCoordinates() != null) {
                    for (int[] coord : ship.getCoordinates()) {
                        shipBoard.getShipBoard()[coord[0]][coord[1]].setOccupantShip(ship);
                    }
                }
            }
        }

        // Update PlayerTwo's ShipBoard
        PlayerTwo.setShipBoard(shipBoard);
    }

    public static void endSetup(Ship[] ships) {

        if (Game.playerNumber == 1) {
            PlayerOne.getShipBoard().setShips(ships);
            InitializeGame.initializePlayerOneShipBoard();
            Game.setUpPlayerTwo();
        }
        else {
            PlayerTwo.getShipBoard().setShips(ships);
            InitializeGame.initializePlayerTwoShipBoard();
            Game.playerNumber = 1;
            //priont out hello world
            System.out.println("Hello World");
            MovementInput.setupScene();
        }
        //MovementInput.displayMovementWindow();
        //Game.printOutShipCoords();
        if (Game.playerNumber == 1) {

        }
        else {

        }
        Game.printOutShipCoords(PlayerOne.getShipBoard());
    }

    // ... other methods ...
}
