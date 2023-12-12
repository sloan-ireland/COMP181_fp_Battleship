package view;


import controller.Game;
import controller.MovementChecker;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.PlayerOne;
import model.PlayerTwo;
import model.Ship;
import java.util.List;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static controller.MovementChecker.coordsAfterMove;
import view.AttackInput;

public class MovementInput {
    static Stage stage;

    public static void setupScene() {
        // Create the stage
        stage = new Stage();
        // Create the root pane and set the scene
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Create a label for the "Name" section
        Label nameLabel;
        if (Game.playerNumber == 1) {
            nameLabel = new Label("Player: " + PlayerOne.getName());
        }
        else {
            nameLabel = new Label("Player: " + PlayerTwo.getName());
        }
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: normal;"); // Styling the label

        // Create a VBox for the ship selection menu and add the nameLabel to it
        TilePane menu = createShipSelectionMenu();
        menu.getChildren().add(0, nameLabel); // Add nameLabel at the top of the VBox

        // Set the ship selection menu to the left
        root.setLeft(menu);

        if (Game.playerNumber == 1) {
            // Set the ship board to the center
            root.setCenter(BoardView.getPlayerOneBoard());
        }
        else {
            // Set the ship board to the center
            root.setCenter(BoardView.getPlayerTwoBoard());
        }

        // Configure the stage
        stage.setScene(scene);
        if (Game.playerNumber == 1) {
            stage.setTitle("PlayerOne: Move a Ship");
        }
        else {
            stage.setTitle("PlayerTwo: Move a Ship");
        }

        // Show the stage
        stage.show();
    }

    private static TilePane createShipSelectionMenu() {
        // Create a TilePane for the ship selection menu
        TilePane menu = new TilePane(10, 10);
        menu.setStyle("-fx-padding: 10px;"); // Add padding to the TilePane
        menu.setPrefWidth(200); // Set the preferred width of the TilePane
        menu.setMinWidth(200); // Set the minimum width of the TilePane
        menu.setMaxWidth(200); // Set the maximum width of the TilePane

        // Create buttons for each ship
        Button carrierButton = new Button("Carrier");
        Button battleshipButton = new Button("Battleship");
        Button cruiserButton = new Button("Cruiser");
        Button submarineButton = new Button("Submarine");
        Button destroyerButton = new Button("Destroyer");

        // Code to style the buttons
        carrierButton.setStyle("-fx-font-size: 14px; -fx-base: #4d4d4d; -fx-padding: 10px; -fx-text-fill: white;");
        battleshipButton.setStyle("-fx-font-size: 14px; -fx-base: #4d4d4d; -fx-padding: 10px; -fx-text-fill: white;");
        cruiserButton.setStyle("-fx-font-size: 14px; -fx-base: #4d4d4d; -fx-padding: 10px; -fx-text-fill: white;");
        submarineButton.setStyle("-fx-font-size: 14px; -fx-base: #4d4d4d; -fx-padding: 10px; -fx-text-fill: white;");
        destroyerButton.setStyle("-fx-font-size: 14px; -fx-base: #4d4d4d; -fx-padding: 10px; -fx-text-fill: white;");

        // Create an event handler for the buttons
        EventHandler<ActionEvent> handler = event -> {
            // Disable all buttons while the method is running
            carrierButton.setDisable(true);
            battleshipButton.setDisable(true);
            cruiserButton.setDisable(true);
            submarineButton.setDisable(true);
            destroyerButton.setDisable(true);

            // Get the name of the ship from the button that was clicked
            String shipName = ((Button) event.getSource()).getText();
            if (Game.playerNumber == 1) {
                selectDirection(PlayerOne.getShipBoard().getShip(shipName));
            }
            else {
                selectDirection(PlayerTwo.getShipBoard().getShip(shipName));
            }


            // Enable all buttons after the method has finished running
            carrierButton.setDisable(false);
            battleshipButton.setDisable(false);
            cruiserButton.setDisable(false);
            submarineButton.setDisable(false);
            destroyerButton.setDisable(false);
        };

        // Set the event handler for each button
        carrierButton.setOnAction(handler);
        battleshipButton.setOnAction(handler);
        cruiserButton.setOnAction(handler);
        submarineButton.setOnAction(handler);
        destroyerButton.setOnAction(handler);

        // Add the ship buttons to the TilePane
        menu.getChildren().addAll(carrierButton, battleshipButton, cruiserButton, submarineButton, destroyerButton);

        return menu;
    }

    //create a new popup window with a set of buttons: left right up down for the user to select the direction of the ship
    public static void selectDirection(Ship ship) {
        Stage popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Select Direction");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20px; -fx-background-color: #f0f0f0;"); // Set padding and background color

        Label directionLabel = new Label("Select the direction to move the ship:");
        directionLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;"); // Styling the label
        layout.getChildren().add(directionLabel);

        // Create and style buttons for each direction
        Button leftButton = createStyledButton("Left", ship, "left");
        Button rightButton = createStyledButton("Right", ship, "right");
        Button upButton = createStyledButton("Up", ship, "up");
        Button downButton = createStyledButton("Down", ship, "down");

        layout.getChildren().addAll(leftButton, rightButton, upButton, downButton);

        Scene scene = new Scene(layout, 400, 400);
        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }

    private static Button createStyledButton(String text, Ship ship, String direction) {
        Button button = new Button(text);
        ArrayList<int[]> newShipPosition = coordsAfterMove(ship.getCoordinates(), direction);
        button.setDisable(!MovementChecker.checkMovement(ship.getCoordinates(), newShipPosition, ship));
        button.setOnAction(e -> {
            confirmMove(newShipPosition, ship);
            Stage stage = (Stage) button.getScene().getWindow();
            stage.close();
        });
        button.setStyle("-fx-font-size: 14px; -fx-base: #4d4d4d; -fx-text-fill: white; -fx-padding: 10px;");
        return button;
    }


    public static void confirmMove(ArrayList<int[]> newShipPosition, Ship ship) {
        Stage popupWindow = new Stage();
        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("Confirm Movement");

        // Increase the size of the popup window
        popupWindow.setWidth(500);
        popupWindow.setHeight(500);

        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-padding: 20px; -fx-background-color: #f0f0f0;"); // Set padding and background color
        Label confirmLabel = new Label("Confirm the new position of the ship:");
        confirmLabel.getStyleClass().add("label");
        confirmLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;"); // Styling the label
        layout.setCenter(confirmLabel);

        // Create a preview of the board with the new ship position
        //GridPane boardPreview = createBoardPreview(newShipPosition, ship);
        //boardPreview.setStyle("-fx-background-color: #ffffff; -fx-grid-lines-visible: true;"); // Style the board preview

        // Confirmation and cancel buttons with enhanced styling
        Button confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-font-size: 14; -fx-base: #4CAF50; -fx-text-fill: white; -fx-padding: 10px;");
        confirmButton.setOnAction(e -> {
            MovementChecker.moveShip(ship, newShipPosition);
            BoardView.refreshBoardView();
            popupWindow.close();

            //close the movement window
            stage.close();


            Platform.runLater(AttackInput::setupAttackScreen);


            //terminate the current process


        });


        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-font-size: 14px; -fx-base: #F44336; -fx-text-fill: white; -fx-padding: 10px;");
        cancelButton.setOnAction(e -> {
            popupWindow.close();
        });

        HBox buttonLayout = new HBox(10, confirmButton, cancelButton);
        buttonLayout.setAlignment(Pos.CENTER); // Align buttons to the center
        buttonLayout.setStyle("-fx-padding: 10px;"); // Add padding to the button layout

        layout.setBottom(buttonLayout);
        //layout.setCenter(boardPreview);

        Scene scene = new Scene(layout);
        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }


    /*private static GridPane createBoardPreview(List<int[]> newShipPosition, Ship ship) {
        GridPane boardPreview = new GridPane();

        // Determine the current player's board
        GridPane currentPlayerBoard = (Game.playerNumber == 1) ? BoardView.getPlayerOneBoard() : BoardView.getPlayerTwoBoard();

        // Clone the current board to create a preview
        for (Node node : currentPlayerBoard.getChildren()) {
            if (node instanceof Button) {
                Button originalButton = (Button) node;
                Button newButton = new Button();
                newButton.setPrefSize(originalButton.getPrefWidth(), originalButton.getPrefHeight());
                newButton.setStyle(originalButton.getStyle());
                GridPane.setRowIndex(newButton, GridPane.getRowIndex(originalButton));
                GridPane.setColumnIndex(newButton, GridPane.getColumnIndex(originalButton));
                boardPreview.add(newButton, GridPane.getColumnIndex(originalButton), GridPane.getRowIndex(originalButton));
            }
        }

        // Update the ship's position on the board
        String shipColor = BoardView.getColorForShip(ship); // Get the color for the ship
        for (int[] position : newShipPosition) {
            int col = position[0];
            int row = position[1];
            for (Node node : boardPreview.getChildren()) {
                if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col && node instanceof Button) {
                    Button button = (Button) node;
                    button.setStyle("-fx-background-color: " + shipColor + ";");
                }
            }
        }

        return boardPreview;
    }*/



    //popup window with a new set of buttons: left right up down for the user to move the ship


}
