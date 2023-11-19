package model;

public class ShipBoard extends Board {
    public Ship[] ships = new Ship[5];
    public ShipCell[][] shipBoard = new ShipCell[BOARD_SIZE][BOARD_SIZE];

}