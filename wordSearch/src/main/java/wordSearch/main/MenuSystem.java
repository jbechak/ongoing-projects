package wordSearch.main;

import org.apache.commons.dbcp2.BasicDataSource;
import wordSearch.*;
import wordSearch.crossword.CrosswordMenuSystem;
import wordSearch.dao.JdbcPuzzleDao;
import wordSearch.dao.PuzzleDao;
import wordSearch.puzzle.Puzzle;

import java.io.FileNotFoundException;
import java.util.*;

public class MenuSystem {
    private FileHandler myFileHandler = new FileHandler();
    private UserInterface myInterface = new UserInterface();
    private CrosswordMenuSystem crosswordMenuSystem = new CrosswordMenuSystem();
    private Puzzle puzzle;
    private BuildingTools buildingTools = new BuildingTools();
    private EditingTools editingTools = new EditingTools();

    private BasicDataSource dataSource = new BasicDataSource();
    private PuzzleDao puzzleDao;
    private JdbcPuzzleDao jdbcPuzzleDao = new JdbcPuzzleDao(dataSource);
    String puzzleType = "word search";


    public void run() {
        dataSource.setUrl("jdbc:postgresql://localhost:5432/WordSearch");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        myInterface.welcome();
        projectSelection();
        mainMenu();
    }

    public void projectSelection() {
        String choice;
        do {
            choice = myInterface.whatKindOfProject();
            if (choice.equals("1")) {
                mainMenu();
            } else if (choice.equals("2")) {
                crosswordMenuSystem.newPuzzleMenu();
            } else if (choice.equals("3")) {
                exit();
            }

        } while (!choice.equals("3"));
    }

    public void mainMenu() {
        String choice;
        do {
            choice = myInterface.mainMenu();
            if (choice.equals("1")) {
                newPuzzleMenu();
            } else if (choice.equals("2")) {
                listPuzzles();
                openPuzzleMenu();
            } else if (choice.equals("3")) {
                exit();
            }
        } while (!choice.equals("3"));
    }

    public void listPuzzles() {
        List<Puzzle> puzzles = jdbcPuzzleDao.getAllPuzzles();
        myInterface.puzzleTableHeader();
        for (Puzzle thisPuzzle : puzzles) {
            myInterface.displayStoredPuzzle(thisPuzzle, space(thisPuzzle.getTitle()));
        }
    }

    public void openPuzzleMenu() {
        boolean success = false;
        int puzzleId = 0;
        do {
            String choice = myInterface.whichPuzzle();
            if (choice.equalsIgnoreCase("s")) {
                sort();
            }
            try {
                puzzleId = Integer.parseInt(choice);
                success = true;
            } catch (NumberFormatException e) {
                myInterface.invalidNumber();
            }
        } while (!success);
        if (puzzleId == 0) {
            return;
        }
        puzzle = jdbcPuzzleDao.getPuzzle(puzzleId);
        try {
            puzzle = myFileHandler.openPuzzle(puzzle);
            System.out.println(puzzle);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        editMenu(puzzle);
    }

    public void sort() {
        String choice = myInterface.sortMenu();
        //String column = "";
        List<Puzzle> puzzles = new ArrayList<>();
        if (choice.equals("1")) {
            puzzles = jdbcPuzzleDao.getAllPuzzles();
        } else if (choice.equals("2")) {
            puzzles = jdbcPuzzleDao.sortPuzzlesByDifficulty();
        } else if (choice.equals("3")) {
            puzzles = jdbcPuzzleDao.sortPuzzlesByWordCount();
//        } else {
//
//            if (choice.equals("4")) {
//
//            } else if (choice.equals("5")) {
//
//            } else if (choice.equals("6")) {
//
//            }
        }

        myInterface.puzzleTableHeader();
        for (Puzzle thisPuzzle : puzzles) {
            myInterface.displayStoredPuzzle(thisPuzzle, space(thisPuzzle.getTitle()));
        }
        openPuzzleMenu();

    }

    public void newPuzzleMenu() {
        String title = myInterface.getTitle(puzzleType);
        puzzle = new Puzzle(title);

        String choice = myInterface.sourceMenu();
        if (choice.equalsIgnoreCase("1")) {
            wordSearchFromFile();
        } else {
            if (choice.equalsIgnoreCase("2")) {
                buildingTools.wordSearchUserEntry(puzzle);
            }
        }
        displayWordList(puzzle.getWordCollection());
        buildMenu(puzzle);
    }

    public void buildMenu(Puzzle puzzle) {
        String choice;
        do {
            choice = myInterface.generateAddRemove();
            if (choice.equals("1")) {
                buildingTools.wordSearchUserEntry(puzzle);
            }
            if (choice.equals("2")) {
                buildingTools.removeWords(puzzle);
            }
            if (choice.equals("3")) {
                buildingTools.wordDirections(puzzle);
            }
            if (choice.equals("4")) {
                buildingTools.getDimensions(puzzle);
            }
            if (choice.equals("5")) {
                buildingTools.createGrid(puzzle);
                editMenu(puzzle);
            }
            if (choice.equals("6")) {
                exit();
            }
        } while (!choice.equals("6"));
    }

    public void editMenu(Puzzle puzzle) {
        String choice;
        do {
            choice = myInterface.saveOrExit();
            if (choice.equals("1")) {
                editingTools.saveFile(puzzle);
            }
            if (choice.equals("2")) {
                buildMenu(puzzle);
            }
            if (choice.equals("3")) {
                editDetails(puzzle);
            }
            if (choice.equals("4")) {
                myInterface.learnAboutDifficulty();
            }
            if (choice.equals("5")) {
                if (editingTools.lastChance(puzzle)) {
                    editingTools.reset(puzzle);
                    mainMenu();
                }
            }
            if (choice.equals("6")) {
                if (editingTools.lastChance(puzzle)) {
                    exit();
                }
            }
        }
        while (!choice.equals("6"));
    }

    public void editDetails(Puzzle puzzle) {
        String choice;
        do {
            choice = myInterface.editDetailsMenu();
            if (choice.equals("1")) {
                editingTools.changeTitle(puzzle);
            } else if (choice.equals("2")) {
                editingTools.editInstructions(puzzle);
            } else if (choice.equals("3")) {
                editingTools.changeDescription();
            } else if (choice.equals("4")) {
                editingTools.changeGenre();
            } else if (choice.equals("5")) {
                editMenu(puzzle);
            }
        } while (!choice.equals("5"));
    }

    public void displayWordList(List<String> wordCollection) {
        myInterface.hereAreTheWords(wordCollection.size());
        int counter = 0;
        for (String word : wordCollection) {
            myInterface.printWord(word, space(word));
            counter++;
            if (counter >= 3) {
                myInterface.printBlankLine();
                counter = 0;
            }
        }
        System.out.println();
    }

    public String space(String word) {
        String space = "";
        final int MAX_SPACES = 20;
        int spaces = MAX_SPACES - word.length();
        for (int i = 0; i < spaces; i++) {
            space += " ";
        }
        return space;
    }

    public void wordSearchFromFile() {
        boolean completed = false;
        List<String> wordCollection = new ArrayList<>();
        do {
            myFileHandler.listFiles();
            String choice = myInterface.enterOpenPath();
            try {
                wordCollection = myFileHandler.createFromFile(choice);
                removeDuplicates(wordCollection);
                completed = true;
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        }
        while (!completed);
        puzzle.setWordCollection(wordCollection);
    }

    public void exit() {
        myInterface.sayGoodBye();
        System.exit(0);
    }

    public List<String> removeDuplicates(List<String> wordCollection) {
        for (int i = 0; i < wordCollection.size(); i++) {
            if (wordCollection.get(i).equalsIgnoreCase("") || wordCollection.get(i).equalsIgnoreCase(" ")) {
                wordCollection.remove(i);
                continue;
            }
            for (int j = i + 1; j < wordCollection.size(); j++)
                if (wordCollection.get(j).equalsIgnoreCase(wordCollection.get(i))) {
                    wordCollection.remove(j);
                }

        }
        return wordCollection;
    }

}
