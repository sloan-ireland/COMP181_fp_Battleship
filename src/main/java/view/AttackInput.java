package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AttackCell;
import controller.Game;
import controller.AttackChecker;
import javafx.scene.control.Alert;
import model.PlayerOne;
import model.PlayerTwo;

import java.util.Optional;

public class AttackInput {
    private static int attackCount = 0;
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

        //add the buttons to the alert
        confirmAlert.getButtonTypes().setAll(attackButton, historyButton);

        //get the result of the alert
        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == attackButton) {
            return false;
        }
        else return result.isPresent() && result.get() == historyButton;
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
            AttackChecker.updateShipBoard(row, col);
            cell.setStyle("-fx-background-color: red;");
        } else {
            cell.setStyle("-fx-background-color: blue;");
        }
        BoardView.updateAttackBoard(cell);
        if (attackCount == 3) {
            //close the stage
            Stage currentStage = (Stage) cell.getScene().getWindow();
            currentStage.close();
            Game.nextTurn();
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
