package com.TicTacToe.players;

import com.TicTacToe.Board;

import java.util.*;

public abstract class Player {
    private String name;
    private List<Integer> scoringList = new ArrayList<>();
    private String xorO;
    private static final Set<List> WINNING_SETS = new HashSet<>();
    //private static int[] winningNumbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 4, 7, 2, 5, 8, 3, 6, 9, 1, 5, 9, 3, 5, 7 };

    public Player(String name) {
        this.name = name;
    }


    public abstract void playerDecision(Board ticTacToeBoard, List<Integer> opponentBlocks, List<List> winSet);

    public String getName() {
        return name;
    }

    public List<Integer> getScoringList() {
        return scoringList;
    }

    public void updateScoringList(int newBlock) {
        scoringList.add(newBlock);
    }

    public String getXorO() {
        return xorO;
    }

    public void setXorO(String xorO) {
        this.xorO = xorO;
    }

    public void resetPlayer() {
        scoringList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(scoringList, player.scoringList) && Objects.equals(xorO, player.xorO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scoringList, xorO);
    }
}
