package com.zupple.main;

import com.zupple.FileHandler;
import com.zupple.HtmlEncoder;
import com.zupple.HtmlPrintEncoder;
import com.zupple.Instructions;
import com.zupple.dao.JdbcPuzzleDao;
import com.zupple.puzzle.Puzzle;
import org.apache.commons.dbcp2.BasicDataSource;

public class EditingTools {
    private UserInterface myInterface = new UserInterface();
    private BasicDataSource dataSource = new BasicDataSource();
    //private PuzzleDao puzzleDao;
    private JdbcPuzzleDao jdbcPuzzleDao = new JdbcPuzzleDao(dataSource);
    private Instructions instructions = new Instructions();

    public void changeDescription() {
    }

    public void changeGenre() {
    }

    public void changeTitle(Puzzle puzzle) {
        String newTitle = myInterface.changeTitle(puzzle.getTitle());
        if (!newTitle.equals("")) {
            puzzle.setTitle(newTitle);
        }
    }

    public void editInstructions(Puzzle puzzle) {
        String currentInstructions = puzzle.getInstructions();
        String choice;
        do {
            choice = myInterface.useTheseInstructions(currentInstructions);
            if (choice.equals("2")) {
                createNewInstructions(puzzle);
            }
            if (choice.equals("3")) {
                BuildingTools buildingTools = new BuildingTools();
//                buildingTools.setInstructions(puzzle);
            }
        } while (!choice.equals("1"));
    }

    public void createNewInstructions(Puzzle puzzle) {
        String instructions = myInterface.enterInstructions();
        puzzle.setInstructions(instructions);
    }

    public boolean lastChance(Puzzle puzzle) {
        if (!puzzle.isSaved()) {
            while (true) {
                String save = myInterface.saveFirst();
                if (save.substring(0, 1).equalsIgnoreCase("y")) {
                    return true;
                }
                if (save.substring(0, 1).equalsIgnoreCase("n")) {
                    saveFile(puzzle);
                    return false;
                }
            }
        }
        return true;
    }

    public void reset(Puzzle puzzle) {
        puzzle.getWordCollection().clear();
        puzzle.getWordList().clearWordList();
        puzzle.getGrid().clearGrid();
        puzzle.setWordCount(0);
        puzzle.setSaved(false);
    }

    public void saveFile(Puzzle puzzle) {
        jdbcConnect();
        FileHandler fileHandler = new FileHandler();
        String fileName = myInterface.enterSavePath();
        puzzle.setFilePath(fileName);
        fileHandler.saveWordSearch(fileName, puzzle);

        HtmlEncoder htmlEncoder = new HtmlEncoder();
        HtmlPrintEncoder printEncoder = new HtmlPrintEncoder();

//        fileHandler.saveHtmlWordSearch(fileName, puzzle, htmlEncoder);
//        fileHandler.saveHtmlWordSearch(fileName + "-printable", puzzle, printEncoder);

        Integer puzzleId = jdbcPuzzleDao.createNewPuzzle(puzzle);
        myInterface.fileSaved(fileName);
        puzzle.setSaved(true);
    }

    public void jdbcConnect() {
        dataSource.setUrl("jdbc:postgresql://localhost:5432/WordSearch");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
    }

}
