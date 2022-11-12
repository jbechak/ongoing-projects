package musicBuddy;

import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in);
        String choice = "";
        String scaleChoice = "";
        String chordChoice = "";
        NoteGroup.setNoteList();
        MusicTeacher mrMelody = new MusicTeacher();
        String more = "y";

        mrMelody.welcome();

        do {
            choice = mrMelody.offerServices();
            if (choice.equalsIgnoreCase("a")) {
                mrMelody.scales();
            } else if (choice.equalsIgnoreCase("b")) {
                mrMelody.chords();
            } else if (choice.equalsIgnoreCase("c")) {
                mrMelody.melody();
            }

        }   while (!choice.equalsIgnoreCase("q")) ;


        }
    }



