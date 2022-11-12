package Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player implements Dealable {
    //private int money = 2000;
    //private int totalBet = 0;
    private static Scanner getInput = new Scanner(System.in);
    private boolean hasAce;
    private boolean hasBlackjack;

    public HumanPlayer(String playerName, String playerType) {
        super(playerName, playerType);
        this.totalCardValue = 0;
    }

    @Override
    public void decideBet() {
        System.out.println("\nYou have $" + money + ".");
        System.out.print("How much would you like to bet? ");
        String startingBet = getInput.nextLine();
        totalBet = Integer.parseInt(startingBet);
        money = money - totalBet;
    }

    @Override
    public String playerDecide() {
            System.out.print("What would you like to do next: (h)it, (s)tand, or (d)ouble down? ");
            String choice = getInput.nextLine();
            if (choice.equals("s")) {
                stillPlaying = false;
            }
            return choice;
    }

    public void displayCards() {
        System.out.println("You have: ");
//        for (Card card : hand) {
//            System.out.println("   " + card.cardString());
//        }
        for (Card card : hand) {
            System.out.print("+-----+  ");
        }
        System.out.println();
        for (Card card : hand) {
            System.out.print("|     |  ");
        }
        System.out.println();
        for (Card card : hand) {
            //if (card.getNumber()
            System.out.print("|  " + card.getNumber() + "  |  ");
        }
        System.out.println();
        for (Card card : hand) {
            System.out.print("|" + card.getSuit() + "|  ");
        }
        System.out.println();
        for (Card card : hand) {
            System.out.print("+-----+  ");
        }

        System.out.println();
        System.out.println("Funds: $" + money + ", Bet: $" + totalBet);
    }

    public boolean hasBust() {
        if (totalCardValue > 21) {
            stillPlaying = false;
            return true;
        }
        return false;
    }

    public void resetPlayer() {
        totalBet = 0;
        totalCardValue = 0;
        stillPlaying = true;
        hasBlackjack = false;
        hasAce = false;
        hand.clear();
    }

    //    public int getMoney() {
//        return money;
//    }
//
//    public int getTotalBet() {
//        return totalBet;
//    }

//    public void updateTotalCardValue() {
//        totalCardValue = 0;
//        for (Card card : hand) {
//            totalCardValue += card.getValue();
//        }
//    }

//    public void addCardToHand(Card newCard) {
//        hand.add(newCard);
//    }

//    public List<Card> getHand() {
//        return hand;
//    }

//    public boolean isStillPlaying() {
//        return stillPlaying;
//    }
}



