package controller;

import model.PlayerOne;
import model.PlayerTwo;

public class AttackChecker {
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
}
