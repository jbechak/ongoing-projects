package musicBuddy.chords;

import musicBuddy.NoteGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class Chord extends NoteGroup {
    //private int[] chordPattern;
    private List<String> chordTones = new ArrayList<>();

    public Chord(String key, int[] chordPattern) {
        super(key, chordPattern);
    }

}
