package wordSearch.main;

import wordSearch.*;
import wordSearch.puzzle.Grid;
import wordSearch.puzzle.Puzzle;
import wordSearch.puzzle.Word;
import wordSearch.puzzle.WordList;
import wordSearch.spaceFinder.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BuildingTools {
    private UserInterface myInterface = new UserInterface();
    private SpaceFinder spaceFinder = new SpaceFinder();
    private VerticalSpaceFinder verticalSpaceFinder = new VerticalSpaceFinder();
    private HorizontalSpaceFinder horizontalSpaceFinder = new HorizontalSpaceFinder();
    private DiagonalSpaceFinder diagonalSpaceFinder = new DiagonalSpaceFinder();
    private DiagonalUpSpaceFinder diagonalUpSpaceFinder = new DiagonalUpSpaceFinder();
    private final int MAX_WIDTH = 81;


    public void wordSearchUserEntry(Puzzle puzzle) {
        String word;
        System.out.println();
        do {
            word = myInterface.getAnotherWordFromUser(puzzle.getWordCount());
            if (!word.equals("")) {
                if (!word.matches("[ a-zA-Z]+") || word.length() < 2 || word.contains("  ")) {
                    myInterface.notAWord();
                } else if (isDuplicate(word, puzzle)) {
                    myInterface.duplicate();
                } else {
                    puzzle.getWordCollection().add(word);
                    puzzle.incrementWordCount();
                }
            }
        } while (!word.equals(""));
    }

    public boolean isDuplicate(String newWord, Puzzle puzzle) {
        for (String word : puzzle.getWordCollection()) {
            if (newWord.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    public void removeWords(Puzzle puzzle) {
        myInterface.displayNumberedWordList(puzzle.getWordCollection());
        String wordsToRemove = myInterface.chooseWordsToRemove();
        String[] wordArray = wordsToRemove.split(",");

        List<String> numberStringList = new ArrayList<>();
        for (String word : wordArray) {
            String[] numbers = word.split(" ");
            for (String num : numbers) {
                numberStringList.add(num);
            }
        }
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < numberStringList.size(); i++) {
            try {
                int removeIndex = Integer.parseInt(numberStringList.get(i));
                numberList.add(removeIndex);
            } catch (NumberFormatException e) {
            }
        }
        Collections.sort(numberList);
        for (int i = numberList.size() - 1; i >= 0; i--) {
            puzzle.getWordCollection().remove(numberList.get(i) - 1);
            puzzle.decrementWordCount();
        }
        myInterface.displayNumberedWordList(puzzle.getWordCollection());
    }

    public void wordDirections(Puzzle puzzle) {
        String wordDirections;
        do {
            wordDirections = myInterface.wordDirectionsMenu();
            if (!isValidWordDirections(wordDirections)) {
                myInterface.invalidNumber();
            }
        } while (!isValidWordDirections(wordDirections));

        int wd = Integer.parseInt(wordDirections);
        puzzle.setWordDirections(wd);

//        puzzle.setDifficultyNum(difficulty);
//        puzzle.setDifficulty(difficulty);
        setInstructions(puzzle);
    }

    private boolean isValidWordDirections(String wordDirections) {
        return wordDirections.equals("1") || wordDirections.equals("2") || wordDirections.equals("3");
    }

    public void setInstructions(Puzzle puzzle) {
        Instructions instructions = new Instructions();
        if (puzzle.getWordDirections() == 1) {
            puzzle.setInstructions(instructions.getInstructions2());
        }
        if (puzzle.getWordDirections() == 2) {
            puzzle.setInstructions(instructions.getInstructions4());
        }
        if (puzzle.getWordDirections() == 3) {
            puzzle.setInstructions(instructions.getInstructions8());
        }
    }

    public int longestWord(List<String> wordCollection) {
        int longestWord = 0;
        for (String word : wordCollection) {
            if (word.length() > longestWord) {
                longestWord = word.length();
            }
        }
        return longestWord;
    }

    public void getDimensions(Puzzle puzzle) {
        int minimum = longestWord(puzzle.getWordCollection()) + 1;
        int width = 0;
        int height = 0;
        String[] dimensionsArray;
        do {
            do {
                String choice = myInterface.dimensions(minimum);
                dimensionsArray = choice.split("[x* ]");
            } while (dimensionsArray.length < 2);
            try {
                width = Integer.parseInt(dimensionsArray[0]);
                height = Integer.parseInt(dimensionsArray[1]);
            } catch (NumberFormatException e) {
                myInterface.invalidNumber();
            }
        } while (width < minimum && height < minimum);
        puzzle.setWidth(width);
        puzzle.setHeight(height);
    }

    public void createGrid(Puzzle puzzle) {
        if (puzzle.getHeight() < longestWord(puzzle.getWordCollection()) + 1 || puzzle.getWidth() < longestWord(puzzle.getWordCollection()) + 1) {
            getDimensions(puzzle);
        }
        Grid grid = new Grid(puzzle.getWidth(), puzzle.getHeight());
        puzzle.populateWordList(puzzle.getWordCollection());
        puzzle.setWordCount(puzzle.getWordList().size());
        buildWordSearch(puzzle, grid);
    }

    public void buildWordSearch(Puzzle puzzle, Grid grid) {
        generateWordSearch(puzzle, grid);
        //grid.turnZerosToBlanks();
        puzzle.getWordList().printResults();
        System.out.println(grid);
        grid.fillWithRandomLetters();

        puzzle.setGrid(grid);
        puzzle.setWordCount(puzzle.getFinalWordList().size());
        System.out.println(puzzle);

        myInterface.printAddedWordCount(puzzle.getFinalWordList().size(), puzzle.getWordList().size());

        if (puzzle.getFinalWordList().size() < puzzle.getWordList().size()) {
            myInterface.suggestLargerDimensions();
            printUnusedWords(puzzle);
        }


    }

    public void generateWordSearch(Puzzle puzzle, Grid grid) {
        WordList finalWordList = new WordList();
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < puzzle.getWordList().size(); i++) {
                boolean success = false;
                Word currentWord = blanklessWord(puzzle, i);
                if (!puzzle.getWordList().getWord(i).isInGrid()) {
                    if (puzzle.getWordDirections() == 1) {
                        success = easyWord(currentWord, grid);
                    }
                    if (puzzle.getWordDirections() == 2) {
                        success = mediumWord(currentWord, grid);
                    }
                    if (puzzle.getWordDirections() == 3) {
                        success = hardWord(currentWord, grid);
                    }
                }
                if (success) {
                    grid.updateUsedSpaces(currentWord.length());
                    puzzle.getWordList().getWord(i).setInGrid(true);
                    finalWordList.add(puzzle.getWordList().getWord(i));
                }
            }
        }

        puzzle.setFinalWordList(finalWordList);
    }

    private Word blanklessWord(Puzzle puzzle, int index) {
        Word newWord =new Word(puzzle.getWordList().getWord(index).getLetterArray());
        newWord.withoutSpace();
        return newWord;
    }

    private boolean easyWord(Word word, Grid grid) {
        Random generate = new Random();
        int direction = generate.nextInt(2);
        return placeEasy(word, grid, direction);
    }

    private boolean mediumWord(Word word, Grid grid) {
        Random generate = new Random();
        int direction = generate.nextInt(4);
        if (placeEasy(word, grid, direction)) {
            return true;
        } else {
            return placeMedium(word, grid, direction);
        }
    }

    private boolean hardWord(Word word, Grid grid) {
        Random generate = new Random();
        int flip = generate.nextInt(2);
        if (flip == 1) {
            word = word.flip();
        }
        int direction = generate.nextInt(4);
        if (placeEasy(word, grid, direction)) {
            return true;
        } else {
            return placeMedium(word, grid, direction);
        }
    }

    private boolean placeEasy(Word word, Grid grid, int direction) {
        if (direction == 0) {
            return horizontalSpaceFinder.writeWordInEmptySpace(word, grid);
        }
        if (direction == 1) {
            return verticalSpaceFinder.writeWordInEmptySpace(word, grid);
        }
        return false;
    }

    private boolean placeMedium(Word word, Grid grid, int direction) {
        if (direction == 2) {
            return diagonalSpaceFinder.writeWordInEmptySpace(word, grid);
        }
        if (direction == 3) {
            return diagonalUpSpaceFinder.writeWordInEmptySpace(word, grid);
        }
        return false;
    }

    private void printUnusedWords(Puzzle puzzle) {
        myInterface.unusedWords();
        int charactersInLine = 0;
        for (Word word : puzzle.getWordList().getWords()) {
            if (!puzzle.getFinalWordList().getWords().contains(word)) {
                if (charactersInLine + word.length() >= MAX_WIDTH) {
                    myInterface.printBlankLine();
                    charactersInLine = 0;
                }
                myInterface.printUnusedWord(word);
                charactersInLine += (word.length() + 2);
            }
        }
    }

}

