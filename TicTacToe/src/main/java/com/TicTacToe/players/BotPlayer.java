package com.TicTacToe.players;

import com.TicTacToe.Board;
import com.TicTacToe.UserInterface;

import java.util.List;
import java.util.Random;

public class BotPlayer extends Player {
    Random random = new Random();

    public BotPlayer(String name) {
        super(name);
    }

    @Override
    public void playerDecision(Board ticTacToeBoard, List<Integer> opponentBlocks, List<List> winSet) {
        int choiceAsInt;
        System.out.println();
        do {
            choiceAsInt = random.nextInt(8) + 1;
        } while (ticTacToeBoard.alreadyPlayed(choiceAsInt));

        ticTacToeBoard.setArrayIndex(choiceAsInt, getXorO());
        updateScoringList(choiceAsInt);
        UserInterface.announcePlayerMove(getName(), choiceAsInt, getXorO());
    }
}
