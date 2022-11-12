package com.TicTacToe;

import com.TicTacToe.players.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMaster {
    private Board ticTacToeBoard = new Board();
    List<Player> playerList = new ArrayList<>();
    UserInterface gameInterface = new UserInterface();
    ScoreKeeper ref = new ScoreKeeper();
    private Player player1;
    private Player player2;
    String playAgain = "y";

    public void setUpGame() {
        ref.createWinningSets();

        gameInterface.howManyPlayers();
        player1 = gameInterface.createPlayer("Player 1", "X");
        playerList.add(player1);
        player2 = gameInterface.createPlayer("Player 2", "O");
        playerList.add(player2);

        ticTacToeBoard.printBoard();
    }

    public void runGame() {
        Player currentPlayer = player1;
        Player otherPlayer = player2;
        do {
            for (int i = 0; i < 9; i++) {
                currentPlayer = playerList.get(i % 2);
                otherPlayer = playerList.get(Math.abs((i % 2) - 1));
                currentPlayer.playerDecision(ticTacToeBoard, otherPlayer.getScoringList(), ref.getWinningSets());

                if (ref.checkForWin(currentPlayer)) {
                    gameInterface.weHaveAWinner(currentPlayer.getName());
                    break;
                }
            }
            playAgain = gameInterface.gameOver();
            if (playAgain.equalsIgnoreCase("y")) {
               resetGame();
            }
        } while (playAgain.equalsIgnoreCase("y"));
    }

    public void resetGame() {
        ticTacToeBoard.resetBoard();
        player1.resetPlayer();
        player2.resetPlayer();
    }
}
