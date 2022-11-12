package musicBuddy;

import musicBuddy.chords.*;
import musicBuddy.scales.*;

import java.util.Scanner;

public class MusicTeacher {
    //private Scanner getInput = new Scanner(System.in);
    private String choice = "";
    private String keyChoice = "";
    private String more = "";
    private String doMore = "y";

    public MusicTeacher () {

    }

    public void welcome() {
        System.out.println("\nWelcome to music Buddy! My name is Mr. Melody.");

    }
    public String offerServices() {
        Scanner getInput = new Scanner(System.in);
        System.out.print("\nWhat can I help you with?\n(a) Scales and Modes\t(b) Chords\t\t(c) Melody\t\t(q) Quit : ");
        choice = getInput.nextLine();
        return choice;
    }

    public void scales() {
        do {
            Scanner getInput = new Scanner(System.in);
            System.out.print("\nWhich type of scale are you wondering about?\n" +
                    "(a) Major\t(b) Natural Minor\t(c) Harmonic Minor\n" +
                    "(d) Dorian\t(e) Phrygian\t(f) Lydian\t(g) Mixolydian\t(h) Locrian\n" +
                    "(i) Major Pentatonic\t(j) Minor Pentatonic : ");
            choice = getInput.nextLine();

            System.out.print("\nWhich key? ");
            keyChoice = getInput.next();

            if (choice.equalsIgnoreCase("a")) {
                MajorScale newScale = new MajorScale(keyChoice);
                newScale.printResultNotes();
            } else if (choice.equalsIgnoreCase("b")) {
                NaturalMinorScale newScale = new NaturalMinorScale(keyChoice);
                newScale.printResultNotes();
            } else if (choice.equalsIgnoreCase("c")) {
                HarmonicMinorScale newScale = new HarmonicMinorScale(keyChoice);
                newScale.printResultNotes();
            } else if (choice.equalsIgnoreCase("d")) {
                DorianMode newScale = new DorianMode(keyChoice);
                newScale.printResultNotes();
            } else if (choice.equalsIgnoreCase("e")) {
                PhrygianMode newScale = new PhrygianMode(keyChoice);
                newScale.printResultNotes();
            }else if (choice.equalsIgnoreCase("f")) {
                LydianMode newScale = new LydianMode(keyChoice);
                newScale.printResultNotes();
            } else if (choice.equalsIgnoreCase("g")) {
                MixolydianMode newScale = new MixolydianMode(keyChoice);
                newScale.printResultNotes();
            } else if (choice.equalsIgnoreCase("h")) {
                LocrianMode newScale = new LocrianMode(keyChoice);
                newScale.printResultNotes();
            } else if (choice.equalsIgnoreCase("i")) {
                MajorPentatonic newScale = new MajorPentatonic(keyChoice);
                newScale.printResultNotes();
            } else if (choice.equalsIgnoreCase("j")) {
                MinorPentatonic newScale = new MinorPentatonic(keyChoice);
                newScale.printResultNotes();
            }

            doMore = doAnother("scale");
        } while (doMore.equalsIgnoreCase("y"));
    }

    public void chords() {
        do {
            Scanner getInput = new Scanner(System.in);
            System.out.print("\nWhich type of chord are you wondering about?\n" +
                    "(a) Major\t(b) Minor\t(c) Major7\t(d) Dominant7\t(e) Minor7 : ");
            choice = getInput.nextLine();

            System.out.print("\nWhich key? ");
            keyChoice = getInput.next();

            if (choice.equalsIgnoreCase("a")) {
                MajorChord newChord = new MajorChord(keyChoice);
                newChord.printResultNotes();
            } else if (choice.equalsIgnoreCase("b")) {
                MinorChord newChord = new MinorChord(keyChoice);
                newChord.printResultNotes();
            } else if (choice.equalsIgnoreCase("c")) {
                Major7Chord newChord = new Major7Chord(keyChoice);
                newChord.printResultNotes();
            } else if (choice.equalsIgnoreCase("d")) {
                Dominant7thChord newChord = new Dominant7thChord(keyChoice);
                newChord.printResultNotes();
            } else if (choice.equalsIgnoreCase("e")) {
                Minor7Chord newChord = new Minor7Chord(keyChoice);
                newChord.printResultNotes();
            }
            doMore = doAnother("chord");
        } while (doMore.equalsIgnoreCase("y"));
    }

    public void melody() {

        Scanner getInput = new Scanner(System.in);
        System.out.print("\nWhat key is your melody in? ");
        keyChoice = getInput.nextLine();

        System.out.println("OK, key of " + keyChoice + ". Please type in your melody (with spaces between the note names): ");
        String melodyString = getInput.nextLine();
        int[] intervalPattern = melodyToIntervals(melodyString);
        Melody userMelody = new Melody(keyChoice, intervalPattern);
        do {
            getInput = new Scanner(System.in);
            System.out.print("\nWhat key would you like to transpose your melody to? ");
            keyChoice = getInput.nextLine();
            Melody transposedMelody = new Melody(keyChoice, intervalPattern);
            transposedMelody.printResultNotes();
            //System.out.println(transposedMelody.getResultNotes());
            doMore = transposeAgain();
        } while (doMore.equalsIgnoreCase("y"));

    }

    public int[] melodyToIntervals(String melodyString) {
        int currentNoteIndex = 0;
        String[] melodyArray = melodyString.split(" ");
        int[] intervalPattern = new int[melodyArray.length];

        int keyIndex = NoteGroup.getKeyIndex(keyChoice);
        int startIndex = NoteGroup.getKeyIndex(melodyArray[0]);

        intervalPattern[0] = startIndex - keyIndex;
        currentNoteIndex = startIndex;

        for (int i = 1; i < melodyArray.length; i++) {
            int nextNoteIndex = NoteGroup.getKeyIndex(melodyArray[i]);
            intervalPattern[i] = nextNoteIndex - currentNoteIndex;
            currentNoteIndex = nextNoteIndex;
        }
        return intervalPattern;
    }

    public String doAnother(String musicalElement) {
        Scanner getInput = new Scanner(System.in);
        System.out.print("\nWould you like to know about another " + musicalElement + " (Y/N)? ");
        doMore = getInput.nextLine();
        return doMore;
    }

    public String transposeAgain() {
        Scanner getInput = new Scanner(System.in);
        System.out.print("\nWould you like to transpose your melody to another key (Y/N)? ");
        doMore = getInput.nextLine();
        return doMore;
    }



    public String keepGoing() {
        Scanner getInput = new Scanner(System.in);
        System.out.print("Would you like to keep going (y)es or (n)o? ");
        more = getInput.nextLine();
        return more;
    }

    public String getKeyChoice() {
        return keyChoice;
    }
}
