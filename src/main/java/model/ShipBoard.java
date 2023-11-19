package model;

public class ShipBoard extends Board {

    public Ship[] ships = {new Destroyer(), new Submarine(), new Battleship(), new Carrier(), new Cruiser()};
    public ShipCell[][] shipBoard = new ShipCell[BOARD_SIZE][BOARD_SIZE];

}