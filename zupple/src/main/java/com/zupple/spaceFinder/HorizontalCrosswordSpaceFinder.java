package com.zupple.spaceFinder;

import com.zupple.crossword.PlacementResults;
import com.zupple.puzzle.Grid;
import com.zupple.puzzle.Word;

import java.util.Random;

public class HorizontalCrosswordSpaceFinder extends SpaceFinder {
    private Random generate = new Random();
    private int firstX;
    private int firstY;
    private int maxStartX;


    @Override
    public PlacementResults scanForWordSpace(Word word, Grid grid, int[] xy) {
        boolean inBounds = true;
        int x = xy[0];
        int y = xy[1];
        while (inBounds) { //this loops through every column
            for (int i = 0; i < word.length(); i++) {    //this loop checks through one column
                if (word.getChar(i).equals(grid.getBlock(x, y))) {
                    if (!wordSpaceOccupied(x - i, y, word, grid)) {
                        PlacementResults results = new PlacementResults(true, x - i, y);
                        return results;
                    }
                }
            }
            y++;
            if (isBlank(x, y, grid) || y >= grid.getWidth()) {
                inBounds = false;
            }
        }
        return null;
    }

    @Override
    public void assignInitialValues(Grid grid, Word word) {
        maxStartX = grid.getWidth() - word.length();
        firstX = generate.nextInt(maxStartX);
        firstY = generate.nextInt(grid.getHeight());
    }

    @Override
    public boolean wordSpaceOccupied(int wordStartX, int testY, Word word, Grid grid) {
        if (!beforeAndAfterWordClean(wordStartX, testY, word, grid)) {
            return true;
        }
        for (int testX = wordStartX; testX < wordStartX + word.length(); testX++) { //check each letter in word for this 1 word space
            String currentLetterInWord = word.getChar(testX - wordStartX);
            if (hasLetter(testX, testY, currentLetterInWord, grid)) {
                return true;
            }
            if (!yPerimeterClean(testX, testY, currentLetterInWord, grid)) {
                return true;
            }
        }
        return false;
    }

    private boolean beforeAndAfterWordClean(int x, int y, Word word, Grid grid) {
        if (isBlank(x - 1, y, grid) && isBlank(x + word.length(), y, grid)) {
            return true;
        }
        return false;
    }

    private boolean yPerimeterClean(int x, int y, String letter, Grid grid) {
        if (!grid.getBlock(x, y).equals(letter) && isBlank(x, y + 1, grid) && isBlank(x, y - 1, grid)) {
            return true;
        }
        if (grid.getBlock(x, y).equals(letter)) {
            return true;
        }
        return false;
    }

    @Override
    public void addWord(Word word, Grid grid, int x, int y) {
        for (int i = x, j = 0; j < word.length(); i++, j++) {
            grid.setBlock(i, y, word.getChar(j));
        }
    }

}
