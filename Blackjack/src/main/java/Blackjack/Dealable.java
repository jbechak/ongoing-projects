package Blackjack;

import java.util.List;

public interface Dealable {

    int getMoney();
    int getTotalBet();
    void updateTotalCardValue();
    String getPlayerName();
    void displayCards();
    boolean isStillPlaying();
    void decideBet();
    boolean hasBlackjack();
    List<Card> getHand();
    void recoupBet();
    boolean hasBust();
    void resetPlayer();
    String playerDecide();
    String getStatus();








}
