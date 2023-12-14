package controller;
import model.*;
import view.BoardView;
import view.MovementInput;
import view.ShipInput;


public class Game {
    public static int playerNumber;

    //goes through player 1
    public static void startSetup() {
        System.out.println("start setup begins");
        playerNumber = 1;
        ShipInput.displaySetupWindow();
        PlayerOne.setAttackBoard(new AttackBoard());
        PlayerTwo.setAttackBoard(new AttackBoard());
        System.out.println("start setup ends");
    }

    //called from ShipInput. Sets the ships for the player
    public static void endSetup(Ship[] ships) {
        System.out.println("end setup begins");
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
            //priont out hello worldSystem.out.println("Hello World");
            MovementInput.setupScene();
        }
        //MovementInput.displayMovementWindow();
        //Game.printOutShipCoords();
        if (Game.playerNumber == 1) {

        }
        else {

        }
        System.out.println("player 1");
        Test.printOutShipCoords(PlayerOne.getShipBoard());
        System.out.println("2nd time");
        Test.printOutShipCoords();
        System.out.println("player 2");
        Test.printOutShipCoords(PlayerTwo.getShipBoard());
        System.out.println("2nd time");
        Test.printOutShipCoords();
        System.out.println("end setup ends");
    }

    public static void nextTurn() {
    }

}
