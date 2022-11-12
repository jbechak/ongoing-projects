package Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player implements Dealable {
    //public String getStatus;
    private String playerName;
    private String playerType;
    public List<Card> hand = new ArrayList<>();
    public int money = 2000;
    public int totalBet = 0;
    public int totalCardValue = 0;
    private Random rand = new Random();
    private boolean hasAce;
    private boolean hasBlackjack;
    public boolean stillPlaying = true;
    public String status = "";

    public Player(String playerName, String playerType) {
        this.playerName = playerName;
        this.playerType = playerType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerType() {
        return playerType;
    }

    public String getStatus() {
        return status;
    }

    public void addCardToHand(Card newCard) {
        hand.add(newCard);
    }

    public List<Card> getHand() {
        return hand;
    }

    public void updateTotalCardValue() {
        totalCardValue = 0;
        for (Card card : hand) {
            totalCardValue += card.getValue();
        }
    }

    public int getMoney() {
        return money;
    }

    public int getTotalBet() {
        return totalBet;
    }

    public void recoupBet() {
        money = money + totalBet;
    }

    public void decideBet() {
        do {
            totalBet = rand.nextInt(money / 40) * 10;
        } while (totalBet == 0);
        money -= totalBet;
    }

    public String playerDecide() {
        if (totalCardValue > 14) {
            stillPlaying = false;
            System.out.println(playerName + " will stand.");
            status = "Standing";
            return "s";
        } else {
            System.out.println(playerName + " is taking a hit.");
            return "h";
        }
    }

    public boolean hasAce() {
        for (Card card : hand) {
            if (card.getNumber() == "Ace") {
                return true;
            }
        }
        return false;
    }

    public boolean hasBlackjack() {
        if (totalCardValue == 21 || (hasAce() && totalCardValue == 31)) {
            hasBlackjack = true;
            stillPlaying = false;
            status = "BLACKJACK!";
        }
        return hasBlackjack;
    }

    public boolean hasBust() {
        if (totalCardValue > 21) {
            stillPlaying = false;
            status = "BUST";
            return true;
        }
        return false;
    }

    public boolean isStillPlaying() {
        return stillPlaying;
    }

    public void resetPlayer() {
        totalBet = 0;
        totalCardValue = 0;
        stillPlaying = true;
        hasBlackjack = false;
        hasAce = false;
        status = "";
        hand.clear();
    }

    public void displayCards() {
        System.out.println(playerName + " has: ");

        for (Card card : hand) {
            System.out.print("+-----+  ");
        }
        System.out.println();
        for (Card card : hand) {
            System.out.print("|     |  ");
        }
        System.out.println();
        for (Card card : hand) {
            String leftSpace = "|  ";
            if (card.getNumber().equals("10")) {
                leftSpace = "| ";
            }
            System.out.print(leftSpace + card.getNumber() + "  |  ");
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
        if (!playerName.equals("The dealer")) {
            System.out.println("Funds: $" + money + ", Bet: $" + totalBet);
        }
    }
}


