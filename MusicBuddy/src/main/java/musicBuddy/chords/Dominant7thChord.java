package musicBuddy.chords;

import musicBuddy.chords.Chord;

public class Dominant7thChord extends Chord {
    private static final int[] DOM7_CHORD_PATTERN = { 4, 3, 3 };
    private String type = "dominant 7th chord";

    public Dominant7thChord(String key) {
        super(key, DOM7_CHORD_PATTERN);
        this.type = "dominant 7th chord";
    }

    @Override
    public String getType() {
        return type;
    }
}
