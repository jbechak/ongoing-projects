package diceRoller.main;

import diceRoller.diceAndPlayers.Die;
import diceRoller.diceAndPlayers.Player;
import diceRoller.diceSides.*;
import diceRoller.odds.Odds;

import java.util.ArrayList;
import java.util.List;

public class Roller {

    private List<DiceSide> Die = new ArrayList();
    private diceRoller.diceAndPlayers.Die die1 = new Die();
    private UserInterface ui = new UserInterface();
    private int numOfPlayers;
    private List<Player> playerList = new ArrayList<>();

    private Odds odds = new Odds();

    public void run() {
        do {
            ui.welcome();
            mainMenu();
            getPlayers();
            diceRoller();
        } while (true);

    }

    public void mainMenu() {
        do {
            String choice = ui.mainMenu();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") &&
                    !choice.equals("4") && !choice.equals("5") && !choice.equals("6")) {
                choice = ui.validSelection();
            }

            if (choice.equals("1")) {
                getPlayers();
                getSameDiceAmount();
            }
            if (choice.equals("2")) {
//                selectNumberOfDice();
                getSameDiceAmount();
            }
            if (choice.equals("3")) {
                diceRoller();
            }
            if (choice.equals("4")) {
                RiskMode riskMode = new RiskMode();
                riskMode.runRisk();
            }
            if (choice.equals("5")) {
                odds.oddsMenu();
            }
            if (choice.equals("6")) {
                exit();
            }
        } while (true);
    }

    public void getPlayers() {
//        if (playerList.size() >= 2) {
//            addOrResetMenu();
//        }
        playerList.clear();
        String choice = ui.howManyPlayers();
        numOfPlayers = stringToInt(choice);
        addPlayers(numOfPlayers);
    }

    public void addPlayers(int numOfPlayers) {
        String choice = ui.wantToEnterNames();
        if (choice.substring(0, 1).equalsIgnoreCase("y")) {
            createNamedPlayers(numOfPlayers);
        } else {
            createNamelessPlayers(numOfPlayers);
        }
    }

    public void addOrResetMenu() {
        do {
            String choice = ui.addMoreOrRest(playerList.size());
            if (choice.equals("1")) {
                choice = ui.howManyToAdd();
                numOfPlayers = stringToInt(choice);
                addPlayers(numOfPlayers);
                mainMenu();
            }
            if (choice.equals("2")) {
                playerList.clear();
                return;
            }
            if (choice.equals("3")) {
                mainMenu();
            }
        } while(true);
    }

    public void createNamelessPlayers(int numOfPlayers) {
        for (int i = 0; i < numOfPlayers; i++) {
            Player player = new Player("Player " + (i + 1));
            playerList.add(player);
        }
    }

    public void createNamedPlayers(int numOfPlayers) {
        for(int i = 0; i<numOfPlayers; i++)
        {
            String name = ui.getName(i + 1);
            Player player = new Player(name);
            playerList.add(player);
        }
    }

    public void selectNumberOfDice() throws NumberFormatException {
        String choice;
        if (playerList.size() > 1) {
            choice = ui.sameNumberOfDice();
            if (choice.substring(0, 1).equalsIgnoreCase("y")) {
                getSameDiceAmount();
            } else {
                getDifferentDiceAmounts();
            }
        } else if (playerList.size() < 1) {
            createNamelessPlayers(1);
            getSameDiceAmount();
        } else {
            getSameDiceAmount();
        }

    }

    public void getSameDiceAmount() throws NumberFormatException {
        int numOfDice = getNumberOfDice();
        for (Player player : playerList) {
            player.setNumOfDice(numOfDice);
        }
    }

    public int getNumberOfDice() {
        int numOfDice;
        do {
            String diceAmount = ui.getNumOfDice("", "");
            try {
                numOfDice = Integer.parseInt(diceAmount);
                return numOfDice;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        } while (true);
    }

    public int stringToInt(String stringNum) throws NumberFormatException {
        int num;
        try {
            num = Integer.parseInt(stringNum);
        } catch (NumberFormatException e)
        {
            throw e;
        }
        return num;
    }

    public void getDifferentDiceAmounts() {
        String choice = ui.alternating();
        for (int i = 0; i < playerList.size(); i++) {
            String playerName;
            if (choice.substring(0, 1).equalsIgnoreCase("y")) {
                playerName = "Player " + (i + 1);
            }
            playerName = playerList.get(i).getName();
            String diceAmount = ui.getNumOfDiceAlternatingTurns(playerName);
            int numOfDice = Integer.parseInt(diceAmount);
            playerList.get(i).setNumOfDice(numOfDice);
        }
    }

    public void diceRoller() {
        if (playerList.size() < 1) {
            createNamelessPlayers(1);
        }
        String choice = "r";
        do {
            if (choice.equalsIgnoreCase("r")) {
                for (Player player : playerList) {
                    ui.rolling(player.getName());
                    player.rollDice();
                }
            }
            choice = ui.rollDice();
        } while (!choice.equalsIgnoreCase("p"));
    }

    public void exit() {
        ui.goodbye();
        System.exit(0);
    }
}

