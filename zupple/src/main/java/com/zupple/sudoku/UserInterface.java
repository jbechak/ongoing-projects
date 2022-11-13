package com.zupple.sudoku;

import java.util.Scanner;

public class UserInterface {
    private Scanner keyboard = new Scanner(System.in);

    public String difficultyLevel() {
        System.out.print("What level of difficulty would you like (1 - 10)? ");
        return keyboard.nextLine();
    }

    public String getFileName() {
        System.out.print("Please enter a file name to save the puzzle to: ");
        return keyboard.nextLine();
    }
}
