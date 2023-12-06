package model;

public class Controller {

    // Format: {x0, y0, x1, y1, . . . you get the idea}
    private int[] DestroyerPositions = {1, 1, 1, 2};
    private int[] CruiserPositions = {2, 3, 2, 4, 2, 5};
    private int[] BattleshipPositions = {3, 6, 3, 7, 3, 8, 3, 9};
    private int[] SubmarinePositions = {4, 1, 4, 2, 4, 3};
    private int[] CarrierPositions = {0, 0, 0, 1, 0, 2, 0, 3, 0, 4};

    private int movementUnit = 1;

    public void right(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || i % 2 == 0) {
                array[i] += movementUnit;
            }
        }
    }

    public void left(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || i % 2 == 0) {
                array[i] -= movementUnit;
            }
        }
    }

    public void up(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i % 2 != 0) {
                array[i] += movementUnit;
            }
        }
    }

    public void down(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i % 2 != 0) {
                array[i] -= movementUnit;
            }
        }
    }


}