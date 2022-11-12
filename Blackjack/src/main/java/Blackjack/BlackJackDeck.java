package Blackjack;

import java.util.ArrayList;
import java.util.List;

public class BlackJackDeck {

    private String[] SUITS = {"hearts", "diamonds", "clubs", "spades" };
    private String[] FACE_CARDS = { "Jack", "Queen", "King", "Ace" };
    private String name;

    public BlackJackDeck(String name) {
        this.name = name;
    }

//    public BlackJackDeck createPokerDeck() {
//        Deck newDeck = new Deck();
//
//        for (String suit : SUITS) {
//            for (int j = 2; j <= 10; j++) {
//                Card newCard = new Card("" + j, suit);
//                //myDeck.addCard(newCard);
//                newDeck.addCard(newCard);
//            }
//            for (String faceCard : FACE_CARDS) {
//                Card newCard = new Card(faceCard, suit);
//                //myDeck.addCard(newCard);
//                newDeck.addCard(newCard);
//                //cardDeck.add(new Card(faceCard, suit));
//            }
//        }
//        return newDeck;
//    }
//
//    public String deckString() {
//        String result = "";
//        for (Card c : cardDeck) {
//            result += c.cardString() + ", ";
//        }
//        return result;
//    }
//
//    public List<Card> getCardDeck() {
//        for (String suit : SUITS) {
//            for (int j = 2; j <= 10; j++) {
//                cardDeck.add(new Card("" + j, suit));
//            }
//            for (String faceCard : FACE_CARDS) {
//                cardDeck.add(new Card(faceCard, suit));
//            }
//        }
//        return cardDeck;
//    }
}
