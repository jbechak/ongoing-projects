package diceBuddy.odds;

import diceBuddy.main.UserInterface;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Odds {
    private UserInterface oddsUI = new UserInterface();
    private final int MAX_DIE_VALUE = 6;
    private int highestValue;
    private Map<Integer, Integer> possibleValues = new LinkedHashMap<>();
    private List<Integer> doubles = new ArrayList<>();
    private int doublesCounter;
    private int numOfDice;
    private int diceRecursed;
    private int totalCombinations;

    public void oddsMenu() {
        String choice;
        do {
            choice = oddsUI.oddsMenu();
            if (choice.equals("1")) {
                basicOdds();
            }
            if (choice.equals("2")) {
                RiskOdds riskOdds = new RiskOdds();
                riskOdds.mainBrain();
            }
        }
        while (!choice.equals("3"));
    }

    public int getNumberOfDice(String color) {
        do {
            String diceAmount = oddsUI.getNumOfDice(color, "");
            try {
                numOfDice = Integer.parseInt(diceAmount);
                return numOfDice;
            } catch (NumberFormatException e) {
                oddsUI.validNumber();
            }
        } while (true);
    }

    public void basicOdds() {
        resetValues();
        recurseDice(0);
        presentOdds();
    }

    public void resetValues() {
        possibleValues.clear();
        doubles.clear();
        numOfDice = getNumberOfDice("");
        highestValue = numOfDice * MAX_DIE_VALUE;
        for (int i = numOfDice; i <= highestValue; i++) {
            possibleValues.put(i, 0);
        }
        diceRecursed = 0;
        totalCombinations = 0;
        doublesCounter = 0;
    }

    public void recurseDice(int incomingValue) {
        if (diceRecursed >= numOfDice) {
            return;
        }
        diceRecursed++;
        for (int i = 1; i <= MAX_DIE_VALUE; i++) {
            doubles.add(i);
            incomingValue++;
            recurseDice(incomingValue);
            if (diceRecursed == numOfDice) {
                recordCombination(incomingValue);
                checkForDoubles();
                doubles.remove(doubles.size() - 1);
            }
        }
        diceRecursed--;
    }

    public void recordCombination(int incomingValue) {
        if (incomingValue >= numOfDice) {
            possibleValues.put(incomingValue, possibleValues.get(incomingValue) + 1);
            totalCombinations++;
        }
    }

    public void checkForDoubles() {
        if (doubles.size() == numOfDice) {
            int firstValue = doubles.get(0);
            for (Integer value : doubles) {
                if (value != firstValue) {
                    break;
                }
            }
            doublesCounter++;
//            break;
        }
    }
    

    public void presentOdds() {
        int chance;
        oddsUI.oddsHeader(numOfDice);
        for (Map.Entry<Integer, Integer> keyValuePair : possibleValues.entrySet()) {
            int value = keyValuePair.getKey();
            int numberOfPossibilities = keyValuePair.getValue();
            chance = Math.round ((float) numberOfPossibilities / totalCombinations * 100);
            oddsUI.printOdds(value, numberOfPossibilities, totalCombinations, chance);
        }
        chance = Math.round ((float) doublesCounter / totalCombinations * 100);
        oddsUI.doublesOdds(doublesCounter, totalCombinations, chance);
    }

}


