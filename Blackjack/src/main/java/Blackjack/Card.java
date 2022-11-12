package Blackjack;

public class Card {
    private String number;
    private String suit;

    private String location = "inDeck";
    private boolean isFaceUp = false;

    // Constructors
    public Card(String number, String suit) {
        this.number = number;
        this.suit = suit;

    }

    // Derived Property
    public int getValue() {
        int value;
        if (number.equals("J") || number.equals("Q") || number.equals("K")) {
            return 10;
        }
        if (number.equals("A")) {
            return 1;
        }
        return Integer.parseInt(number);
    }

    public String getNumber() {
        return number;
    }

    public String getSuit() {
        return suit;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void flipCard() {
        isFaceUp = true;
    }

    public String cardString() {
        if (!isFaceUp) {
            return "one card that's face down";
        }
        return number + " of " + suit;
    }

    public String cardString(Player whoseCard) {
        if (!isFaceUp && whoseCard.getPlayerType() == "human") {
            return number + " of " + suit + " (face down)";
        }
        if (!isFaceUp) {
            return "one card that's face down";
        }
        return number + " of " + suit;
    }

    public String getLocation() {
        return location;
    }

    public void changePossession(String whichPlayer) {
        location = whichPlayer;
    }
}
