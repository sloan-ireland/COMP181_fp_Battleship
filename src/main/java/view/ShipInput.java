package view;

import controller.Game;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import model.*;
import controller.InitializeGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;


public class ShipInput {

    private static VBox shipList;
    public static Ship[] ships;

    private static String playerName;
    private static int currentShipIndex = -1;
    private static List<Button> selectedButtons = new ArrayList<>();
    private static List<Label> shipLabels = new ArrayList<>();
    private static boolean isPlacingShip = false;
    
    

    public static void displaySetupWindow() {
        Stage setupStage = new Stage();
        if (Game.playerNumber == 1) {
            setupStage.setTitle("Player 1: Name Input");
        }
        else {
            resetClass();
            setupStage.setTitle("Player 2: Name Input");

        }

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
        if (Game.playerNumber == 1) {
            boardStage.setTitle("Player 1: Ship Setup");
        }
        else {
            boardStage.setTitle("Player 2: Ship Setup");
        }

        // Create a new BorderPane for the new scene
        BorderPane root = new BorderPane();

        // Stage 2: Display Player Name and Board
        Label playerNameLabel;
        if (Game.playerNumber == 1) {
            playerNameLabel = new Label("Player 1: " + playerName);
        }
        else {
            playerNameLabel = new Label("Player 2: " + playerName);
        }
        playerNameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: normal;");
        if (Game.playerNumber == 1) {
            PlayerOne.setName(playerName);
        }
        else {
            PlayerTwo.setName(playerName);
        }
        root.setTop(playerNameLabel);

        if (Game.playerNumber == 1) {
            BoardView.setPlayerOneBoardAction(e -> {
                int row = GridPane.getRowIndex((Button) e.getSource()) - 1;
                int col = GridPane.getColumnIndex((Button) e.getSource()) - 1;
                handleCellClick(row, col, (Button) e.getSource());
            });
            root.setCenter(BoardView.getPlayerOneBoard());
        }
        else {
            BoardView.setPlayerTwoBoardAction(e -> {
                int row = GridPane.getRowIndex((Button) e.getSource()) - 1;
                int col = GridPane.getColumnIndex((Button) e.getSource()) - 1;
                handleCellClick(row, col, (Button) e.getSource());
            });
            root.setCenter(BoardView.getPlayerTwoBoard());
        }
        


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
        if (buttons.size() <= 1) {
            return true; // Only one cell selected, no need to check further
        }

        // Sort buttons by row and then by column
        buttons.sort(Comparator.comparingInt(b -> GridPane.getRowIndex(b) * 10 + GridPane.getColumnIndex(b)));

        boolean sameRow = true, sameCol = true;
        int prevRow = GridPane.getRowIndex(buttons.get(0));
        int prevCol = GridPane.getColumnIndex(buttons.get(0));

        for (int i = 1; i < buttons.size(); i++) {
            int currentRow = GridPane.getRowIndex(buttons.get(i));
            int currentCol = GridPane.getColumnIndex(buttons.get(i));

            if (currentRow != prevRow) {
                sameRow = false;
            }
            if (currentCol != prevCol) {
                sameCol = false;
            }

            // Check for consecutive placement
            if ((sameRow && currentCol != prevCol + 1) || (sameCol && currentRow != prevRow + 1)) {
                return false; // Cells are not consecutively placed
            }

            prevRow = currentRow;
            prevCol = currentCol;
        }

        return sameRow || sameCol; // Cells are in a line if they are in the same row or column
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
                String shipColor = BoardView.getColorForShip(ships[currentShipIndex]);
                for (Button button : selectedButtons) {
                    button.setStyle("-fx-background-color: " + shipColor + ";");
                    button.setDisable(true); // Disable the button after placing the ship

                    // Store the coordinates in the ship's coordinate list
                    int row = GridPane.getRowIndex(button)-1;
                    int col = GridPane.getColumnIndex(button)-1;
                    ships[currentShipIndex].getCoordinates().add(new int[]{col, row});
                }
                shipLabels.get(currentShipIndex).setStyle("-fx-border-color: black; -fx-padding: 5px; -fx-text-fill: grey;");
                shipLabels.get(currentShipIndex).setDisable(true);

                // Check if all ships have been placed
                if (allShipsPlaced()) {
                    //System.out.println("All ships placed");
                    // Close the current stage
                    Stage currentStage = (Stage) shipList.getScene().getWindow();
                    currentStage.close();
                    InitializeGame.endSetup(ships);
                }
            } else {
                for (Button button : selectedButtons) {
                    button.setStyle(""); // Reset style if cancelled
                }
            }
            selectedButtons.clear();
            isPlacingShip = false;
        });
    }


    private static boolean allShipsPlaced() {
        for (Label label : shipLabels) {
            if (!label.isDisabled()) {
                return false; // If any ship label is not disabled, not all ships are placed
            }
        }
        return true; // All ship labels are disabled, meaning all ships are placed
    }

    private static void resetClass() {
        //reset all instance variables to defualt values or make them null if they didnt have a default value
        //make sure there is something in the instance variable before you set it to null
        //check if the instance variable is null before you set it to null\
        if (shipList != null) {
            shipList.getChildren().clear();
        }
        if (ships != null) {
            ships = null;
        }
        if (playerName != null) {
            playerName = null;
        }
        currentShipIndex = -1;
        if (selectedButtons != null) {
            selectedButtons.clear();
        }
        if (shipLabels != null) {
            shipLabels.clear();
        }
        isPlacingShip = false;


    }

}
