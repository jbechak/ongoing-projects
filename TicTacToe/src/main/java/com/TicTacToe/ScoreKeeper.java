package com.TicTacToe;

import com.TicTacToe.players.Player;

import java.util.*;

public class ScoreKeeper {
    //private final Set<List> WINNING_SETS = new HashSet<>();
    private final List<List> WINNING_SETS = new ArrayList<>();

    public void createWinningSets() {
        // generate winning combinations and put into a list.
        for (int i = 1; i <= 3; i += 2) { // 1st loop with i = 1, 2nd loop with i = 3
            for (int j = 1; j <= i * 2 + 1; j += i) {  //1st loop j = 1; j <= 3; j += 1 -- 2nd loop j = 1; j <= 7; j += 3
                List<Integer> group = Arrays.asList(j, j + Math.abs(i - 4), j + Math.abs(i - 4) * 2);  //1st loop
                WINNING_SETS.add(group);
            }
        }
        for (int i = 1; i <= 3; i += 2) {
            int j = 5 - i;
            List<Integer> group = Arrays.asList(i, i + j, i + j * 2);
            WINNING_SETS.add(group);
        }
    }

    public void printWinningSets() {
        for (List group : WINNING_SETS) {
            System.out.println(group);
        }
    }

    public List<List> getWinningSets() {
        return WINNING_SETS;
    }

    public boolean checkForWin(Player player) {
        int counter = 0;
        for (List testList : WINNING_SETS) {
            for (int i = 0; i < testList.size(); i++) {
                if (player.getScoringList().contains(testList.get(i))) {
                    counter++;
                    if (counter == 3) {
                        return true;
                    }
                }
            }
            counter = 0;
        }
        return false;
    }
}
