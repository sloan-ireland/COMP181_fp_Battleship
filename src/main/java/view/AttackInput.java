package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AttackCell;
import controller.Game;
import controller.AttackChecker;
import model.PlayerOne;
import model.PlayerTwo;

import java.util.Optional;

public class AttackInput {
    private static int attackCount = 0;
    private static final String INSTRUCTION_LABEL_STYLE = "-fx-font-size: 14px; -fx-background-color: #f0f0f0; -fx-padding: 10px; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5;";
    public static void setupAttackScreen() {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);



        //cfreate an event handler for the attack board
        EventHandler attackBoardClick = event -> {
            int row = GridPane.getColumnIndex((Button) event.getSource()) - 1;
            int col = GridPane.getRowIndex((Button) event.getSource()) - 1;
            AttackCell cell;
            if (Game.playerNumber == 1) {
                cell = PlayerOne.getAttackBoard().getAttackBoard()[row][col];
            } else {
                cell = PlayerTwo.getAttackBoard().getAttackBoard()[row][col];
            }
            if (promptNextAction(row, col, cell)) {
                showCellHistory(cell);
            }
            else {
                if (confirmAttack(row, col)) {
                    handleAttack((Button) event.getSource(), row, col);
                }
            }
        };

        Label shipBoardLabel = new Label("Attack Board");
        shipBoardLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");
        shipBoardLabel.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(shipBoardLabel, Pos.CENTER);
        root.setTop(shipBoardLabel);

        // Instructions label
        Label instructionsLabel = new Label("Select a cell to attack or view its history.\n" +
                "You have three attacks.\n"+
                "Click the \"End Turn Now\" button when you are done attacking.\n"+
                "Hitting the same part of a ship \nwill not cause additional damage.\n" +
                        "Careful, your opponent can move their \nships between your attacks!");
        instructionsLabel.setStyle(INSTRUCTION_LABEL_STYLE);
        instructionsLabel.setWrapText(true); // Enable text wrapping
        instructionsLabel.setMaxWidth(300);
        instructionsLabel.setAlignment(Pos.CENTER);
        root.setLeft(instructionsLabel);
        BorderPane.setMargin(instructionsLabel, new Insets(10, 10, 10, 10));

        // Determine which player's attack board to display based on the current turn
        GridPane attackBoard;
        if (Game.playerNumber == 1) {
            attackCount = 0;
            BoardView.setPlayerOneAttackBoardAction(attackBoardClick);
            attackBoard = BoardView.getPlayerOneAttackBoard();
        } else {
            attackCount = 0;
            BoardView.setPlayerTwoAttackBoardAction(attackBoardClick);
            attackBoard = BoardView.getPlayerTwoAttackBoard();
        }
        root.setCenter(attackBoard);

        Button endTurn = new Button("End Turn Now");
        root.setBottom(endTurn);
        BorderPane.setAlignment(endTurn, Pos.CENTER);
        BorderPane.setMargin(endTurn, new Insets(10, 10, 10, 10));
        endTurn.setStyle("-fx-font-size: 14px; -fx-base: #4CAF50; -fx-text-fill: white; -fx-padding: 10px;");
        endTurn.setOnAction(event -> {
            //close the stage
            Stage currentStage = (Stage) endTurn.getScene().getWindow();
            currentStage.close();
            Game.nextTurn();
        });

        Game.applyCommonStyles(scene);
        stage.setScene(scene);
        stage.setTitle("Player " + Game.playerNumber + ": Attack Phase");
        stage.show();
    }

    //method creates a popup window asking the user if they want to attack the cell or see the history
    //returns true if the user wants to attack the cell
    //returns false if the user wants to see the history
    private static boolean promptNextAction(int row, int col, AttackCell cell) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.initModality(Modality.APPLICATION_MODAL);
        confirmAlert.setTitle("Choose Next Action");
        confirmAlert.setHeaderText("Choose Your Next Action");
        confirmAlert.setContentText("Do you want to attack cell(" + row + ", " + col + ") or see the attack history?");
        ButtonType attackButton = new ButtonType("Attack");
        ButtonType historyButton = new ButtonType("History");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        //add the buttons to the alert
        confirmAlert.getButtonTypes().setAll(attackButton, historyButton, cancelButton);

        //get the result of the alert
        Optional<ButtonType> result = confirmAlert.showAndWait();

        //if the user clicks attack, return false
        if (result.isPresent() && result.get() == attackButton) {
            return false;
        }
        //if the user clicks history, return true
        else if (result.isPresent() && result.get() == historyButton) {
            return true;
        }
        //if the user clicks cancel, close the alert and return false
        else {
            confirmAlert.close();
            return false;
        }
    }

    private static boolean confirmAttack(int row, int col) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.initModality(Modality.APPLICATION_MODAL);
        confirmAlert.setTitle("Confirm Attack");
        confirmAlert.setHeaderText("Confirm Your Attack");
        confirmAlert.setContentText("Do you want to attack cell (" + row + ", " + col + ")?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private static void handleAttack(Button cell, int row, int col) {
        attackCount++;
        // Perform the attack logic
        if (AttackChecker.checkAttack(row, col)) {
            //only apply damage if that cell has not been hit before
            if (!AttackChecker.checkIfCellHit(row, col)) {
                AttackChecker.applyDamage(row, col);
            }
            if (AttackChecker.shipIsSunk(row,col)) {
                if (Game.playerNumber == 1) {
                    PlayerOne.getAttackBoard().getAttackBoard()[row][col].setShipSunk(true);
                    PlayerTwo.getShipBoard().getShipBoard()[row][col].getOccupantShip().setIsSunk(true);
                }
                else {
                    PlayerTwo.getAttackBoard().getAttackBoard()[row][col].setShipSunk(true);
                    PlayerOne.getShipBoard().getShipBoard()[row][col].getOccupantShip().setIsSunk(true);
                }
                cell.setStyle("-fx-background-color: red;");
                //set all the cells of sunk ship to black
                AttackChecker.applySunkShipColor(row, col);
                Game.shipSunken = true;
                shipSunk();
            }
            else {
                AttackChecker.updateShipBoardifHit(row, col);
                cell.setStyle("-fx-background-color: red;");
            }
        } else {
            cell.setStyle("-fx-background-color: blue;");
            AttackChecker.updateShipBoardifMiss(row, col);
        }

        if (attackCount == 3) {
            //disable the attack board in this class
            if (Game.playerNumber == 1) {
                BoardView.disablePlayerOneAttackBoard();
            }
            else {
                BoardView.disablePlayerTwoAttackBoard();
            }

        }
    }

    private static void shipSunk() {
        AttackChecker.getSunkShipName();
        //set isSunk to true for the ship and occupantShip of the cell
        if (Game.playerNumber == 1) {
            PlayerTwo.getShipBoard().removeShip(AttackChecker.lastSunkShip);
        }
        else {
            PlayerOne.getShipBoard().removeShip(AttackChecker.lastSunkShip);
        }
        if (!Game.isGameOver()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ship Sunk");
            alert.setHeaderText("You sunk a ship!");
            alert.setContentText("You sunk your opponent's " + AttackChecker.lastSunkShip + "!");
            Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            alert.showAndWait();
            //when ok is clicked, close the popup window
            if (okButton != null) {
                okButton.fire();
            }
        }
        else {
            Game.endGame();
        }
    }

    private static void showCellHistory(AttackCell cell) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cell History");
        //close button
        alert.setHeaderText("History for Cell (" + cell.getXCoord() + ", " + cell.getYCoord() + ")");

        String history = "Hit: " + cell.getShipHit() + "\n" +
                "Sunk: " + cell.getShipSunk() + "\n" +
                "Rounds Since Hit: " + cell.getRoundSinceHit();
        alert.setContentText(history);

        alert.showAndWait();
    }

}
