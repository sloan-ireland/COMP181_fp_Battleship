package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class GameWindow {

    private static Label playerNameLabel;
    private static GridPane gameBoard;
    private static VBox shipList;
    private static Ship[] ships;
    private static Player currentPlayer;
    private static int currentShipIndex = -1;
    private static List<Button> selectedButtons = new ArrayList<>();
    private static List<Label> shipLabels = new ArrayList<>();
    private static boolean isPlacingShip = false;

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

        Label instructionLabel = new Label("Click on a ship to place it:\nOnce selected, click on the board to place the ship\nYou must place that ship before you can move on.");
        shipList.getChildren().add(instructionLabel);

        ships = new Ship[]{new model.Carrier(), new model.Battleship(), new model.Cruiser(), new model.Submarine(), new model.Destroyer()};
        for (Ship ship : ships) {
            Label shipLabel = new Label(ship.getName() + " (Length: " + ship.getLength() + ")");
            shipLabel.setStyle("-fx-border-color: black; -fx-padding: 5px;");
            shipLabel.setOnMouseClicked(e -> selectShipForPlacement(ship, shipLabel));
            shipList.getChildren().add(shipLabel);
            shipLabels.add(shipLabel);
        }

        Scene scene = new Scene(root, 800, 600);
        boardStage.setScene(scene);
        boardStage.show();
    }

    private static void selectShipForPlacement(Ship ship, Label shipLabel) {
        if (!isPlacingShip) {
            currentShipIndex = java.util.Arrays.asList(ships).indexOf(ship);
            for (Label label : shipLabels) {
                label.setStyle("-fx-border-color: black; -fx-padding: 5px;");
            }
            shipLabel.setStyle("-fx-border-color: black; -fx-padding: 5px; -fx-font-weight: bold;");
            isPlacingShip = true;
        }
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
                cell.setOnAction(e -> handleCellClick(finalRow, finalCol, cell));
            }
        }
        return grid;
    }

    private static void handleCellClick(int row, int col, Button cell) {
        if (isPlacingShip && currentShipIndex < ships.length && currentShipIndex >= 0) {
            Ship currentShip = ships[currentShipIndex];
            // Add cell to selected cells if it's not already selected
            if (!selectedButtons.contains(cell)) {
                selectedButtons.add(cell);
                cell.setStyle("-fx-background-color: grey;"); // Temporary color for selection

                // Check if the selected cells match the ship's length
                if (selectedButtons.size() == currentShip.getLength()) {
                    confirmShipPlacement();
                }
            }
        }
    }

    private static void confirmShipPlacement() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Ship Placement");
        confirmationAlert.setHeaderText("Place " + ships[currentShipIndex].getName() + "?");
        confirmationAlert.setContentText("Confirm the placement of your " + ships[currentShipIndex].getName());

        ButtonType confirmButton = new ButtonType("Confirm");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmationAlert.getButtonTypes().setAll(confirmButton, cancelButton);

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == confirmButton) {
                for (Button button : selectedButtons) {
                    button.setStyle("-fx-background-color: blue;"); // Final color for placed ship
                    button.setDisable(true); // Disable the button after placing the ship
                }
                selectedButtons.clear();
                shipLabels.get(currentShipIndex).setStyle("-fx-border-color: black; -fx-padding: 5px; -fx-text-fill: grey;");
                shipLabels.get(currentShipIndex).setDisable(true);
                isPlacingShip = false;
            } else {
                for (Button button : selectedButtons) {
                    button.setStyle(""); // Reset style if cancelled
                }
                selectedButtons.clear();
                isPlacingShip = false;
            }
        });
    }

    // ... [other methods if any]
}
//package view;
//
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import model.*;
//
//import java.util.*;
//
//public class GameWindow {
//
//    private static Label playerNameLabel;
//    private static GridPane gameBoard;
//    private static VBox shipList;
//    private static Ship[] ships;
//    private static Player currentPlayer;
//    private static int currentShipIndex = 0;
//    private static List<Button> selectedButtons = new ArrayList<>();
//    private static Map<Ship, Color> shipColors;
//
//    public static void displaySetupWindow() {
//        Stage setupStage = new Stage();
//        setupStage.setTitle("Setup Your Board");
//
//        // Layout for player name input
//        BorderPane nameInputLayout = new BorderPane();
//        TextField playerNameField = new TextField();
//        playerNameField.setPromptText("Enter your name");
//        Button submitButton = new Button("Submit");
//        VBox inputBox = new VBox(10, playerNameField, submitButton);
//        inputBox.setAlignment(Pos.CENTER);
//        nameInputLayout.setCenter(inputBox);
//
//        // Scene and Stage for name input
//        Scene nameInputScene = new Scene(nameInputLayout, 400, 300);
//        setupStage.setScene(nameInputScene);
//        setupStage.show();
//
//        // Event handling for submit button
//        submitButton.setOnAction(e -> {
//            String playerName = playerNameField.getText();
//            currentPlayer = new Player(playerName);
//            setupStage.close();
//            setupBoardAndShips();
//        });
//
//        // Initialize ship colors
//        initializeShipColors();
//    }
//
//    private static void initializeShipColors() {
//        shipColors = new HashMap<>();
//        shipColors.put(new model.Carrier(), Color.RED);
//        shipColors.put(new model.Battleship(), Color.GREEN);
//        shipColors.put(new model.Cruiser(), Color.BLUE);
//        shipColors.put(new model.Submarine(), Color.ORANGE);
//        shipColors.put(new model.Destroyer(), Color.PURPLE);
//    }
//
//    private static void setupBoardAndShips() {
//        Stage boardStage = new Stage();
//        boardStage.setTitle("Place Your Ships");
//
//        // Create a new BorderPane for the new scene
//        BorderPane root = new BorderPane();
//
//        // Stage 2: Display Player Name and Board
//        playerNameLabel = new Label("Player: " + currentPlayer.getName());
//        root.setTop(playerNameLabel);
//
//        gameBoard = createBoard();
//        root.setCenter(gameBoard);
//
//        shipList = new VBox(10);
//        shipList.setAlignment(Pos.CENTER);
//        root.setLeft(shipList);
//
//        ships = new Ship[]{new model.Carrier(), new model.Battleship(), new model.Cruiser(), new model.Submarine(), new model.Destroyer()};
//        for (Ship ship : ships) {
//            Label shipLabel = new Label(ship.getName() + " (Length: " + ship.getLength() + ")");
//            shipList.getChildren().add(shipLabel);
//        }
//
//        Scene scene = new Scene(root, 800, 600);
//        boardStage.setScene(scene);
//        boardStage.show();
//
//        placeShipsSequentially();
//    }
//
//    private static GridPane createBoard() {
//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        // Add buttons to represent cells
//        for (int row = 0; row < 10; row++) {
//            for (int col = 0; col < 10; col++) {
//                Button cell = new Button();
//                cell.setPrefSize(30, 30);
//                grid.add(cell, col, row);
//                int finalRow = row;
//                int finalCol = col;
//                cell.setOnAction(e -> handleCellClick(finalRow, finalCol, cell));
//            }
//        }
//        return grid;
//    }
//
//    private static void handleCellClick(int row, int col, Button cell) {
//        if (currentShipIndex < ships.length) {
//            Ship currentShip = ships[currentShipIndex];
//            if (isValidPlacement(cell, currentShip)) {
//                selectedButtons.add(cell);
//                cell.setStyle("-fx-background-color: " + toRgbString(shipColors.get(currentShip)) + ";");
//
//                if (selectedButtons.size() == currentShip.getLength()) {
//                    confirmShipPlacement();
//                }
//            } else {
//                // Invalid placement, show error and reset selection
//                showError("Invalid placement. Please place the ship again.");
//                resetShipSelection();
//            }
//        }
//    }
//
//    private static boolean isValidPlacement(Button cell, Ship ship) {
//        // Logic to check if the placement is valid (horizontal/vertical and within bounds)
//        // Return true if valid, false otherwise
//        return true;
//    }
//
//    private static void resetShipSelection() {
//        for (Button button : selectedButtons) {
//            button.setStyle("");
//        }
//        selectedButtons.clear();
//    }
//
//    private static void showError(String message) {
//        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//        errorAlert.setTitle("Placement Error");
//        errorAlert.setHeaderText(null);
//        errorAlert.setContentText(message);
//        errorAlert.showAndWait();
//    }
//
//    private static String toRgbString(Color color) {
//        if (color == null) {
//            return "null"; // or any default value you see fit
//        }
//        return String.format("#%02X%02X%02X",
//                (int)(color.getRed() * 255),
//                (int)(color.getGreen() * 255),
//                (int)(color.getBlue() * 255));
//    }
//
//
//    private static void placeShipsSequentially() {
//        if (currentShipIndex < ships.length) {
//            Ship currentShip = ships[currentShipIndex];
//            highlightCurrentShip(currentShip);
//            // Show instructions for placing the current ship
//        } else {
//            // All ships placed, finalize setup
//        }
//    }
//
//    private static void highlightCurrentShip(Ship ship) {
//        for (Node node : shipList.getChildren()) {
//            if (node instanceof Label) {
//                Label label = (Label) node;
//                if (label.getText().startsWith(ship.getName())) {
//                    label.setStyle("-fx-font-weight: bold; -fx-underline: true;");
//                } else {
//                    label.setStyle("");
//                }
//            }
//        }
//    }
//
//    private static void confirmShipPlacement() {
//        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
//        confirmationAlert.setTitle("Confirm Ship Placement");
//        confirmationAlert.setHeaderText("Place " + ships[currentShipIndex].getName() + "?");
//        confirmationAlert.setContentText("Confirm the placement of your " + ships[currentShipIndex].getName());
//
//        ButtonType confirmButton = new ButtonType("Confirm");
//        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
//        confirmationAlert.getButtonTypes().setAll(confirmButton, cancelButton);
//
//        confirmationAlert.showAndWait().ifPresent(response -> {
//            if (response == confirmButton) {
//                // Confirm placement
//                for (Button button : selectedButtons) {
//                    button.setStyle("-fx-background-color: " + toRgbString(shipColors.get(ships[currentShipIndex])) + ";"); // Final color for placed ship
//                    button.setDisable(true); // Disable the button to prevent re-selection
//                }
//                selectedButtons.clear();
//                currentShipIndex++;
//                if (currentShipIndex < ships.length) {
//                    placeShipsSequentially();
//                } else {
//                    // All ships placed, finalize setup
//                    // This can lead to the next stage of the game
//                }
//            } else {
//                // Cancel placement, reset selection
//                for (Button button : selectedButtons) {
//                    button.setStyle(""); // Reset button style
//                }
//                selectedButtons.clear();
//            }
//        });
//    }
//
//    // ... [other methods]
//}
