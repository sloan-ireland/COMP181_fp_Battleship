package controller;

import model.Player;

public class GameOverSequence {

    private Player player1;
    private Player player2;

    public GameOverSequence(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void checkGameOver() {
        if (player1.hasLost()) {
            announceWinner(player2);
        } else if (player2.hasLost()) {
            announceWinner(player1);
        }
    }

    private void announceWinner(Player winner) {
        System.out.println(winner.getName() + " wins!");
    }
}
