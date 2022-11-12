package wordSearch.crossword;

import wordSearch.main.UserInterface;
import wordSearch.puzzle.Puzzle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CrosswordMenuSystem {

    private CrosswordUserInterface ui = new CrosswordUserInterface();
    //private UserInterface generalUi = new UserInterface();
    Map<String, String> wordClues = new LinkedHashMap<>();
    CrosswordFileHandler fileHandler = new CrosswordFileHandler();
    CrosswordCreator crosswordCreator = new CrosswordCreator();
    CrosswordBuildingTools buildingTools = new CrosswordBuildingTools();
    CrosswordPuzzle crosswordPuzzle;
    String puzzleType = "crossword puzzle";

//    public void run() {
//        //String filename = ui.getFilename();
//        CrosswordFileHandler fileHandler = new CrosswordFileHandler();
//        wordClues = fileHandler.getWordClues("cross.txt");
//        System.out.println(wordClues);
//        String[] words = new String[wordClues.size()];
//        List<String[]> wordList = new ArrayList<>();
//        int i = 0;
//
//        for (String key : wordClues.keySet()) {
//            String[] word = new String[key.length()];
//            for (int j = 0; j < word.length; j++) {
//                word[j] = key.substring(j, j + 1);
//            }
//            wordList.add(word);
//        }
//        crosswordCreator.findCommonLetters(wordList);
//    }

    public void mainMenu() {
        String choice;
        do {
            choice = ui.mainMenu();
            if (choice.equals("1")) {
                newPuzzleMenu();
            } else if (choice.equals("2")) {
                exit();
                exit();
            } else if (choice.equals("3")) {
                exit();
            }
        } while (!choice.equals("3"));
    }

    public void newPuzzleMenu() {
        String title = ui.getTitle(puzzleType);
        crosswordPuzzle = new CrosswordPuzzle(title);

        String choice = ui.sourceMenu();
        if (choice.equalsIgnoreCase("1")) {
            crosswordPuzzleFromFile();
        }
//         else {
//            if (choice.equalsIgnoreCase("2")) {
//                buildingTools.wordSearchUserEntry(crosswordPuzzle);
//            }
//        }

        ui.printWordList(crosswordPuzzle.getWordList());
        buildMenu(crosswordPuzzle);
    }

    public void crosswordPuzzleFromFile() {
        String filename = ui.getFilename();
        Map<String, String> wordClues = fileHandler.getWordClues(filename);
        crosswordPuzzle.setWordClues(wordClues);
        crosswordPuzzle.populateWordCollection();
        crosswordPuzzle.populateWordList();
    }

    public void buildMenu(CrosswordPuzzle crosswordPuzzle) {
        String choice;
        do {
            choice = ui.generateAddRemove();
//            if (choice.equals("1")) {
//                buildingTools.wordSearchUserEntry(puzzle);
//            }
//            if (choice.equals("2")) {
//                buildingTools.removeWords(puzzle);
//            }
//            if (choice.equals("3")) {
//                buildingTools.wordDirections(puzzle);
//            }
            if (choice.equals("4")) {
                buildingTools.getDimensions(crosswordPuzzle);
            }
            if (choice.equals("5")) {
                buildingTools.createGrid(crosswordPuzzle);
                editMenu(crosswordPuzzle);
            }
            if (choice.equals("6")) {
                exit();
            }
        } while (!choice.equals("6"));
    }

    public void editMenu(CrosswordPuzzle crosswordPuzzle) {
        String choice;
        do {
            choice = ui.saveOrExit();
//            if (choice.equals("1")) {
//                editingTools.saveFile(puzzle);
//            }
            if (choice.equals("2")) {
                buildMenu(crosswordPuzzle);
            }
//            if (choice.equals("3")) {
//                editDetails(puzzle);
//            }
//            if (choice.equals("4")) {
//                myInterface.learnAboutDifficulty();
//            }
//            if (choice.equals("5")) {
//                if (editingTools.lastChance(puzzle)) {
//                    editingTools.reset(puzzle);
//                    mainMenu();
//                }
//            }
//            if (choice.equals("6")) {
//                if (editingTools.lastChance(puzzle)) {
//                    exit();
//                }
//            }
        }
        while (!choice.equals("6"));
    }

    public void editDetails(Puzzle puzzle) {
        String choice;
        do {
            choice = ui.editDetailsMenu();
//            if (choice.equals("1")) {
//                editingTools.changeTitle(puzzle);
//            } else if (choice.equals("2")) {
//                editingTools.editInstructions(puzzle);
//            } else if (choice.equals("3")) {
//                editingTools.changeDescription();
//            } else if (choice.equals("4")) {
//                editingTools.changeGenre();
//            } else if (choice.equals("5")) {
//                editMenu(puzzle);
//            }
        } while (!choice.equals("5"));
    }

    public void exit() {
        ui.sayGoodBye();
        System.exit(0);
    }

}
