package model;

public class Carrier extends Ship{
    public Carrier() {
        length = 5;
        name = "Carrier";
        damageByCell = new int[] {10,10,20,50,20};
    }
}
