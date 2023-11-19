package view;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import model.Ship; // Import your Ship class

public class GameWindow {

    public static void displaySetupWindow() {
        Stage setupStage = new Stage();
        setupStage.setTitle("Setup Your Board");

        // Layout
        BorderPane root = new BorderPane();

        // Player name input
        TextField playerNameField = new TextField();
        playerNameField.setPromptText("Enter your name");
        Button submitButton = new Button("Submit");
        VBox inputBox = new VBox(10, playerNameField, submitButton);
        inputBox.setAlignment(Pos.CENTER);

        // Game board
        GridPane gameBoard = createBoard();
        root.setCenter(gameBoard);

        // Add input box to layout
        root.setTop(inputBox);

        // Ship placement UI
        VBox shipList = new VBox(10);
        shipList.setAlignment(Pos.CENTER);

        // Assuming you have specific ship classes like Battleship, Cruiser, etc.
        Ship[] ships = {/* Instantiate your ship objects here */};

        for (Ship ship : ships) {
            Label shipLabel = new Label(ship.getName() + " (Length: " + ship.getLength() + ")");
            TextField shipInput = new TextField();
            shipInput.setPromptText("Enter cells for " + ship.getName());
            Button placeShipButton = new Button("Place " + ship.getName());

            placeShipButton.setOnAction(e -> {
                String input = shipInput.getText();
                // TODO: Validate and place ship logic
                // TODO: Show confirmation popup
                showConfirmationPopup(ship.getName(), input);
            });

            shipList.getChildren().addAll(shipLabel, shipInput, placeShipButton);
        }

        root.setBottom(shipList);

        // Scene and Stage
        Scene scene = new Scene(root, 800, 600);
        setupStage.setScene(scene);
        setupStage.show();

        // Event handling for submit button
        submitButton.setOnAction(e -> {
            String playerName = playerNameField.getText();
            // TODO: Handle player name and board setup
        });
    }

    private static GridPane createBoard() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        // Add buttons to represent cells
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                final int finalRow = row;
                final int finalCol = col;
                Button cell = new Button();
                cell.setPrefSize(30, 30);
                grid.add(cell, col, row);
                cell.setOnAction(e -> handleCellClick(finalRow, finalCol));
            }
        }
        return grid;
    }

    private static void handleCellClick(int row, int col) {
        // TODO: Handle ship placement logic
        System.out.println("Cell clicked: Row " + row + ", Col " + col);
    }

    private static void showConfirmationPopup(String shipName, String input) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Ship Placement");
        alert.setHeaderText("Placement for " + shipName);
        alert.setContentText("Coordinates: " + input);

        // TODO: Add logic to handle user confirmation

        alert.showAndWait();
    }
}
