package diceBuddy.main;

import java.util.Scanner;

public class UserInterface {
    private Scanner keyboard = new Scanner(System.in);

    public void welcome() {
        System.out.println("\nWELCOME TO DICE BUDDY!\n");
    }

    public String mainMenu() {
        System.out.println("\nMAIN MENU\n" +
                "1. Create players\n" +
                "2. Select number of dice\n" +
                "3. Roll dice!\n" +
                "4. RISK mode\n" +
                "5. Dice rolling odds\n" +
                "6. Exit\n");

        System.out.print("Please select an option: ");
        return keyboard.nextLine();
    }

    public String validSelection() {
        System.out.print("Please enter a valid option: ");
        return keyboard.nextLine();
    }

    public void validNumber() {
        System.out.println("Please enter a valid number");
    }

    public String howManyPlayers() {
        System.out.print("How many players will be rolling dice simultaneously? ");
        return keyboard.nextLine();
    }

    public String howManyToAdd() {
        System.out.print("How many players would you like to add? ");
        return keyboard.nextLine();
    }

    public String addMoreOrRest(int numOfPlayers) {
        System.out.println("\nCurrently, there are " + numOfPlayers + " players." +
                "\n1. Add more players" +
                "\n2. Reset players" +
                "\n3. Previous menu");
        System.out.print("Please select an option: ");
        return keyboard.nextLine();
    }

    public String wantToEnterNames() {
        System.out.print("Would you like to enter your names (Y/N)? ");
        return keyboard.nextLine();
    }

    public String getName(int playerNumber) {
        System.out.print("Player " + playerNumber + ", please enter your name: ");
        return keyboard.nextLine();
    }

    public String sameNumberOfDice() {
        System.out.print("Will all players be rolling the same number of dice (Y/N)? ");
        return keyboard.nextLine();
    }

    public String alternating() {
        System.out.print("Will the number of dice being rolled alternate between players each turn (Y/N)? ");
        return keyboard.nextLine();
    }

    public String rollDice() {
        System.out.print("Type 'R' to roll the dice again ('P' for previous menu): ");
        return keyboard.nextLine();
    }

    public String getNumOfDice(String color, String constraint) {
        System.out.print("How many " + color + "dice would you like to roll" + constraint + "? ");
        return keyboard.nextLine();
    }

    public String getNumOfDiceAlternatingTurns(String player) {
        System.out.print("How many dice will " + player + " be rolling on their first turn? ");
        return keyboard.nextLine();
    }

    public void rolling(String name) {
        System.out.println(name + " is rolling...");
    }

    public String oddsMenu() {
        System.out.println("\nODDS MENU");
        System.out.println("1. Calculate the odds for a single roll of one or more dice\n" +
                "2. RISK dice rolling odds\n" +
                "3. Main menu\n");
        System.out.print("Please select an option: ");
        return keyboard.nextLine();
    }

    public void oddsHeader(int numberOfDice) {
        System.out.println("\nOdds when rolling " + numberOfDice + " dice:\n");
    }

    public void printOdds(int value, int possibilities, int totalCombinations, int chance) {
        System.out.println("The odds of rolling " + value + " are " + possibilities + " in " + totalCombinations +
                " (" + chance + "% chance)");
    }

    public void doublesOdds(int doublesCount, int totalCombinations, int chance) {
        System.out.println("The odds of all dice coming up the same value are " + doublesCount + " in " + totalCombinations +
                " (" + chance + "% chance)");
    }

    public void riskOddsHeader(int redDice, int whiteDice) {
        System.out.println("\nWhen rolling " + redDice + " red dice versus " + whiteDice + " white dice:");
    }

    public void printRiskOdds(String resultType, int occurrences, int totalCombinations, int chance) {
        System.out.println("The odds of a " + resultType + " are " + occurrences + " in " + totalCombinations +
                " (a " + chance + "% chance)");
    }

    public void goodbye() {
        System.out.println("good bye!");
    }

    public String riskDice(String name, int maxDice) {
        System.out.print(name + ", how many dice would you like to roll (1-" + maxDice + ")? ");
        return keyboard.nextLine();
    }

    public String riskNextRoll() {
        System.out.print("\nWhat would you like to do next?\n" +
                "1. Roll the same dice again\n" +
                "2. Change the amount of dice\n" +
                "3. Main Menu\n\n" +
                "Please select an option: ");
        return keyboard.nextLine();
    }

    public void printRiskResult(String result) {
        System.out.println(result);
    }

    public void printBlankLine() {
        System.out.println();
    }
}
