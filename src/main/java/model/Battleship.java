package model;

public class Battleship extends Ship{
    public Battleship() {
        length = 4;
        name = "Battleship";
        damageByCell = new int[] {10,20,60,20};
    }
}
