package controller;

import model.PlayerOne;
import model.Ship;

import java.util.ArrayList;

public class MovementChecker {
    //given the
    public static boolean checkMovement(ArrayList<int[]> startPos, ArrayList<int[]> endPos) {
        if (startPos.size() != endPos.size()) {
            return false;
        }
        //check to make sure the ship is in bounds
        for (int[] coord : endPos) {
            if (coord[0] < 0 || coord[0] > 9 || coord[1] < 0 || coord[1] > 9) {
                return false;
            }

        }
        //check to make sure there are no ships in the way. confirm this with the shipboard
        for (int[] coord : endPos) {
            if (PlayerOne.getShipBoard().getShipBoard()[coord[0]][coord[1]].getOccupantShip() != null) {
                return false;
            }
        }

        return true;
    }

    //given the starting coordinates of a ship and the direction it is moving, return the coordinates of the ship after the move

    public static ArrayList<int[]> coordsAfterMove(ArrayList<int[]> startPos, String direction) {
        ArrayList<int[]> endPos = new ArrayList<int[]>();
        for (int[] coord : startPos) {
            int[] newCoord = new int[2];
            switch (direction) {
                case "left" -> {
                    newCoord[0] = coord[0];
                    newCoord[1] = coord[1] - 1;
                }
                case "right" -> {
                    newCoord[0] = coord[0];
                    newCoord[1] = coord[1] + 1;
                }
                case "up" -> {
                    newCoord[0] = coord[0] - 1;
                    newCoord[1] = coord[1];
                }
                case "down" -> {
                    newCoord[0] = coord[0] + 1;
                    newCoord[1] = coord[1];
                }
            }
            endPos.add(newCoord);
        }
        return endPos;

    }

    //update the shipboard with the new coordinates of the ship
    public static void moveShip(Ship ship, ArrayList<int[]> newShipPosition) {
        //remove the ship from the old coordinates
        for (int[] coord : ship.getCoordinates()) {
            PlayerOne.getShipBoard().getShipBoard()[coord[0]][coord[1]].setOccupantShip(null);
        }
        //add the ship to the new coordinates
        for (int[] coord : newShipPosition) {
            PlayerOne.getShipBoard().getShipBoard()[coord[0]][coord[1]].setOccupantShip(ship);
        }
        //update the ship's coordinates
        ship.setCoordinates(newShipPosition);
    }
}
