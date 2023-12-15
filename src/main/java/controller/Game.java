package controller;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
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
    public static boolean shipSunken = false;
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
            if (shipSunken) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("For PlayerTwo: Ship Sunk");
                alert.setHeaderText("Your opponent sunk your " + AttackChecker.lastSunkShip + "!"
                        + "ship! It has been removed from the board.");
                alert.setContentText("It is now your turn.");
                Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                alert.showAndWait();
                //when ok is clicked, close the popup window
                if (okButton != null) {
                    okButton.fire();
                }
                shipSunken = false;
            }
            MovementInput.setupScene();
        }
        else {
            Game.playerNumber = 1;
            if (shipSunken) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("For PlayerOne: Ship Sunk");
                alert.setHeaderText("Your opponent sunk your " + AttackChecker.lastSunkShip + "!"
                        + "ship! It has been removed from the board.");
                alert.setContentText("It is now your turn.");
                Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                alert.showAndWait();
                //when ok is clicked, close the popup window
                if (okButton != null) {
                    okButton.fire();
                }
                shipSunken = false;
            }
                MovementInput.setupScene();
        }
    }

    public static void applyCommonStyles(Scene scene) {
        scene.getStylesheets().add("data:text/css," + CSS_STYLES);
    }


    public static boolean isGameOver() {
        if (Game.playerNumber == 1) {
            for (Ship ship : PlayerTwo.getShipBoard().getShips()) {
                if (ship.getHealth() > 0) {
                    return false;
                }
            }
        }
        else {
            for (Ship ship : PlayerOne.getShipBoard().getShips()) {
                if (ship.getHealth() > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void endGame() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Game Over");
        alert.setHeaderText("You sunk your opponent's last ship!");
        alert.setContentText("You won the game!");
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        alert.showAndWait();
        //when ok is clicked, close the popup window
        if (okButton != null) {
            okButton.fire();
            //close all windows
            System.exit(0);
        }

    }
}
