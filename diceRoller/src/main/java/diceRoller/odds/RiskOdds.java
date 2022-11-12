package diceRoller.odds;

import diceRoller.main.RiskMode;
import diceRoller.main.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class RiskOdds extends RiskMode {
    private UserInterface riskUI = new UserInterface();
    private List<Integer> redDiceValues = new ArrayList<>();
    private List<Integer> whiteDiceValues = new ArrayList<>();
    private List<Integer[]> redWhiteWinSet = new ArrayList<>();
    private final int MAX_DIE_VALUE = 6;
    private int numOfDice;
    private int totalCombinations;

    public void mainBrain() {
        resetValues();
        int currentDie = 0;
        riskRecursion(currentDie);
        totalCombinations = redWhiteWinSet.size();
        RiskOddsResult result = calculateRiskOdds();
        presentRiskOdds(result);
    }

    public void resetValues() {
        redDiceValues = populateListWithZeros(redDiceValues, "red ", 3, " (1 - 3) ");
        whiteDiceValues = populateListWithZeros(whiteDiceValues, "white ", 2, " (1 - 2) ");
        numOfDice = redDiceValues.size() + whiteDiceValues.size();
        redWhiteWinSet.clear();
        totalCombinations = 0;
    }

    public int getNumberOfDice(String color, String constraint) {
        do {
            String diceAmount = riskUI.getNumOfDice(color, constraint);
            try {
                numOfDice = Integer.parseInt(diceAmount);
                return numOfDice;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        } while (true);
    }

    public List<Integer> populateListWithZeros(List<Integer> diceList, String color, int max, String constraint) {
        diceList.clear();
        int amountOfDice;

        do {
            amountOfDice = getNumberOfDice(color, constraint);
        } while (amountOfDice < 1 || amountOfDice > max);

        for (int i = 0; i < amountOfDice; i++) {
            diceList.add(0);
        }
        return diceList;
    }

    public void riskRecursion(int currentDie) {
        if (currentDie >= numOfDice) {
            return;
        }
        currentDie++;

        for (int i = 1; i <= MAX_DIE_VALUE; i++) {
            if (currentDie <= redDiceValues.size()) {
                redDiceValues.set(currentDie - 1, i);
            } else {
                whiteDiceValues.set(currentDie - redDiceValues.size() - 1, i);
            }
            riskRecursion(currentDie);
            if (currentDie == numOfDice) {
                redWhiteWinSet.add(getRiskWinners());
            }
        }
        currentDie--;
    }

    public Integer[] getRiskWinners() {
        Integer[] wins = { 0, 0 };
        if (highest(whiteDiceValues) >= highest(redDiceValues)) {
            wins[1]++;
        }
        else wins[0]++;
        if (whiteDiceValues.size() > 1 && redDiceValues.size() > 1) {
            if (secondHighest(whiteDiceValues) >= secondHighest(redDiceValues)) {
                wins[1]++;
            }
            else wins[0]++;
        }
        return wins;
    }

    public RiskOddsResult calculateRiskOdds() {
        RiskOddsResult result = new RiskOddsResult();

        for(Integer[] redWhite : redWhiteWinSet) {
            if (redWhite[0] == 2) {
                result.incrementRedSweepCount();
            }
            else if (redWhite[1] == 2) {
                result.incrementWhiteSweepCount();
            }
            else if (redWhite[0] == 1 && redWhite[1] == 1) {
                result.incrementTieCount();
            }
            else if (redWhite[0] == 1) {
                result.incrementRedWinCount();
            }
            else if (redWhite[1] == 1) {
                result.incrementWhiteWinCount();
            }
        }
        return result;
    }

    public void presentRiskOdds(RiskOddsResult result) {
        riskUI.riskOddsHeader(redDiceValues.size(), whiteDiceValues.size());
        if (redDiceValues.size() <= 1 || whiteDiceValues.size() <= 1) {
            riskUI.printRiskOdds("red win (defender removes 1 battalion)", result.getRedWinCount(), totalCombinations, chance(result.getRedWinCount(), totalCombinations));
            riskUI.printRiskOdds("white win (attacker removes 1 battalion)", result.getWhiteWinCount(), totalCombinations, chance (result.getWhiteWinCount(), totalCombinations));

        } else {
            riskUI.printRiskOdds("red sweep (defender removes 2 battalions)", result.getRedSweepCount(), totalCombinations, chance(result.getRedSweepCount(), totalCombinations));
            riskUI.printRiskOdds("white sweep (attacker removes 2 battalions)", result.getWhiteSweepCount(), totalCombinations, chance(result.getWhiteSweepCount(), totalCombinations));
            riskUI.printRiskOdds("tie (defender and attacker both remove 1 battalion)", result.getTieCount(), totalCombinations, chance(result.getTieCount(), totalCombinations));
        }
    }

    public int chance(int occurrences, int totalCombinations) {
        return Math.round ((float) occurrences / totalCombinations * 100);
    }
}
