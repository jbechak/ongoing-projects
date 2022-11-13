package com.zupple.dto;

public class SudokuUpdateDto {

    private int sudokuId = 0;
    private String title;
    private int difficulty;
    private String gridString;
    private String instructions;

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

}
