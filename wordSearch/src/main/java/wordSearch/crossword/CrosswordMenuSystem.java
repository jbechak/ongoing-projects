package wordSearch.crossword;

import java.util.Map;

public class CrosswordMenuSystem {

    private CrosswordUserInterface ui = new CrosswordUserInterface();
    CrosswordFileHandler fileHandler = new CrosswordFileHandler();
    CrosswordBuildingTools buildingTools = new CrosswordBuildingTools();
    CrosswordPuzzle crosswordPuzzle;
    String puzzleType = "crossword puzzle";

    public void newPuzzleMenu() {
        String title = ui.getTitle(puzzleType);
        crosswordPuzzle = new CrosswordPuzzle(title);
        crosswordPuzzleFromFile();

        ui.printWordList(crosswordPuzzle.getWordList());
        String fileName = buildingTools.createGrid(crosswordPuzzle);
        ui.sayGoodBye(fileName);
        System.exit(0);
    }

    public void crosswordPuzzleFromFile() {
        String filename = ui.getFilename();
        Map<String, String> wordClues = fileHandler.getWordClues(filename);
        crosswordPuzzle.setWordClues(wordClues);
        crosswordPuzzle.populateWordCollection();
        crosswordPuzzle.populateWordList();
    }

}
