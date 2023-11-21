package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import model.*;

public class GameWindow {

    private static Label playerNameLabel;
    private static GridPane gameBoard;
    private static VBox shipList;
    private static Ship[] ships;
    private static Player currentPlayer;

    public static void displaySetupWindow() {
        Stage setupStage = new Stage();
        setupStage.setTitle("Setup Your Board");

        // Layout for player name input
        BorderPane nameInputLayout = new BorderPane();
        TextField playerNameField = new TextField();
        playerNameField.setPromptText("Enter your name");
        Button submitButton = new Button("Submit");
        VBox inputBox = new VBox(10, playerNameField, submitButton);
        inputBox.setAlignment(Pos.CENTER);
        nameInputLayout.setCenter(inputBox);

        // Scene and Stage for name input
        Scene nameInputScene = new Scene(nameInputLayout, 400, 300);
        setupStage.setScene(nameInputScene);
        setupStage.show();

        // Event handling for submit button
        submitButton.setOnAction(e -> {
            String playerName = playerNameField.getText();
            currentPlayer = new Player(playerName);
            setupStage.close();
            setupBoardAndShips();
        });
    }

    private static void setupBoardAndShips() {
        Stage boardStage = new Stage();
        boardStage.setTitle("Place Your Ships");

        // Create a new BorderPane for the new scene
        BorderPane root = new BorderPane();

        // Stage 2: Display Player Name and Board
        playerNameLabel = new Label("Player: " + currentPlayer.getName());
        root.setTop(playerNameLabel);

        gameBoard = createBoard();
        root.setCenter(gameBoard);

        shipList = new VBox(10);
        shipList.setAlignment(Pos.CENTER);
        root.setLeft(shipList);

        ships = new Ship[]{new model.Carrier(), new model.Battleship(), new model.Cruiser(), new model.Submarine(), new model.Destroyer()};
        for (Ship ship : ships) {
            Label shipLabel = new Label(ship.getName() + " (Length: " + ship.getLength() + ")");
            shipList.getChildren().add(shipLabel);
        }

        Scene scene = new Scene(root, 800, 600);
        boardStage.setScene(scene);
        boardStage.show();

        placeShipsSequentially();
    }

    private static GridPane createBoard() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        // Add buttons to represent cells
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Button cell = new Button();
                cell.setPrefSize(30, 30);
                grid.add(cell, col, row);
                int finalRow = row;
                int finalCol = col;
                cell.setOnAction(e -> handleCellClick(finalRow, finalCol));
            }
        }
        return grid;
    }

    private static void handleCellClick(int row, int col) {
        // Handle ship placement logic
        System.out.println("Cell clicked: Row " + row + ", Col " + col);
    }

    private static void placeShipsSequentially() {
        // Logic to handle sequential ship placement
        // Update UI and handle ship placement one by one
    }
}
