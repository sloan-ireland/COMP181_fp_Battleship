package controller;
import javafx.scene.Scene;
import model.*;
import view.BoardView;
import view.MovementInput;
import view.ShipInput;
import java.net.URL;



public class Game {

    private static final String CSS_STYLES =
            "/* Your CSS styles here */\n" +
                    "button {\n" +
                    "    -fx-background-color: #4CAF50;\n" +
                    "    -fx-text-fill: white;\n" +
                    "}\n" +
                    "/* Add more styles as needed */";

    public static int playerNumber;

    //goes through player 1
    public static void startSetup() {
        playerNumber = 1;
        ShipInput.displaySetupWindow();
        PlayerOne.setAttackBoard(new AttackBoard());
        PlayerTwo.setAttackBoard(new AttackBoard());
    }

    //called from ShipInput. Sets the ships for the player
    //if player 1 is done, then it will call the setup for player 2
    //if player 2 is done, then it will call the movement input for player 1
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
    }


    public static void nextTurn() {
        if (Game.playerNumber == 1) {
            Game.playerNumber = 2;
            MovementInput.setupScene();
        }
        else {
            Game.playerNumber = 1;
            MovementInput.setupScene();
        }
    }

    public static void applyCommonStyles(Scene scene) {
        scene.getStylesheets().add("data:text/css," + CSS_STYLES);
    }


}
