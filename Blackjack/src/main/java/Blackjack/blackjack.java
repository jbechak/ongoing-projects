package Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class blackjack {

    public static void main(String[] args) {

        Scanner getInput = new Scanner(System.in);
        Deck myDeck = new Deck();
        PlayerList players = new PlayerList();
        myDeck.shuffle();
        boolean hasHits = true;
        String playMore = "y";
        int round = 1;
        boolean dealerBlackjack = false;

        System.out.println("\nWelcome to Black Jack!\n");

        // Get Players and print list
        Player playerOne = players.addHumanPlayer();
        int computerPlayerNumber = players.addComputerPlayers();
        int totalPlayerNumber = computerPlayerNumber + 1;
        players.printPlayerList();


        //start of game

        do {
            players.placeBets();
            myDeck.dealGameCards(players);
            players.printEveryonesCards();
            dealerBlackjack = players.checkForBlackJack(round);
            while (!players.gameOver(hasHits) && !dealerBlackjack) {
                round++;
                hasHits = myDeck.dealRound(players);
                players.checkForBust();
                players.checkForBlackJack(round);
                players.printEveryonesCards();
            }
            System.out.print("Would you like to play again (y)es or (n)o? ");
            playMore = getInput.nextLine();

            myDeck.shuffle();
            players.reset();
            hasHits = true;
        } while (playMore.equals("y"));





//        myDeck.dealRound(players);
//        players.printEveryonesCards();
//        players.checkForBust();
//
//        players.checkForBlackJack();




    }



}
