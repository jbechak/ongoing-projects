package wordSearch.crossword;

import wordSearch.main.UserInterface;
import wordSearch.puzzle.WordList;

import java.util.Scanner;

public class CrosswordUserInterface extends UserInterface {
    private Scanner keyboard = new Scanner(System.in);

    public String getFilename() {
        System.out.print("Please enter a file name: ");
        return keyboard.nextLine();
    }

    public void printWordList(WordList wordList) {
        System.out.println(wordList);
    }

    public void sayGoodBye(String fileName) {
        System.out.println("Your crossword puzzle has been saved as " + fileName + ".html.\n" +
                "Have a great day. Goodbye!");
    }
}
