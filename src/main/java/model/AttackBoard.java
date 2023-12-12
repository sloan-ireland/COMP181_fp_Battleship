package model;

public class AttackBoard extends Board{
    public AttackCell[][] attackBoard = new AttackCell[BOARD_SIZE][BOARD_SIZE];

    public AttackBoard() {
        //initialize attackBoard
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE ; j++) {
                attackBoard[i][j] = new AttackCell(i, j);
            }
        }
    }

    public AttackCell[][] getAttackBoard() {
        return attackBoard;
    }
}
