package com.zupple.puzzle;

public class Word {
    private String[] letterArray;
    private boolean inGrid = false;
    private boolean checked = false;
    private String direction = "";
    private int number;
    private int startingX;
    private int startingY;
    private String clue;


    public Word(String[] letterArray) {
        this.letterArray = letterArray;
    }

    @Override
    public String toString() {
        String wordString = "";
        for (String letter : letterArray) {
            wordString += letter;
        }
        return wordString;
    }

    public Word flip() {
        String[] flippedWord = new String[letterArray.length];
        for (int i = 0, j = letterArray.length - 1; i < letterArray.length; i++, j--) {
            flippedWord[j] = letterArray[i];
        }
        Word newWord = new Word(flippedWord);
        return newWord;
    }

    public String[] getLetterArray() {
        return letterArray;
    }

    public void withoutSpace() {
        String[] newArray = new String[letterArray.length];
        int j = 0;
        for (int i = 0; i < letterArray.length; i++) {
            if (!letterArray[i].equals(" ")) {
                newArray[j] = letterArray[i];
                j++;
            }
        }
        if (j != letterArray.length) {
            letterArray = new String[j];
            for (int i = 0; i < letterArray.length; i++) {
                letterArray[i] = newArray[i];
            }
        }
    }

    public int length() {
        return letterArray.length;
    }

    public String getChar(int index) {
        return letterArray[index];
    }

    public boolean isInGrid() {
        return inGrid;
    }

    public void setInGrid(boolean inGrid) {
        this.inGrid = inGrid;
    }

    public String asString() {
        String wordAsString = "";
        for (String letter : letterArray) {
            wordAsString += letter;
        }
        return wordAsString;
    }

    public String getDirection() {
        return direction;
    }



    public boolean isChecked() {
        return checked;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getStartingX() {
        return startingX;
    }

    public void setStartingX(int startingX) {
        this.startingX = startingX;
    }

    public int getStartingY() {
        return startingY;
    }

    public void setStartingY(int startingY) {
        this.startingY = startingY;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setLetterArray(String[] letterArray) {
        this.letterArray = letterArray;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }
}
