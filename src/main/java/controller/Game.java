package controller;
import model.*;
import view.BoardView;
import view.MovementInput;
import view.ShipInput;


public class Game {
    public static int playerNumber;

    //goes through player 1
    public static void startSetup() {
        playerNumber = 1;
        ShipInput.displaySetupWindow();
        PlayerOne.setAttackBoard(new AttackBoard());
        PlayerTwo.setAttackBoard(new AttackBoard());
    }

    //called from ShipInput. Sets the ships for the player
    public static void endSetup(Ship[] ships) {
        if (Game.playerNumber == 1) {
            PlayerOne.getShipBoard().setShips(ships);
            BoardView.initializePlayerOneShipBoard();
            Game.playerNumber = 2;
            ShipInput.displaySetupWindow();
        }
        else {
            PlayerTwo.getShipBoard().setShips(ships);
            BoardView.initializePlayerTwoShipBoard();
            Game.playerNumber = 1;
            MovementInput.setupScene();
        }
        //MovementInput.displayMovementWindow();
        //Game.printOutShipCoords();
        if (Game.playerNumber == 1) {

        }
        else {

        }

    }

    public static void nextTurn() {
    }

}
