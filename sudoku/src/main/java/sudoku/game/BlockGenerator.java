package sudoku.game;

import sudoku.game.shapes.Row;
import sudoku.game.shapes.TripleRow;

import java.util.Scanner;

public class BlockGenerator {
    Board board = new Board();
    UserInterface myUI = new UserInterface();
    Scanner keyboard = new Scanner(System.in);
    FileHandler fileHandler = new FileHandler();
    int blanks;

    public void run() {
        String difficultyString = myUI.difficultyLevel();
        int difficulty = Integer.parseInt(difficultyString);
        blanks = difficulty * 6;
        createBoard();
        board.setLevel(difficulty);
        System.out.println();
        System.out.println(board);
        board.createBlanks(blanks);
        System.out.println();
        System.out.println(board);
        String fileName = myUI.getFileName();
        //fileHandler.savePuzzle(board, fileName + ".txt");
        fileHandler.saveHtmlPuzzle(board.toHtml(), fileName + ".html");


    }

    public void createBoard() {
        initialBoardSetup();
        populateRows();
        board.putAllBlocksInList();
    }

    public void initialBoardSetup() {
        Row.populateListOf9();
        putRowsInBoard();
        putTripleRowsInBoard();

        board.createAllColumns();
        board.createAllSquares();
        board.updateColumns(0);
        board.updateSquares(0);
    }

    public void putRowsInBoard() {
        for (int i = 0; i < 9; i++) {
            Row row = new Row(i);
            board.addRow(row);
        }
    }

    public void putTripleRowsInBoard() {
        for (int i = 0, j = 0 ; i < 7; i += 3, j++) {
            TripleRow tripleRow = new TripleRow(i);
            tripleRow.addRow(board.getRow(i));
            tripleRow.addRow(board.getRow(i + 1));
            tripleRow.addRow(board.getRow(i + 2));
            board.addTripleRow(tripleRow);
        }
    }

    public void populateRows() {
        boolean success = false;
        do {
            success = create0to2();
        }
        while (!success);

        do {
            success = create3to5();
        } while (!success);

        do {
            success = create6to7();
        } while (!success);
        board.getRow(8).alternateRow9Builder(board);
    }

    public boolean create0to2() {
        boolean success = false;
        for (int i = 1; i <= 2; i++) {
            success = board.getRow(i).shuffleRowUntilGood(board);
            if (!success) {
                return false;
            }
            board.updateColumns(i);
            board.updateSquares(i);
        }
        return true;
    }

    public boolean create3to5() {
        boolean success = false;
        for (int i = 3; i <= 5; i++) {
            success = board.getRow(i).shuffleRowUntilGood(board);
            if (!success) {
                return false;
            }
            board.updateColumns(i);
            board.updateSquares(i);
        }
        return true;
    }

    public boolean create6to7() {
        boolean success = false;
        for (int i = 6; i <= 7; i++) {
            success = board.getRow(i).shuffleRowUntilGood(board);
            if (!success) {
                return false;
            }
            board.updateColumns(i);
            board.updateSquares(i);
        }
        return true;
    }

}
