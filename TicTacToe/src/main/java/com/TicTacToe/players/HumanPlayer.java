package com.TicTacToe.players;

import com.TicTacToe.Board;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {
private Scanner getInput = new Scanner(System.in);

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public void playerDecision(Board ticTacToeBoard, List<Integer> opponentBlocks, List<List> winSet) {
        int choiceAsInt;
        System.out.println();
        do {
            System.out.print(getName() + ", Where would you like to put your " + getXorO() + "? ");
            String choice = getInput.nextLine();
            choiceAsInt = Integer.parseInt(choice);
        } while (ticTacToeBoard.alreadyPlayed(choiceAsInt));

        ticTacToeBoard.setArrayIndex(choiceAsInt, getXorO());
        updateScoringList(choiceAsInt);

    }
}


