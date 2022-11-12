package com.TicTacToe;

import com.TicTacToe.players.*;

import java.util.Scanner;

public class UserInterface {
    private Scanner keyboard = new Scanner(System.in);
    private int playerAmount = 1;
    private int playersCreated = 0;

    public void howManyPlayers() {
        System.out.println("\nWELCOME TO TIC-TAC-TOE!\n");
        System.out.print("How many players (1 or 2)? ");
        String numberString = keyboard.nextLine();
        playerAmount = Integer.parseInt(numberString);
    }

    public Player createPlayer(String player, String xo) {
        Player newPlayer;
        if (playersCreated < playerAmount) {
            String name = playerName(player);
            newPlayer = new HumanPlayer(name);
            newPlayer.setXorO(xo);
            playersCreated++;
            return newPlayer;
        }
        newPlayer = createComputerPlayer(player, xo);
        return newPlayer;
    }

    public Player createComputerPlayer(String player, String xo) {
        Player newPlayer;
        int difficulty = selectDifficulty();
        if (difficulty == 1) {
            newPlayer = new BotPlayer("Bricks");
        } else if (difficulty == 2) {
            newPlayer = new SmartBotPlayer("Marie");
        } else {
            newPlayer = new SmartestBotPlayer("Albert");
        }
        newPlayer.setXorO(xo);
        System.out.println(player + " is " + newPlayer.getName() + " the computer.");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}
        return newPlayer;
    }

    public String playerName(String defaultName) {
        System.out.print(defaultName + ", Enter your name: ");
        String name = keyboard.nextLine();
        return name;
    }

    public int selectDifficulty() {
        System.out.println("Who would you like to play against?\n" +
                "1.) Bricks (easy)\n2.) Marie (medium)\n3.) Albert (hard)");
        String answer = keyboard.nextLine();
        return Integer.parseInt(answer);
    }

    public static void announcePlayerMove(String name, int move, String xo) {
        System.out.print(name + " put their " + xo + " on block " + move);

    }

    public void weHaveAWinner(String playerName) {
        System.out.println("\n" + playerName + " WINS!");
    }

    public String gameOver() {
        System.out.println("GAME OVER");
        System.out.print("play another (y)es or (n)o? ");
        String playAgain = keyboard.nextLine();
        return playAgain;
    }

}
