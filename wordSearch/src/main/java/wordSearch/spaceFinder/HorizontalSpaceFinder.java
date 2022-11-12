package wordSearch.spaceFinder;

import wordSearch.puzzle.Grid;
import wordSearch.puzzle.Word;

import java.util.Random;

public class HorizontalSpaceFinder extends SpaceFinder {
    private Random generate = new Random();
    private int firstX;
    private int firstY;
    private int maxStartX;


    @Override
    public int[] scanForWordSpace(Word word, Grid grid) {
        assignInitialValues(grid, word);

        for (int i = 0; i < grid.getHeight(); i++) {           //this loops through every row
            int testY = setTestXY(i, firstY, grid.getHeight());

            for (int j = 0; j < maxStartX; j++) {    //this loop checks through one row
                boolean blockTaken = false;
                int wordStartX = setWordStart(j, firstX, maxStartX);
                blockTaken = wordSpaceOccupied(wordStartX, testY, word, grid);

                if (!blockTaken) { //space for word found!!!
                    return foundLocation(wordStartX, testY);
                }
            }
        }
        return foundLocation(-1, -1);
    }

    @Override
    public void assignInitialValues(Grid grid, Word word) {
        maxStartX = grid.getWidth() - word.length();
        firstX = generate.nextInt(maxStartX);
        firstY = generate.nextInt(grid.getHeight());
    }

    @Override
    public boolean wordSpaceOccupied(int wordStartX, int testY, Word word, Grid grid) {
        for (int testX = wordStartX; testX < wordStartX + word.length(); testX++) { //check each letter in word for this 1 word space
            String currentLetterInWord = word.getChar(testX - wordStartX);
            if (hasLetter(testX, testY, currentLetterInWord, grid)) {
                return true;
            }
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
