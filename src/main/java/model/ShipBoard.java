package model;

public class ShipBoard extends Board {

    public Ship[] ships = new Ship[5];
    public ShipCell[][] shipBoard = new ShipCell[BOARD_SIZE][BOARD_SIZE];

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

    public void setShips(Ship[] ships) {
        this.ships = ships;
    }
}