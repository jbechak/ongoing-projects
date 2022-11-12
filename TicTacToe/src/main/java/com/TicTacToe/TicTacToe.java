package com.TicTacToe;

import java.util.*;

public class TicTacToe {

    public static void main(String[] args) {

        GameMaster wizard = new GameMaster();
        wizard.setUpGame();
        wizard.runGame();
    }
}

//        Board ticTacToeBoard = new Board();
//        Scanner getInput = new Scanner(System.in);
//
//        List<Player> playerList = new ArrayList<>();
//        Player player1;
//        Player player2;
//
//        Player.createWinningSets();
//        Player.printWinningSets();
//
//        UserInterface gameInterface = new UserInterface();
//        gameInterface.howManyPlayers();

//        player1 = gameInterface.createPlayer("Player 1", "X");
//        playerList.add(player1);
//        player2 = gameInterface.createPlayer("Player 2", "O");
//        playerList.add(player2);
//
//        ticTacToeBoard.printBoard();

        //game flow
//        Player currentPlayer = player1;
//        do {
//            for (int i = 0; i < 9; i++) {
//                System.out.println();
//                currentPlayer = playerList.get(i % 2);
//                currentPlayer.playerDecision(ticTacToeBoard);
//
//                //check for win
//                gameWon = currentPlayer.checkForWin();
//                if (gameWon) {
//                    System.out.println(currentPlayer.getName() + " WINS!");
//                    break;
//                }
//
//            }
//            System.out.print("play another (y)es or (n)o? ");
//            playAgain = getInput.nextLine();

            //reset for next game
//            if (playAgain.equals("y")) {
//                ticTacToeBoard.resetBoard();
//                player1.resetPlayer();
//                player2.resetPlayer();
//            }
//        } while (playAgain.equals("y"));




//    public static List winCombos() {
//        List<List> groupsTogether = new ArrayList<>();
//
//        for (int i = 1; i <= 3; i += 2) {
//            for (int j = 1; j <= i * 2 + 1; j += i) {  //1st loop j = 1; j <= 3; j += 1 -- 2nd loop j = 1; j <= 7; j += 3
//                List<Integer> group = Arrays.asList(j, j + Math.abs(i - 4), j + Math.abs(i - 4) * 2);  //1st loop
//                groupsTogether.add(group);
//            }
//        }
//        for (int i = 1; i <= 3; i += 2) {
//            int j = 5 - i;
//            List<Integer> group = Arrays.asList(i, i + j, i + j * 2);
//            groupsTogether.add(group);
//        }
//        return groupsTogether;
//    }
