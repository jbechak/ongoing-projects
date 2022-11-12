package sudoku.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class FileHandler {

    public void savePuzzle(Board board, String fileName) {
        File filePath = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(filePath)) {
            printWriter.println("\t\t\t Level " + board.getLevel() + " Puzzle\n");
            printWriter.println(board);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }
    public void saveHtmlPuzzle(String board, String fileName) {
        File filePath = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(filePath)) {
            printWriter.println(board);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

}
