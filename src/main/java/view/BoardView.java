package view;

import controller.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.*;

public class BoardView {
    public static GridPane playerOneShipBoard = createShipBoard(null);
    public static GridPane playerTwoShipBoard = createShipBoard(null);

    public static GridPane playerOneAttackBoard = createAttackBoard(null);
    public static GridPane playerTwoAttackBoard = createAttackBoard(null);


    public static GridPane createShipBoard(EventHandler<ActionEvent> cellClickAction) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5); // Reduced horizontal gap
        grid.setVgap(5); // Reduced vertical gap

        // Adding X-axis labels (Column Headers)
        for (int col = 0; col < 10; col++) {
            Label colLabel = new Label(Integer.toString(col));
            grid.add(colLabel, col + 1, 0); // Offset by 1 to account for Y-axis labels
        }

        // Adding Y-axis labels (Row Headers)
        for (int row = 0; row < 10; row++) {
            Label rowLabel = new Label(Integer.toString(row));
            grid.add(rowLabel, 0, row + 1); // Offset by 1 to account for X-axis labels
        }

        // Adding buttons for the board cells
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Button cell = new Button();
                cell.setPrefSize(30, 30); // Adjust size as needed
                grid.add(cell, col + 1, row + 1); // Offset by 1 due to axis labels
                cell.setOnAction(cellClickAction);
            }
        }

        return grid;
    }

    public static GridPane createAttackBoard(EventHandler<ActionEvent> cellClickAction) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5); // Reduced horizontal gap
        grid.setVgap(5); // Reduced vertical gap

        // Adding X-axis labels (Column Headers)
        for (int col = 0; col < 10; col++) {
            Label colLabel = new Label(Integer.toString(col));
            grid.add(colLabel, col + 1, 0); // Offset by 1 to account for Y-axis labels
        }

        // Adding Y-axis labels (Row Headers)
        for (int row = 0; row < 10; row++) {
            Label rowLabel = new Label(Integer.toString(row));
            grid.add(rowLabel, 0, row + 1); // Offset by 1 to account for X-axis labels
        }

        // Adding buttons for the board cells
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Button cell = new Button();
                cell.setPrefSize(30, 30); // Adjust size as needed
                grid.add(cell, col + 1, row + 1); // Offset by 1 due to axis labels
                AttackCell cellForColor;
                if (Game.playerNumber == 1) {
                    cellForColor = PlayerOne.getAttackBoard().getAttackBoard()[col][row];
                } else {
                    cellForColor = PlayerTwo.getAttackBoard().getAttackBoard()[col][row];
                }
                if (cellForColor.getColorOfCell().equals("red")) {
                    cell.setStyle("-fx-background-color: red;");
                }
                if (cellForColor.getColorOfCell().equals("blue")) {
                    cell.setStyle("-fx-background-color: blue;");
                }
                cell.setOnAction(cellClickAction);
            }
        }

        return grid;
    }

    public static String getColorForShip(Ship ship) {
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

    public static void refreshBoardView() {
        // Clear the existing board
        playerOneShipBoard.getChildren().clear();

        // Rebuild the board with updated ship positions and labels
        addAxisLabels(playerOneShipBoard);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Button cell = new Button();
                cell.setPrefSize(30, 30);
                playerOneShipBoard.add(cell, col + 1, row + 1); // Offset by 1 due to axis labels

                Ship occupantShip = PlayerOne.getShipBoard().getShipBoard()[col][row].getOccupantShip();
                if (occupantShip != null) {
                    String shipColor = getColorForShip(occupantShip);
                    cell.setStyle("-fx-background-color: " + shipColor + ";");

                }
            }
        }
        //loop through the board and set the text of the button to the corresponding number in the damageByCell array
        /*Ship[] ships;
        if (Game.playerNumber == 1) {
            ships = PlayerOne.getShipBoard().getShips();
        } else {
            ships = PlayerTwo.getShipBoard().getShips();
        }*/
    }

    private static void addAxisLabels(GridPane grid) {
        // Adding X-axis labels (Column Headers)
        for (int col = 0; col < 10; col++) {
            Label colLabel = new Label(Integer.toString(col));
            grid.add(colLabel, col + 1, 0); // Offset by 1 to account for Y-axis labels
        }

        // Adding Y-axis labels (Row Headers)
        for (int row = 0; row < 10; row++) {
            Label rowLabel = new Label(Integer.toString(row));
            grid.add(rowLabel, 0, row + 1); // Offset by 1 to account for X-axis labels
        }
    }

    public static void initializePlayerOneShipBoard() {
        // Ensure PlayerOne's ShipBoard is initialized
        if (PlayerOne.getShipBoard() == null) {
            PlayerOne.setShipBoard(new ShipBoard());
        }
        ShipBoard shipBoard = PlayerOne.getShipBoard();
        // Check if ships are set for PlayerOne
        if (shipBoard.getShips() != null) {
            // Cycle through the ships and add them to the board
            for (Ship ship : shipBoard.getShips()) {
                if (ship.getCoordinates() != null) {
                    for (int[] coord : ship.getCoordinates()) {
                        shipBoard.getShipBoard()[coord[0]][coord[1]].setOccupantShip(ship);
                    }
                }
            }
        }

        // Update PlayerOne's ShipBoard
        PlayerOne.setShipBoard(shipBoard);
    }

    public static void initializePlayerTwoShipBoard() {
        // Ensure PlayerOne's ShipBoard is initialized
        if (PlayerTwo.getShipBoard() == null) {
            PlayerTwo.setShipBoard(new ShipBoard());
        }
        ShipBoard shipBoard = PlayerTwo.getShipBoard();
        // Check if ships are set for PlayerTwo
        if (shipBoard.getShips() != null) {
            // Cycle through the ships and add them to the board
            for (Ship ship : shipBoard.getShips()) {
                if (ship.getCoordinates() != null) {
                    for (int[] coord : ship.getCoordinates()) {
                        shipBoard.getShipBoard()[coord[0]][coord[1]].setOccupantShip(ship);
                    }
                }
            }
        }

        // Update PlayerTwo's ShipBoard
        PlayerTwo.setShipBoard(shipBoard);
    }

    public static void updateAttackBoardGridpane(Button cell) {
        GridPane newAttackBoard = createAttackBoard(null);
    }


    public static void setPlayerOneBoardAction(EventHandler<ActionEvent> action) {
        playerOneShipBoard = createShipBoard(action);
    }

    public static void setPlayerTwoBoardAction(EventHandler<ActionEvent> action) {
        playerTwoShipBoard = createShipBoard(action);
    }

    public static GridPane getPlayerOneBoard() {
        return playerOneShipBoard;
    }

    public static GridPane getPlayerTwoBoard() {
        return playerTwoShipBoard;
    }

    public static GridPane getPlayerOneAttackBoard() {
        return playerOneAttackBoard;
    }

    public static GridPane getPlayerTwoAttackBoard() {
        return playerTwoAttackBoard;
    }

    public static void setPlayerOneAttackBoardAction(EventHandler<ActionEvent> action) {
        playerOneAttackBoard = createAttackBoard(action);
    }

    public static void setPlayerTwoAttackBoardAction(EventHandler<ActionEvent> action) {
        playerTwoAttackBoard = createAttackBoard(action);
    }


}
