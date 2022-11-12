package Blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerList {

    //private List<Player> players = new ArrayList<>();
    private String[] computerNames = { "The dealer", "Calculo", "Strategio" };
    private Scanner getInput = new Scanner(System.in);
    private int playerCount;
    private boolean dealerBlackjack;
    private int blackjackCount = 0;
    private List<Dealable> players = new ArrayList<>();

    public PlayerList() {
        //this.players = players;
    }


    public Player addHumanPlayer() {
        System.out.print("Please enter your name: ");
        String playerName = getInput.nextLine();
        HumanPlayer playerOne = new HumanPlayer(playerName, "human");
        players.add(playerOne);
        System.out.print("Welcome to the game, " + playerOne.getPlayerName() + ". ");
        return playerOne;
    }

    public int addComputerPlayers() {
        do {
            System.out.print("How many players (1 - 3)? ");
            String playerCountString = getInput.nextLine();
            playerCount = Integer.parseInt(playerCountString);
        } while (playerCount > 3);

        for (int i = playerCount - 1; i >= 0; i--) {
            Player newPlayer = new Player(computerNames[i], "computer" );
            players.add(newPlayer);
        }
        return playerCount;
    }

    public void printPlayerList() {
        System.out.println("\nHere are our " + (playerCount) + " players:");
        for (Dealable player : players) {
            if (!player.getPlayerName().equals("The dealer")) {
                System.out.println(player.getPlayerName() + " with $" + player.getMoney());
            }
        }
    }

    public List<Dealable> getPlayers() {
        return players;
    }

    public void printEveryonesCards() {
        int min = 0;
        int max = players.size() - 1;

        do {
            System.out.println();
            for (int row = 0; row < 8; row++) {
                for (int j = min; j < max; j++) {
                    int counter = 0;
                    for (Card card : players.get(j).getHand()) {
                        if ((row > 0 && row < 6) || counter == 0) {
                            System.out.print(printCard(row, (Player) players.get(j), card));
                        }
                        counter++;
                    }
                    System.out.print("     ");
                }
                System.out.println();
            }
            min = max;
            max = players.size();
        } while (min < players.size());
    }

    public String printCard(int row, Player player, Card card) {
        if (row == 0 ) {
            return player.getPlayerName() + " has: " + extraSpaces(player, player.getPlayerName().length() + 6);
        }
        if (row == 1 || row == 5) {
            return "+-----+  ";
        }
        if (row == 2) {
            return "|     |  ";
        }
        if (row == 3) {
            String leftSpace = "|  ";
            if ( card.getNumber().equals("10")) {
                leftSpace = "| ";
            }
            return leftSpace + card.getNumber() + "  |  ";
        }
        if (row == 4) {
            return "|" + card.getSuit() + "|  ";
        }
        if (row == 6) {
            return  "Funds: $" + player.getMoney() + extraSpaces(player, 12);
        }
        if (row == 7) {
            return "Bet: $" + player.getTotalBet() + " " + player.getStatus() + extraSpaces(player, player.status.length() + 9);
        }
        return "";
    }

    public String extraSpaces(Player player, int subtractor) {
        String printString = "";
        int extraSpaces = (player.getHand().size() * 9 - subtractor);
        for (int k = 0; k < extraSpaces; k++) {
            printString += " ";
        }
        return printString;
    }

    public void placeBets() {
        for (Dealable player : players) {
            if (!player.isStillPlaying()) {
                continue;
            }
            player.decideBet();
        }
    }

    public boolean checkForBlackJack(int round) {
        System.out.println();
        for (Dealable player : players) {
            if (player.isStillPlaying() && player.hasBlackjack()) {
                blackjackCount++;
                System.out.println(player.getPlayerName() + " has blackjack!");
                if (player.getPlayerName().equals("The dealer")) {
                    dealerBlackjack = true;
                    player.getHand().get(0).flipCard();
                }
            }
        }
        if (dealerBlackjack && blackjackCount == 1) {
            System.out.println("Dealer Wins!");
            return true;
        }
        if (dealerBlackjack) {
            for (Dealable player : players) {
                if (player.getPlayerName().equals("The dealer")) {
                    continue;
                }
                if (player.hasBlackjack()) {
                    player.recoupBet();
                }
            }
        }
        return false;
    }

    public void checkForBust() {
        System.out.println();
        for (Dealable player : players) {
            if (player.isStillPlaying()) {
                if (player.hasBust()) {
                    System.out.println(player.getPlayerName() + " is out!");
                    playerCount--;
                }
            }
        }
    }

    public boolean gameOver(boolean hasHits) {
        if (players.get(players.size() - 1).hasBust() || dealerBlackjack && blackjackCount == 1 || playerCount == 0) {
            System.out.println("Game Over!");
            return true;
        }
        if (!hasHits) {
            System.out.println("No hits. Game Over!");
            return true;
        }
        return false;
    }

    public void reset() {
        for (Dealable player : players) {
            player.resetPlayer();
        }
        blackjackCount = 0;
    }

}
