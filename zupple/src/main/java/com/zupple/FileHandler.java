package com.zupple;

import com.zupple.puzzle.Grid;
import com.zupple.puzzle.Puzzle;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    public List<String> createFromFile(String fileName) throws FileNotFoundException {
        List<String> wordCollection = new ArrayList<>();
        File sourceFile = new File(fileName);
        try (Scanner fileScanner = new Scanner(sourceFile)) {

            while(fileScanner.hasNextLine()) {
                String lineOfText = fileScanner.nextLine();
                String[] wordArray = lineOfText.split(" ");
                for (String word : wordArray) {
                    //word = word.replace('-', ' ');
                    wordCollection.add(word.replace('-', ' ').trim());
                }
            }
        } catch (FileNotFoundException e) {
            //System.out.println("File not found");
            throw new FileNotFoundException();
        }
        return wordCollection;
    }

    public Puzzle openPuzzle(Puzzle puzzle) throws FileNotFoundException {

        File sourceFile = new File(puzzle.getWsgFilePath());
        try (Scanner fileScanner = new Scanner(sourceFile)) {

            for (int i = 0; i < puzzle.getWordDirections() + 5; i++) {
                fileScanner.nextLine();
            }

            extractGrid(fileScanner, puzzle);

            for (int i = 0; i < 4; i++) {
                fileScanner.nextLine();
            }
            extractWordList(fileScanner, puzzle);

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        return puzzle;
    }

//    public String getGridFromFile(String filePathString) throws FileNotFoundException {
//        String gridString = "";
//        File sourceFile = new File(filePathString);
//        try (Scanner fileScanner = new Scanner(sourceFile)) {
//            while (fileScanner.hasNext()) {
////                String lineOfText = fileScanner.nextLine();
////                gridString += lineOfText + "\n";
//                gridString += fileScanner.nextLine() + "\n";
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File Not Found");
//        }
//        return gridString;
//
//    }

    public void listFiles() {

        try {
            String currentPath = new java.io.File(".").getCanonicalPath();
            System.out.println("\nYour current directory is " + currentPath);
        } catch (Exception e) {
            System.out.println("bad path");
        }

        System.out.println("\nHere are its contents:");
        File dir = new File(".");
        File[] filesList = dir.listFiles();
        for (File file : filesList) {
            System.out.println(file.getName());
        }

    }

    public void saveWordSearch(String fileName, Puzzle puzzle) {
        File outputFile = new File(fileName + ".wsg");
        try (PrintWriter printWriter = new PrintWriter(outputFile)) {
            printWriter.println(puzzle);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    public void saveHtmlWordSearch(String fileName, Puzzle puzzle, HtmlEncoder htmlEncoder) {
        File outputFile = new File(fileName + ".html");
        try (PrintWriter printWriter = new PrintWriter(outputFile)) {
            printWriter.println(puzzle.toHtml(htmlEncoder));
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    private Puzzle extractGrid(Scanner fileScanner, Puzzle puzzle) {
        Grid grid = new Grid(puzzle.getWidth(), puzzle.getHeight());
        String lineOfText;
        for (int y = 0; y < puzzle.getHeight(); y++) {
            lineOfText = fileScanner.nextLine();
            while (lineOfText.equals("")) {
                lineOfText = fileScanner.nextLine();
            }
            String[] letterArray = lineOfText.split(" ");
            int x = 0;
            for (String letter : letterArray) {
                if (!letter.equals("")) {
                    grid.setBlock(x, y, letter);
                    x++;
                }
            }
        }
        puzzle.setGrid(grid);
        return puzzle;
    }

    private Puzzle extractWordList(Scanner fileScanner, Puzzle puzzle) {
        List<String> wordCollection = new ArrayList<>();
        while(fileScanner.hasNextLine()) {
            String lineOfText = fileScanner.nextLine();
            String[] wordArray = lineOfText.split("  ");
            for (String word : wordArray) {
                if (!word.equals("") && !word.equals(" ")) {
                    wordCollection.add(word.trim());
                }
            }
        }
        puzzle.setWordCollection(wordCollection);
        puzzle.populateWordList(wordCollection);
        return puzzle;
    }

}


//    public void savePrintableHtmlWordSearch(String fileName, Puzzle puzzle) {
//        File outputFile = new File(fileName + ".html");
//        try (PrintWriter printWriter = new PrintWriter(outputFile)) {
//            printWriter.println(puzzle.toPrintableHtml());
//        } catch (FileNotFoundException e) {
//            System.out.println("File Not Found");
//        }
//    }

