package musicBuddy.chords;

import musicBuddy.chords.Chord;

public class MinorChord extends Chord {
    private static final int[] MINOR_CHORD_PATTERN = { 3, 4 };
    private static final String[] MINOR_FLAT_KEYS = { "Bb", "C", "D", "Eb", "F", "G" };
    private String type = "minor chord";

    public MinorChord(String key) {
        super(key, MINOR_CHORD_PATTERN);
    }

    public boolean isFlatKey(String key) {
        for (String flatKey : MINOR_FLAT_KEYS) {
            if (key.equals(flatKey)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getType() {
        return type;
    }
}

