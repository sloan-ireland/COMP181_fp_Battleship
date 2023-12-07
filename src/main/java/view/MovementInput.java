package view;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PlayerOne;
import model.Ship;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.layout.TilePane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class MovementInput {

        private static Label playerNameLabel;
        private static VBox shipList;
        public static Ship[] ships;

        private static String playerName;
        private static int currentShipIndex = -1;
        private static List<Button> selectedButtons = new ArrayList<>();
        private static List<Label> shipLabels = new ArrayList<>();
        private static boolean isPlacingShip = false;

        public static List<int[]> shipCoordinates = new ArrayList<>();

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
            moveShip(PlayerOne.getShipBoard().getShip(shipName));


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

    //get the coords of the ship and prompt the user to move ship to a new location
    //movement may only be in tetris-like way. no diagonal movement. may only move to empty cells. may not move off the board
    //this method onyl changes the Gridpane in BoardView. it does not change the shipBoard in PlayerOne
    //create a confirm button that will lock in the cnanges. if not confirm then not change made
    public static void moveShip(Ship ship) {
    }

    //set a new action for the board in view class.
    //like ship input, the user can click on the cells of the board to move the ship
    public static void promptInput() {

    }
}
