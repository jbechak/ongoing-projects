package com.zupple.spaceFinder;

import com.zupple.puzzle.Grid;
import com.zupple.puzzle.Word;

import java.util.Random;

public class DiagonalSpaceFinder extends SpaceFinder {
    private Random generate = new Random();
    private int firstX;
    private int firstY;
    private int maxStartX;
    private int maxStartY;

    @Override
    public int[] scanForWordSpace(Word word, Grid grid) {
        assignInitialValues(grid, word);

        for (int i = 0; i < maxStartX; i++) {           //this loops through every column
            int testX = setTestXY(i, firstX, maxStartX);

            for (int j = 0; j < maxStartY; j++) {    //this loop checks through one column
                boolean blockTaken = false;
                int wordStartY = setWordStart(j, firstY, maxStartY);
                blockTaken = wordSpaceOccupied(testX, wordStartY, word, grid);

                if (!blockTaken) { //space for word found!!!
                    return foundLocation(testX, wordStartY);
                }
            }
        }
        return foundLocation(-1, -1);
    }

    @Override
    public void assignInitialValues(Grid grid, Word word) {
        maxStartX = grid.getWidth() - word.length();
        maxStartY = grid.getHeight() - word.length();
        firstX = generate.nextInt(maxStartX);
        firstY = generate.nextInt(maxStartY);
    }

    @Override
    public boolean wordSpaceOccupied(int wordStartX, int wordStartY, Word word, Grid grid) {
        for (int testX = wordStartX, testY = wordStartY; testY < wordStartY + word.length(); testX++, testY++) { //check each letter in word for this 1 word space
            String currentLetterInWord = word.getChar(testY - wordStartY);
            if (hasLetter(testX, testY, currentLetterInWord, grid)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addWord(Word word, Grid grid, int x, int y) {
        for (int i = y, j = 0; i < y + word.length(); i++, j++, x++) {
            grid.setBlock(x, i, word.getChar(j));
        }
    }


}
