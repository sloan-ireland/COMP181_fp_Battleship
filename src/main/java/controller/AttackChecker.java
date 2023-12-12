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

    public static void updateShipBoard(int x, int y) {
        if (Game.playerNumber == 1) {
            PlayerTwo.getShipBoard().getShipBoard()[x][y].setIsHit(true);
            PlayerTwo.getAttackBoard().getAttackBoard()[x][y].setRoundSinceHit(0);

        } else {
            PlayerOne.getShipBoard().getShipBoard()[x][y].setIsHit(true);
            PlayerOne.getAttackBoard().getAttackBoard()[x][y].setRoundSinceHit(0);
        }
    }
}
