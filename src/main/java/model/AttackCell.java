package model;

public class AttackCell extends Cell {
    boolean shipHit;
    boolean shipSunk;
    int roundSinceHit;

    public AttackCell(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.shipHit = false;
        this.shipSunk = false;
        this.roundSinceHit = 0;
    }



    //shiphealth system
    int healthOfShipP1 = {3, 3, 3, 3, 3}; //placeholder
    healthOfShipP1[0] = Carrier;
    healthOfShipP1[1] = Cruiser;
    healthOfShipP1[2] = Destroyer;
    healthOfShipP1[3] = Submarine;
    healthOfShipP1[4] = Battleship;

    int healthOfShipP2 = {3, 3, 3, 3, 3}; //placeholder
    healthOfShipP2[0] = Carrier;
    healthOfShipP2[1] = Cruiser;
    healthOfShipP2[2] = Destroyer;
    healthOfShipP2[3] = Submarine;
    healthOfShipP2[4] = Battleship;


    if (shipHit = true){
        int shipIndex = shipHit();
        int i = shipIndex;
        int healthOfShipP1{i} == healthOfShip{i} - 1;
                if (int healthOfShipP1{i} == 0)
                {
                    if (i == 0){
                        boolean carrierSunk = true;
                    }
                    if (i == 1){
                        boolean cruiserSunk = true;
                    }
                    if (i == 2){
                        boolean destroyerSunk = true;
                    }
                    if (i == 3){
                        boolean submarineSunk = true;
                    }
                    if (i == 4){
                        boolean battleshipSunk = true;
                    }
                    else{
                        System.out.println("expected error: AttackCell; line 46");
                    }
                }
    }
}
