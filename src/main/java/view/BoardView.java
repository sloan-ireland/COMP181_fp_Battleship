package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.PlayerOne;
import model.Ship;

public class BoardView {
    public static GridPane playerOneShipBoard = createBoard(null);
    public static GridPane playerTwoShipBoard = createBoard(null);


    public static GridPane createBoard(EventHandler<ActionEvent> cellClickAction) {
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

    public static void setPlayerOneBoardAction(EventHandler<ActionEvent> action) {
        playerOneShipBoard = createBoard(action);
    }

    public static void setPlayerTwoBoardAction(EventHandler<ActionEvent> action) {
        playerTwoShipBoard = createBoard(action);
    }

    public static GridPane getPlayerOneBoard() {
        return playerOneShipBoard;
    }

    public static GridPane getPlayerTwoBoard() {
        return playerTwoShipBoard;
    }
}
