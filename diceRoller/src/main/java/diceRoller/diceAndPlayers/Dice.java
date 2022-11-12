package diceRoller.diceAndPlayers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {
    private List<Die> dieList = new ArrayList<>();
    private int numOfDice;
    private Random generate = new Random();

    public Dice(int numOfDice) {
        this.numOfDice = numOfDice;

        for (int i = 0; i < numOfDice; i++) {
            Die die = new Die();
            dieList.add(die);
        }
    }

    public void rollDice() {
        for (Die die : dieList) {
            die.rollDie();
        }
    }

    public List<Integer> rollDiceAcross() {
        List<Integer> valueList = new ArrayList<>();
        for (Die die : dieList) {
            int rollValue = generate.nextInt(6) + 1;
            valueList.add(rollValue);
        }
        for (int i = 0; i < dieList.size(); i++) {
            System.out.print(getDie(i).getDiceSide(valueList.get(i)).getHorizontalLine());
        }
        System.out.println();
        for (int i = 0; i < dieList.size(); i++) {
            System.out.print(getDie(i).getDiceSide(valueList.get(i)).getRowOne());
        }
        System.out.println();
        for (int i = 0; i < dieList.size(); i++) {
            System.out.print(getDie(i).getDiceSide(valueList.get(i)).getRowTwo());
        }
        System.out.println();
        for (int i = 0; i < dieList.size(); i++) {
            System.out.print(getDie(i).getDiceSide(valueList.get(i)).getRowThree());
        }
        System.out.println();
        for (int i = 0; i < dieList.size(); i++) {
            System.out.print(getDie(i).getDiceSide(valueList.get(i)).getHorizontalLine());
        }
        System.out.println("\n");
        return valueList;

    }


    public Die getDie (int index) {
        return dieList.get(index);
    }

}
