package musicBuddy.scales;

import musicBuddy.MinorKeys;

public class MinorPentatonic extends Scale {
    private static final int[] MINOR_PENTATONIC_PATTERN = { 3, 2, 2, 3, 2 };
    private static final String[] MINOR_FLAT_KEYS = { "Bb", "C", "D", "Eb", "F", "G" };
    private String type;

    public MinorPentatonic(String key) {
        super(key, MINOR_PENTATONIC_PATTERN);
        this.type = "minor pentatonic scale";
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
