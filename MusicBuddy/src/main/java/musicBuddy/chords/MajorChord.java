package musicBuddy.chords;

import musicBuddy.chords.Chord;

public class MajorChord extends Chord {
    private static final int[] MAJOR_CHORD_PATTERN = { 4, 3 };
    private String type = "major chord";

    public MajorChord(String key) {
        super(key, MAJOR_CHORD_PATTERN);
    }

    @Override
    public String getType() {
        return type;
    }
}
