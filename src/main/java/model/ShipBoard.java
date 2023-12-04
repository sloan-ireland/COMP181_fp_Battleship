package model;

public class ShipBoard extends Board {

    public Ship[] ships = new Ship[5];
    public ShipCell[][] shipBoard = new ShipCell[BOARD_SIZE][BOARD_SIZE];

    public ShipBoard() {
        //initialize shipBoard
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE ; j++) {
                shipBoard[i][j] = new ShipCell(i, j);
            }
        }
    }
    //accessor and mutator methods
    public ShipCell[][] getShipBoard() {
        return shipBoard;
    }

    public void setShipBoard(ShipCell[][] shipBoard) {
        this.shipBoard = shipBoard;
    }

    public Ship[] getShips() {
        return ships;
    }

    public Ship getShip(String name) {
        for (Ship ship : ships) {
            if (ship.getName().equals(name)) {
                return ship;
            }
        }
        return null;
    }

    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

}