package controller;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.PlayerOne;
import model.PlayerTwo;
import model.Ship;
import view.BoardView;

import static java.lang.Integer.parseInt;

public class AttackChecker {
    public static String lastSunkShip;
    //based on what Game.playerNumber is, check the other player's shipboard
    public static boolean checkAttack(int x, int y) {
        if (Game.playerNumber == 1) {
            return PlayerTwo.getShipBoard().getShipBoard()[x][y].isShipPresent();
        } else {
            return PlayerOne.getShipBoard().getShipBoard()[x][y].isShipPresent();
        }
    }

    public static void updateShipBoardifHit(int x, int y) {
        if (Game.playerNumber == 1) {
            PlayerTwo.getShipBoard().getShipBoard()[x][y].setIsHit(true);
            PlayerOne.getAttackBoard().getAttackBoard()[x][y].setRoundSinceHit(0);
            PlayerOne.getAttackBoard().getAttackBoard()[x][y].setShipHit(true);
            PlayerOne.getAttackBoard().getAttackBoard()[x][y].setColorOfCell("red");

        } else {
            PlayerOne.getShipBoard().getShipBoard()[x][y].setIsHit(true);
            PlayerTwo.getAttackBoard().getAttackBoard()[x][y].setRoundSinceHit(0);
            PlayerTwo.getAttackBoard().getAttackBoard()[x][y].setShipHit(true);
            PlayerTwo.getAttackBoard().getAttackBoard()[x][y].setColorOfCell("red");
        }
    }

    public static void updateShipBoardifMiss(int x, int y) {
        if (Game.playerNumber == 1) {
            PlayerTwo.getShipBoard().getShipBoard()[x][y].setIsHit(false);
            PlayerOne.getAttackBoard().getAttackBoard()[x][y].incrementRoundSinceHit();
            PlayerOne.getAttackBoard().getAttackBoard()[x][y].setShipHit(false);
            PlayerOne.getAttackBoard().getAttackBoard()[x][y].setColorOfCell("blue");

        } else {
            PlayerOne.getShipBoard().getShipBoard()[x][y].setIsHit(false);
            PlayerTwo.getAttackBoard().getAttackBoard()[x][y].incrementRoundSinceHit();
            PlayerTwo.getAttackBoard().getAttackBoard()[x][y].setShipHit(false);
            PlayerTwo.getAttackBoard().getAttackBoard()[x][y].setColorOfCell("blue");
        }
    }


    public static void applyDamage(int row, int col) {
        int index;
        if (Game.playerNumber == 1) {
            index = PlayerTwo.getShipBoard().getShipBoard()[row][col].getindex();
            System.out.println("index: " + index);
            String shipName = PlayerTwo.getShipBoard().getShipBoard()[row][col].getOccupantShip().getName();
            int damage = PlayerTwo.getShipBoard().getShip(shipName).getDamageByCell()[index];
            System.out.println("damage: " + damage);
            int newHealth = PlayerTwo.getShipBoard().getShip(shipName).getHealth() - damage;
            PlayerTwo.getShipBoard().getShip(shipName).setHealth(newHealth);


        } else {
            index = PlayerOne.getShipBoard().getShipBoard()[row][col].getindex();
            String shipName = PlayerOne.getShipBoard().getShipBoard()[row][col].getOccupantShip().getName();
            int newHealth = PlayerOne.getShipBoard().getShip(shipName).getHealth() - PlayerOne.getShipBoard().getShip(shipName).getDamageByCell()[index];
            PlayerOne.getShipBoard().getShip(shipName).setHealth(newHealth);
        }
    }

    public static boolean shipIsSunk() {
        if (Game.playerNumber == 1) {
            for (Ship ship : PlayerTwo.getShipBoard().getShips()) {
                if (ship.getHealth() == 0) {
                    return true;
                }
            }
        } else {
            for (Ship ship : PlayerOne.getShipBoard().getShips()) {
                if (ship.getHealth() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void getSunkShipName() {
        if (Game.playerNumber == 1) {
            for (Ship ship : PlayerTwo.getShipBoard().getShips()) {
                if (ship.getHealth() == 0) {
                    lastSunkShip = ship.getName();
                }
            }
        } else {
            for (Ship ship : PlayerOne.getShipBoard().getShips()) {
                if (ship.getHealth() == 0) {
                    lastSunkShip = ship.getName();
                }
            }
        }
    }
}
