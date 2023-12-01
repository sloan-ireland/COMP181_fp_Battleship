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

public class ShipInput {

    private static Label playerNameLabel;
    private static GridPane gameBoard;
    private static VBox shipList;
    private static Ship[] ships;

    private static String playerName;
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
            playerName = playerNameField.getText();
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
        playerNameLabel = new Label("Player: " + playerName);
        PlayerOne.setName(playerName);
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
                    if (areCellsInLine(selectedButtons)) {
                        confirmShipPlacement();
                    } else {
                        // Reset if cells are not in line
                        for (Button button : selectedButtons) {
                            button.setStyle("");
                        }
                        selectedButtons.clear();
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Invalid Placement");
                        errorAlert.setHeaderText("Invalid Ship Placement");
                        errorAlert.setContentText("Please ensure all cells are in a line.");
                        errorAlert.showAndWait();
                    }
                }
            }
        }
    }

    private static boolean areCellsInLine(List<Button> buttons) {
        // Check if all cells are in a line
        boolean areInLine = true;
        Button firstButton = buttons.get(0);
        Button secondButton = buttons.get(1);
        // Check if all cells are in the same row
        if (firstButton.getLayoutY() == secondButton.getLayoutY()) {
            // Check if all cells are in the same column
            for (int i = 2; i < buttons.size(); i++) {
                if (buttons.get(i).getLayoutY() != firstButton.getLayoutY()) {
                    areInLine = false;
                    break;
                }
            }
        } else if (firstButton.getLayoutX() == secondButton.getLayoutX()) {
            // Check if all cells are in the same row
            for (int i = 2; i < buttons.size(); i++) {
                if (buttons.get(i).getLayoutX() != firstButton.getLayoutX()) {
                    areInLine = false;
                    break;
                }
            }
        } else {
            areInLine = false;
        }
        return areInLine;
    }
    private static String getColorForShip(Ship ship) {
        if (ship instanceof model.Carrier) {
            return "#0077be"; // Dark Blue
        } else if (ship instanceof model.Battleship) {
            return "#228b22"; // Forest Green
        } else if (ship instanceof model.Cruiser) {
            return "#b8860b"; // Dark Goldenrod
        } else if (ship instanceof model.Submarine) {
            return "#cd5c5c"; // Indian Red
        } else if (ship instanceof model.Destroyer) {
            return "#8a2be2"; // Blue Violet
        }
        return "#808080"; // Default Grey
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
                String shipColor = getColorForShip(ships[currentShipIndex]);
                for (Button button : selectedButtons) {
                    button.setStyle("-fx-background-color: " + shipColor + ";");
                    button.setDisable(true); // Disable the button after placing the ship
                }
                shipLabels.get(currentShipIndex).setStyle("-fx-border-color: black; -fx-padding: 5px; -fx-text-fill: grey;");
                shipLabels.get(currentShipIndex).setDisable(true);
            } else {
                for (Button button : selectedButtons) {
                    button.setStyle(""); // Reset style if cancelled
                }
            }
            selectedButtons.clear();
            isPlacingShip = false;
        });
    }


    // ... [other methods if any]
}
