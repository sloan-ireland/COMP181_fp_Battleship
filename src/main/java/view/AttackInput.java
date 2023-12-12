package view;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.AttackCell;
import model.PlayerOne;
import model.PlayerTwo;
import controller.Game;
import controller.AttackChecker;
import javafx.scene.control.Alert;

import java.util.Optional;

public class AttackInput {
    private static int attackCount = 0;
    public static void setupAttackScreen() {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);



        //cfreate an event handler for the attack board
        EventHandler attackBoardClick = event -> {
            int row = GridPane.getRowIndex((Button) event.getSource());
            int col = GridPane.getColumnIndex((Button) event.getSource());
            if (confirmAttack(row, col)) {
                handleAttack((Button) event.getSource());
            }
        };

        // Determine which player's attack board to display based on the current turn
        GridPane attackBoard;
        if (Game.playerNumber == 1) {
            attackBoard = BoardView.playerOneAttackBoard;
            BoardView.setPlayerOneAttackBoardAction(attackBoardClick);
        } else {
            attackBoard = BoardView.playerTwoAttackBoard;
            BoardView.setPlayerTwoAttackBoardAction(attackBoardClick);
        }

        root.setCenter(attackBoard);
        stage.setScene(scene);
        stage.setTitle("Player " + Game.playerNumber + ": Attack Phase");
        stage.show();
    }


    private static boolean confirmAttack(int row, int col) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Attack");
        confirmAlert.setHeaderText("Confirm Your Attack");
        confirmAlert.setContentText("Do you want to attack cell (" + row + ", " + col + ")?");

        Optional<ButtonType> result = confirmAlert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    private static void handleAttack(Button cell) {
        attackCount++;
        int row = GridPane.getRowIndex(cell);
        int col = GridPane.getColumnIndex(cell);

        // Perform the attack logi

        // Update the cell style based on whether it's a hit or miss
        if (AttackChecker.checkAttack(row, col)) {
            cell.setStyle("-fx-background-color: red;");
            AttackChecker.updateShipBoard(row, col);
        } else {
            cell.setStyle("-fx-background-color: blue;");
        }
        if (attackCount == 3) {
            //endAttack();
        }
    }

    private static void showCellHistory(AttackCell cell) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cell History");
        alert.setHeaderText("History for Cell (" + cell.getXCoord() + ", " + cell.getYCoord() + ")");

        String history = "Hit: " + cell.getShipHit() + "\n" +
                "Sunk: " + cell.getShipSunk() + "\n" +
                "Rounds Since Hit: " + cell.getRoundSinceHit();
        alert.setContentText(history);

        alert.showAndWait();
    }


    private static void endAttack() {
        // Switch to the next player's turn
        Game.nextTurn();
        //Stage currentStage = (Stage) cell.getScene().getWindow();
        //currentStage.close();

        // Open the next player's attack screen
        setupAttackScreen();
    }
}
