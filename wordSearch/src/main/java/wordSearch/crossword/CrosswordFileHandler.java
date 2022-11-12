package wordSearch.crossword;

import wordSearch.HtmlEncoder;
import wordSearch.HtmlPrintEncoder;
import wordSearch.puzzle.Puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CrosswordFileHandler {

    public Map<String, String> getWordClues(String filename) {
        Map<String, String> wordClues = new LinkedHashMap<>();
        File fileToOpen = new File(filename);
        try (Scanner fileScanner = new Scanner(fileToOpen)) {
            while (fileScanner.hasNextLine()) {
                String lineString = fileScanner.nextLine();
                String[] separated = lineString.split("-");
                wordClues.put(separated[0].trim(), separated[1]);

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return wordClues;
    }

    public void saveHtmlCrosswordPuzzle(String fileName, CrosswordPuzzle puzzle, HtmlPrintEncoder htmlEncoder) {
        File outputFile = new File(fileName + ".html");
        try (PrintWriter printWriter = new PrintWriter(outputFile)) {
            printWriter.println(htmlEncoder.crossWordHtml(puzzle));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

}
