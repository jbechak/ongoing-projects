package com.zupple.model;

import com.zupple.HtmlEncoder;
import com.zupple.Instructions;
import com.zupple.puzzle.Grid;
import com.zupple.puzzle.Puzzle;
import com.zupple.puzzle.WordList;

import java.util.ArrayList;
import java.util.List;

public class WordSearch {

    private String gridString;
    private int wordSearchId =0;
    private String title;
    private int width;
    private int height;
    private List<String> wordCollection = new ArrayList<>();
    private WordList wordList = new WordList();
    private int wordDirections = 1;
    private int wordCount;
    private String difficulty;
    private String genre = "";
    private String instructions = "";
    private String description = "";
    private String creator = "";
    private String gridPath;
    private String htmlPath;

    private final int FULL_WIDTH = 81;
//    private Grid grid;


//    private boolean isSaved = false;
    //    private WordList finalWordList = new WordList();

    public WordSearch(String title) {
        this.title = title;
    }

    public WordSearch() {}

    public int getWordSearchId() {
        return wordSearchId;
    }

    public void setWordSearchId(int wordSearchId) {
        this.wordSearchId = wordSearchId;
    }



    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public int getWordDirections() {
        return wordDirections;
    }

    public void setWordDirections(int wordDirections) {
        this.wordDirections = wordDirections;
    }

//    public String getWordDirectionsString() {
//        if (wordDirections == 1) {
//            return "Words can go horizontally and vertically";
//        }
//        if (wordDirections == 2) {
//            return "Words can go horizontally, vertically, and in 2 diagonal directions";
//        }
//        if (wordDirections == 3) {
//            return "Words can go horizontally, vertically, diagonally, and backwards in all directions";
//        }
//        return "";
//    }
//
//    public void setGridString(Grid grid) {
//        this.gridString = "\n" + tabToCenter(title) + "\n" + tabToCenter(getDifficulty()) + "\n\n" + instructions + "\n\n" +
//                grid.toString() + "\n\n" + wordList.toString();
//    }

//    public void setGridString(Grid grid) {
//        this.gridString = "" + grid;
//        //this.gridString = grid.toString();
//    }

    public void setGridString(String gridString) {
        this.gridString = gridString;
    }

    public String getGridString() {
        return gridString;
    }

    public int getFULL_WIDTH() {
        return FULL_WIDTH;
    }

    private String tabToCenter(String word) {
        String space = "";
        int spaces = (FULL_WIDTH - word.length()) / 2;
        for (int i = 0; i < spaces; i++) {
            space += " ";
        }
        return space + word;
    }

    public String getGridPath() {
        return gridPath;
    }

    public void setGridPath(String gridPath) {
        this.gridPath = gridPath;
    }

    public List<String> getWordCollection() {
        return wordCollection;
    }

    public void setWordCollection(List<String> wordCollection) {
        this.wordCollection = wordCollection;
    }

    public void populateWordList(List<String> wordCollection) {
        wordList.populateFromStringList(wordCollection);
    }

    public void setWordList(WordList wordList) {
        this.wordList = wordList;
    }

    public WordList getWordList() {
        return wordList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(Grid grid) {
        if (getCredits(grid) >= 8) {
            this.difficulty = "Level 5 - Very Difficult";
        }
        else if (getCredits(grid) >= 6) {
            this.difficulty = "Level 4 - Difficult";
        }
        else if (getCredits(grid) >= 3) {
            this.difficulty = "Level 3 - Medium";
        }
        else if (getCredits(grid) >= 1) {
            this.difficulty = "Level 2 - Easy";
        }
        else this.difficulty = "Level 1 - Very Easy";
    }

    private int getCredits(Grid grid) {
        int credits = 0;
        credits += grid.remainingSpaces() / 50;
        credits += wordDirections - 1;
        credits += (wordCount - 1) / 10;
        return credits;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public void incrementWordCount() {
        wordCount++;
    }

    public void decrementWordCount() {
        wordCount--;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getInstructions() {
        return instructions;
    }

    public void createInstructions() {
        Instructions instructMaker = new Instructions();
        if (wordDirections == 1) {
            this.instructions = instructMaker.getINSTRUCTIONS_1();
        }
        if (wordDirections == 2) {
            this.instructions = instructMaker.getINSTRUCTIONS_2();
        }
        if (wordDirections == 3) {
            this.instructions = instructMaker.getINSTRUCTIONS_3();
        }
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}


//    public String getWsgFilePath() {
//        return filePath + ".wsg";
//    }
//
//    public void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }
//
//    public String getHtmlFilePath() {
//        return filePath + ".html";
//    }

//    public String getDifficulty() {
//        if (getCredits() >= 8) {
//            return "Level 5 - Very Difficult";
//        }
//        if (getCredits() >= 6) {
//            return "Level 4 - Difficult";
//        }
//        if (getCredits() >= 3) {
//            return "Level 3 - Medium";
//        }
//        if (getCredits() >= 1) {
//            return "Level 2 - Easy";
//        }
//        return "Level 1 - Very Easy";
//    }


//    public Grid getGrid() {
//        return grid;
//    }
//
//    public void setGrid(Grid grid) {
//        this.grid = grid;
//    }


//    public boolean isSaved() {
//        return isSaved;
//    }

//    public void setSaved(boolean saved) {
//        isSaved = saved;
//    }

//@Override
//    public String toString() {
//        String puzzleString = "\n" + tabToCenter(title) + "\n" + tabToCenter(getDifficulty()) + "\n\n" + instructions + "\n\n" +
//                grid.toString() + "\n\n" + finalWordList.toString();
//
//        return puzzleString;
//    }

//    public String toHtml(HtmlEncoder htmlEncoder) {
//        String htmlPuzzle = htmlEncoder.htmlHeader() + htmlEncoder.htmlTitle(title, getDifficulty()) +
//                htmlEncoder.htmlInstructions(instructions) + htmlEncoder.htmlGrid(grid) +
//                htmlEncoder.htmlWordList(finalWordList);
//        return htmlPuzzle;
//    }


//    public void setFinalWordList(WordList finalWordList) {
//        this.finalWordList = finalWordList;
//    }
//
//    public WordList getFinalWordList() {
//        return finalWordList;
//    }
