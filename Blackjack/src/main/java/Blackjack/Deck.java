package Blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Deck {

    String[] SUITS = {"heart", "dimnd", "clubs", "spade" };
    String[] FACE_CARDS = { "J", "Q", "K", "A" };
    private List<Card> cardDeck = new ArrayList<>();
    private static int currentCard = 0;
    private Scanner getInput = new Scanner(System.in);
    private boolean hasHits = true;

    //constructors
    public Deck() {
        for (String suit : SUITS) {
            for (int j = 2; j <= 10; j++) {
                Card newCard = new Card("" + j, suit);
                cardDeck.add(newCard);
            }
            for (String faceCard : FACE_CARDS) {
                Card newCard = new Card(faceCard, suit);
                cardDeck.add(newCard);

            }
        }
    }

    //methods
    public void shuffle() {
        Collections.shuffle(cardDeck);
        currentCard = 0;
    }

    public void dealSingleCard(Player receivingPlayer, boolean turnFaceUp) {
        //cardDeck.get(currentCard).changePossession(receivingPlayer.getPlayerName());
        receivingPlayer.addCardToHand(cardDeck.get(currentCard));
        if (turnFaceUp) {
            cardDeck.get(currentCard).flipCard();
        }
        currentCard++;
    }

    public void dealSingleCard(Player receivingPlayer) {
        //cardDeck.get(currentCard).changePossession(receivingPlayer.getPlayerName());
        receivingPlayer.addCardToHand(cardDeck.get(currentCard));
        cardDeck.get(currentCard).flipCard();
        currentCard++;
    }

    public void dealGameCards(PlayerList players) {
        for (Dealable player : players.getPlayers()) {
            if (player.getPlayerName().equals("The dealer")) {
                dealSingleCard((Player) player, false);
            } else {
                dealSingleCard((Player) player, true);
            }
        }
        for (Dealable player : players.getPlayers()) {
            dealSingleCard((Player) player, true);
            player.updateTotalCardValue();
        }
    }

    public boolean dealRound(PlayerList players) {
        boolean hasHits = false;
        if (!players.getPlayers().get(0).isStillPlaying()) {
            System.out.print("Type (c) to continue.");
            String carryOn = getInput.nextLine();
        }

        for (Dealable player : players.getPlayers()) {
            if (!player.isStillPlaying()) {
                continue;
            }
            if (player.playerDecide().equals("h")) {
                dealSingleCard((Player) player);
                player.updateTotalCardValue();
                hasHits = true;
            }
        }
        return hasHits;
    }

    public void addCard(Card newCard) {
        cardDeck.add(newCard);
    }

    public Card getCard(int number) {
        return cardDeck.get(number);
    }

    //    public String deckString() {
//        String result = "";
//        for (Card c : cardDeck) {
//            result += c.cardString() + ", ";
//        }
//        return result;
//    }
//
//    public List<Card> getCardDeck() {
//        return cardDeck;
//    }
}
