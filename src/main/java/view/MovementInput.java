package view;


import controller.MovementChecker;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.PlayerOne;
import model.Ship;
import java.util.List;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import static controller.MovementChecker.coordsAfterMove;

public class MovementInput {
        private static Label playerNameLabel;

    public static void setupScene() {
        // Create the stage
        Stage stage = new Stage();
        // Create the root pane and set the scene
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Create a label for the "Name" section
        Label nameLabel = new Label("Player: " + PlayerOne.getName());
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: normal;"); // Styling the label

        // Create a VBox for the ship selection menu and add the nameLabel to it
        TilePane menu = createShipSelectionMenu();
        menu.getChildren().add(0, nameLabel); // Add nameLabel at the top of the VBox

        // Set the ship selection menu to the left
        root.setLeft(menu);

        root.setCenter(BoardView.getPlayerOneBoard());

        // Configure the stage
        stage.setScene(scene);
        stage.setTitle("Move Your Ships");

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
            selectDirection(PlayerOne.getShipBoard().getShip(shipName));


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

        // Create buttons for each direction
        Button leftButton = new Button("Left");
        ArrayList<int[]> newShipPosition = coordsAfterMove(ship.getCoordinates(), "left");
        leftButton.setDisable(!MovementChecker.checkMovement(ship.getCoordinates(), newShipPosition));
        leftButton.setOnAction(e -> {
            confirmMove(newShipPosition, ship);
            popupWindow.close();
        });

        Button rightButton = new Button("Right");
        ArrayList<int[]> newShipPosition2 = coordsAfterMove(ship.getCoordinates(), "right");
        rightButton.setDisable(!MovementChecker.checkMovement(ship.getCoordinates(), newShipPosition2));
        rightButton.setOnAction(e -> {
            confirmMove(newShipPosition2, ship);
            popupWindow.close();
        });

        Button upButton = new Button("Up");
        ArrayList<int[]> newShipPosition3 = coordsAfterMove(ship.getCoordinates(), "up");
        upButton.setDisable(!MovementChecker.checkMovement(ship.getCoordinates(), newShipPosition3));
        upButton.setOnAction(e -> {
            confirmMove(newShipPosition3, ship);
            popupWindow.close();
        });

        Button downButton = new Button("Down");
        ArrayList<int[]> newShipPosition4 = coordsAfterMove(ship.getCoordinates(), "down");
        downButton.setDisable(!MovementChecker.checkMovement(ship.getCoordinates(), newShipPosition4));
        downButton.setOnAction(e -> {
            confirmMove(newShipPosition4, ship);
            popupWindow.close();

        });

        layout.getChildren().addAll(leftButton, rightButton, upButton, downButton);

        Scene scene = new Scene(layout, 200, 200);
        popupWindow.setScene(scene);
        popupWindow.showAndWait();
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

        // Create a preview of the board with the new ship position
        GridPane boardPreview = createBoardPreview(newShipPosition);
        boardPreview.setStyle("-fx-background-color: #ffffff; -fx-grid-lines-visible: true;"); // Style the board preview

        // Confirmation and cancel buttons with enhanced styling
        Button confirmButton = new Button("Confirm");
        confirmButton.setStyle("-fx-font-size: 14px; -fx-base: #4CAF50; -fx-text-fill: white; -fx-padding: 10px;");
        confirmButton.setOnAction(e -> {
            MovementChecker.moveShip(ship, newShipPosition);
            popupWindow.close();
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-font-size: 14px; -fx-base: #F44336; -fx-text-fill: white; -fx-padding: 10px;");
        cancelButton.setOnAction(e -> {
            selectDirection(ship);
            popupWindow.close();
        });

        HBox buttonLayout = new HBox(10, confirmButton, cancelButton);
        buttonLayout.setAlignment(Pos.CENTER); // Align buttons to the center
        buttonLayout.setStyle("-fx-padding: 10px;"); // Add padding to the button layout

        layout.setBottom(buttonLayout);
        layout.setCenter(boardPreview);

        Scene scene = new Scene(layout);
        popupWindow.setScene(scene);
        popupWindow.showAndWait();
    }


    private static GridPane createBoardPreview(List<int[]> newShipPosition) {
        GridPane boardPreview = new GridPane();
        // Clone the current board to create a preview
        for (Node node : BoardView.getPlayerOneBoard().getChildren()) {
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

        // Highlight the new ship position
        for (int[] position : newShipPosition) {
            int row = position[0];
            int col = position[1];
            for (Node node : boardPreview.getChildren()) {
                if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col && node instanceof Button) {
                    Button button = (Button) node;
                    button.setStyle("-fx-background-color: #4d4d4d; -fx-text-fill: white;"); // Example style
                    break;
                }
            }
        }

        return boardPreview;
    }
    //popup window with a new set of buttons: left right up down for the user to move the ship


}
