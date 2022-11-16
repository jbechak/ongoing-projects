package wordSearch.main;

import wordSearch.puzzle.Puzzle;
import wordSearch.puzzle.Word;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner keyboard = new Scanner(System.in);

    public String whatKindOfProject() {
        System.out.print("\nMAIN MENU\n" +
                "1. Word Search Generator\n" +
                "2. Crossword Puzzle Generator\n" +
                "3. Exit\n\n");
        return chooseAnOption();
    }

    public void welcome() {
        System.out.println("\nWELCOME TO WORD SEARCH GENERATOR\n");
    }

    public String mainMenu() {
        System.out.print("\nMAIN MENU\n" +
                "1. Create a new word search\n" +
                "2. Open an existing word search - *CURRENTLY OUT OF ORDER*\n" +
                "3. Exit\n\n");
        return chooseAnOption();
    }

    public void puzzleTableHeader() {
        System.out.println("\nPuzzle\tPuzzle\t\t\t\tDifficulty\t\tWord");
        System.out.println("Number\tTitle\t\t\t\tLevel\t\t\tCount");
    }

    public void displayStoredPuzzle(Puzzle puzzle, String titleSpace) {
        System.out.println(puzzle.getPuzzleId() + "\t\t" + puzzle.getTitle() + titleSpace + puzzle.getDifficulty() + "\t\t\t" + puzzle.getWordCount());
    }

    public String whichPuzzle() {
        System.out.print("\nPlease, enter the number of the puzzle you'd like to open\n" +
                "(or '0' to go back, 'S' to sort and filter): ");
        return keyboard.nextLine();
    }

    public String sortMenu() {
        System.out.print("\nWhat would you like to do?\n\n" +
                "Sort by:\t\t\t\tFilter by:\n" +
                "1. Puzzle Title\t\t\t4. Puzzle Title\n" +
                "2. Difficulty Level\t\t5. Difficulty Level\n" +
                "3. Word Count\t\t\t6. Word Count\n\n" +
                "0. Go Back\n\n");
        return chooseAnOption();
    }

    public String getTitle(String puzzleType) {
        System.out.print("\n\nSTEP 1: Create a Title\n" +
                "What would you like to name your " + puzzleType + "? ");
        return keyboard.nextLine();
    }

    public String sourceMenu() {
        System.out.println("\n\nSTEP 2: Populate Your Word Search:\n\n" +
                "How would you like to get your words?\n" +
                "1. From a text file\n" +
                "2. Input words manually\n");
        return chooseAnOption();
    }

    public String enterOpenPath() {
        System.out.print("\nPlease, enter the name of your source file: ");
        return keyboard.nextLine();
    }

    public void invalidNumber() {
        System.out.println("Please enter a valid number");
    }

    public String getAnotherWordFromUser(int wordCount) {
        System.out.print("Enter a word, or press enter to move on. (" + wordCount + " words so far): ");
        return keyboard.nextLine();
    }

    public void notAWord() {
        System.out.println("That's not a valid word. Please try again.");
    }

    public void duplicate() {
        System.out.println("That's a duplicate. Please enter a unique word.");
    }

    public void displayWordList(List<String> wordCollection) {
        System.out.println("\nHere are the words you entered:");
        int counter = 0;
        for (String word : wordCollection) {
            System.out.print(word + "\t\t");
            counter++;
            if (counter >= 3) {
                System.out.println();
                counter = 0;
            }
        }
        System.out.println();
    }

    public void printAddedWordCount(int addedWords, int totalWords) {
        System.out.println("\n" + addedWords + " out of " + totalWords + " words have been added to the puzzle.");
    }

    public void suggestLargerDimensions() {
        System.out.println("Try larger dimensions if you'd like to fit more words.");
    }

    public void unusedWords() {
        System.out.println("\nThese words didn't make it into the puzzle: ");
    }

    public void printUnusedWord(Word word) {
        System.out.print(word + "  ");
    }

    public void hereAreTheWords(int wordAmount) {
        System.out.println("\nYou entered " + wordAmount + " words:");
    }

    public void printWord(String word, String space) {
        System.out.print(word + space);
    }

    public void displayNumberedWordList(List<String> wordCollection) {
        System.out.println("\nHere are the words you entered:");
        for (int i = 0; i < wordCollection.size(); i++) {
            System.out.println((i + 1) + ". " + wordCollection.get(i));
        }
        System.out.println();
    }

    public String chooseWordsToRemove() {
        System.out.print("Enter the number(s) corresponding to the word(s) that you'd like to remove: ");
        return keyboard.nextLine();
    }

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



    public String wordDirectionsMenu() {
        System.out.println("\nWORD DIRECTION OPTIONS\n" +
                "1. Words can go horizontally and vertically\n" +
                "2. Words can go horizontally, vertically, and in 2 diagonal directions\n" +
                "3. Words can go horizontally, vertically, diagonally, and backwards in all directions\n");
        return chooseAnOption();
    }

    public String useTheseInstructions(String instructions) {
        System.out.print("Currently, the instructions for your puzzle are:\n" + instructions +
                "\n\n1. Keep these instructions" +
                "\n2. Create new instructions" +
                "\n3. Reset to default instructions\n");
        return chooseAnOption();
    }

    protected String chooseAnOption() {
        System.out.print("Please choose an option: ");
        return keyboard.nextLine();
    }

    public String enterInstructions() {
        System.out.println("Please input the instructions for your puzzle and press enter when finished:");
        return keyboard.nextLine();
    }

    public String enterSavePath() {
        System.out.print("\nPlease, enter the name of your save file (with no extension): ");
        return keyboard.nextLine();
    }

    public String dimensions(int longestWord) {
        System.out.print("\nPlease, enter the desired dimensions (width x height) of your word search\n" +
                "(eg. 20 x 20 | 20 * 20 | 20 20) minimum " + longestWord + " on either side: ");
        return keyboard.nextLine();
    }

    public String saveOrExit() {
        System.out.print("\nWhat would you like to do next?\n" +
                "1. Save word search\n" +
                "2. Edit words, word directions, or size, and regenerate word search\n" +
                "3. Edit word search details\n" +
                "4. Learn how to adjust the difficulty level\n" +
                "5. Build or open a new word search\n" +
                "6. Exit\n\n");
        return chooseAnOption();
    }

    public void learnAboutDifficulty() {
        System.out.println("\nThe difficulty of your puzzle is shown at the top, just under the title. The puzzle difficulty\n" +
                            "is dependent upon 3 things: 1.) the number of excess letters (meaning letters that are not\n" +
                            "part of the words in the word list) 2.) the number of word directions 3.) and the total number\n" +
                            "of words. To adjust the difficult of your puzzle, type '2' to change these aspects.");
    }

    public String editDetailsMenu() {
        /*
        title
        instructions
        description
        genre
         */
        System.out.print("\nEDIT DETAILS MENU\n" +
                "1. Change title\n" +
                "2. Edit instructions\n" +
                "3. Enter/change description\n" +
                "4. Enter/change genre\n" +
                "5. Return to previous menu\n\n");
        return chooseAnOption();
    }

    public String changeTitle(String oldTitle) {
        System.out.println("The current title is " + oldTitle + ".");
        System.out.print("Please enter a new title, or press enter to keep " + oldTitle + ": ");
        return keyboard.nextLine();
    }

    public void printArea(int area) {
        System.out.println("The area of this puzzle is: " + area);
    }

    public void fileSaved(String fileName) {
        System.out.println("Your word search has been saved to " + fileName + ".wsg");
    }

    public String saveFirst() {
        System.out.print("Would to like to continue without saving?\n" +
                "All data will be lost. (Y/N): ");
        return keyboard.nextLine();
    }

    public void printBlankLine() {
        System.out.println();
    }

    public void sayGoodBye(){
        System.out.println("Goodbye!");
    }
}
