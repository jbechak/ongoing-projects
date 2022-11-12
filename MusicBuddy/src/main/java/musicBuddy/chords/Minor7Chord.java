package musicBuddy.chords;

import musicBuddy.chords.Chord;

public class Minor7Chord extends Chord {
    private static final int[] MIN7_CHORD_PATTERN = { 3, 4, 3 };
    private String type = "minor 7th chord";
    private static final String[] MINOR_FLAT_KEYS = { "Bb", "C", "D", "Eb", "F", "G" };

    public Minor7Chord(String key) {
        super(key, MIN7_CHORD_PATTERN);
        this.type = "minor 7th chord";
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
