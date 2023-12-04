package controller;

import model.*;

public class InitializeGame {

    public static void initializeShipBoard() {
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

    // ... other methods ...
}
