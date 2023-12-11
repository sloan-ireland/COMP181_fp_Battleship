package controller;

import model.PlayerOne;
import model.Ship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MovementChecker {
    //given the
    public static boolean checkMovement(ArrayList<int[]> startPos, ArrayList<int[]> endPos, Ship ship) {
        if (startPos.size() != endPos.size()) {
            return false;
        }

        // Convert startPos to a set for easier comparison
        Set<String> startPosSet = new HashSet<>();
        for (int[] coord : startPos) {
            startPosSet.add(coord[0] + "," + coord[1]);
        }

        // Check to make sure the ship is in bounds and not overlapping with other ships
        for (int[] coord : endPos) {
            String posKey = coord[0] + "," + coord[1];

            // Check bounds
            if (coord[0] < 0 || coord[0] > 9 || coord[1] < 0 || coord[1] > 9) {
                return false;
            }

            // Check for ship overlap, excluding the ship's current position
            if (!startPosSet.contains(posKey) && PlayerOne.getShipBoard().getShipBoard()[coord[0]][coord[1]].getOccupantShip() != null) {
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
                case "up":
                    newCoord[0] = coord[0];
                    newCoord[1] = coord[1]-1;
                    break;
                case "down":
                    newCoord[0] = coord[0];
                    newCoord[1] = coord[1]+1;
                    break;
                case "left":
                    newCoord[0] = coord[0]-1;
                    newCoord[1] = coord[1];
                    break;
                case "right":
                    newCoord[0] = coord[0]+1;
                    newCoord[1] = coord[1];
                    break;
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
