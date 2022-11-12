package com.zupple.crossword;

import com.zupple.main.UserInterface;
import com.zupple.puzzle.WordList;

import java.util.Scanner;

public class CrosswordUserInterface extends UserInterface {

    private Scanner keyboard = new Scanner(System.in);

    @Override
    public String mainMenu() {
        System.out.print("\nMAIN MENU\n" +
                "1. Create a new crossword puzzle\n" +
                "2. Open an existing word search\n" +
                "3. Exit\n\n");
        return chooseAnOption();
    }

    public String getFilename() {
        System.out.print("Please enter a file name: ");
        return keyboard.nextLine();
    }

    @Override
    public String sourceMenu() {
        System.out.println("\n\nSTEP 2: Populate Your Crossword Puzzle:\n\n" +
                "How would you like to get your words and clues?\n" +
                "1. From a text file\n" +
                "2. Input words manually\n");
        return chooseAnOption();
    }

    public void printWordList(WordList wordList) {
        System.out.println(wordList);
    }

    @Override
    public String generateAddRemove() {
        System.out.print("\nBUILD MENU\n" +
                "1. Add more words\n" +
                "2. Remove words\n" +
                "3. Set word directions\n" +
                "4. Set size\n" +
                "5. Generate Word Search\n" +
                "6. Exit\n\n");
        return chooseAnOption();
    }





}
