package com.zupple.model;

public class Sudoku {

    private int sudokuId = 0;
    private String title;
    private int difficulty;
    private String gridString;
    private String answer;
    private String instructions = "Fill in each blank with the correct numbers so that every column contains the numbers 1 thru 9 " +
            "with no duplicates, every row contains the numbers 1 thru 9 with no duplicates, and every 3x3 box " +
            "contains the numbers 1 thru 9 with no duplicates.";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void createTitle() {
        title = "Level " + difficulty + " Puzzle";
    }

    public int getSudokuId() {
        return sudokuId;
    }

    public void setSudokuId(int sudokuId) {
        this.sudokuId = sudokuId;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getGridString() {
        return gridString;
    }

    public void setGridString(String gridString) {
        this.gridString = gridString;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
