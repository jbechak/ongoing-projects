package diceBuddy.diceAndPlayers;

import java.util.List;

public class Player {
    private String name;
    private int numOfDice;
    private Dice dice = new Dice(1);

    public Player(String name, int numOfDice) {
        this.name = name;
        this.numOfDice = numOfDice;
        this.dice = new Dice(numOfDice);

    }

    public Player(String name) {
        this.name = name;
    }

    public void setNumOfDice(int num) {
        this.numOfDice = num;
        this.dice = new Dice(numOfDice);
    }

    public List<Integer> rollDice() {
        return dice.rollDiceAcross();
    }

    public String getName() {
        return name;
    }
}
