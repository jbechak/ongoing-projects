package diceRoller.main;

import diceRoller.diceAndPlayers.Player;
import diceRoller.odds.RiskOddsResult;

import java.util.ArrayList;
import java.util.List;

public class RiskMode {
    private UserInterface ui = new UserInterface();
    private List<Player> riskPlayerList = new ArrayList<>();

    public void runRisk() {
        String choice = "";
        riskSetup();
        do {
            riskNumberOfDice();
            do {
                List<List> bothPlayersRollValues = riskDiceRoller();
                Integer[] wins = getRiskWinners(bothPlayersRollValues);
                presentWinner(wins);

                do {
                    choice = ui.riskNextRoll();
                } while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));

            } while (choice.equals("1"));
        } while (choice.equals("2"));
    }

    public void riskSetup() {
            Player attacker = new Player("Attacker");
            Player defender = new Player("Defender");
            riskPlayerList.add(attacker);
            riskPlayerList.add(defender);
    }

    public void riskNumberOfDice() {
        for (int i = 0, max = 3; i < 2; i++, max--) {
            int numOfDice = 0;
            boolean success = false;
            do {
            String diceAmount = ui.riskDice(riskPlayerList.get(i).getName(), max);
                try {
                    numOfDice = Integer.parseInt(diceAmount);
                    if (numOfDice >= 1 && numOfDice <= max) {
                        success = true;
                    } else {
                        ui.validNumber();
                    }
                } catch (NumberFormatException e) {
                    ui.validNumber();
                }
            } while (!success);
            riskPlayerList.get(i).setNumOfDice(numOfDice);
        }
    }

    public List<List> riskDiceRoller() {
        List<List> bothPlayersRollValues = new ArrayList<>();
        ui.printBlankLine();
        for (Player player : riskPlayerList) {
            ui.rolling(player.getName());
            List<Integer> valueList = player.rollDice();
            bothPlayersRollValues.add(valueList);
        }
        return bothPlayersRollValues;
    }

    public Integer[] getRiskWinners(List<List> bothPlayersRollValues) {
        List<Integer> redDiceValues = bothPlayersRollValues.get(0);
        List<Integer> whiteDiceValues = bothPlayersRollValues.get(1);
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

    public int highest(List<Integer> diceValues) {
        int highest = 1;
        for (Integer diceValue : diceValues) {
            if (diceValue > highest) {
                highest = diceValue;
            }
        }
        return highest;
    }

    public int secondHighest(List<Integer> diceValues) {
        int secondHighest = 0;
        int highest = 0;
        int highestIndex = 0;
        for (int i = 0; i < diceValues.size(); i++) {
            if (diceValues.get(i) > highest) {
                highest = diceValues.get(i);
                highestIndex = i;
            }
        }
        for (int i = 0; i < diceValues.size(); i++) {
            if (diceValues.get(i) > secondHighest && i != highestIndex) {
                secondHighest = diceValues.get(i);
            }
        }
        return secondHighest;
    }

    public void presentWinner(Integer[] redWhite) {
        String result = "";
        if (redWhite[0] == 2) {
            result = "Red sweep! Defender removes 2 battalions";
        }
        else if (redWhite[1] == 2) {
            result = "White sweep! Attacker removes 2 battalions";
        }
        else if (redWhite[0] == 1 && redWhite[1] == 1) {
            result = "Tie! Defender and attacker both remove 1 battalion";
        }
        else if (redWhite[0] == 1) {
            result = "Red win! Defender removes 1 battalion";
        }
        else if (redWhite[1] == 1) {
            result = "White win! Attacker removes 1 battalion";
        }
        ui.printRiskResult(result);
    }

}
