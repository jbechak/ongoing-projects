package musicBuddy.scales;

import musicBuddy.scales.Scale;

public class PhrygianMode extends Scale {
    private static final int[] PHRYGIAN_MODE_PATTERN = { 1, 2, 2, 2, 1, 2, 2 };
    private static final String[] MINOR_FLAT_KEYS = { "Bb", "C", "D", "Eb", "F", "G" };
    private String type = "dorian mode";

    public PhrygianMode(String key) {
        super(key, PHRYGIAN_MODE_PATTERN);
    }

    public PhrygianMode(String key, int[] scalePattern) {
        super(key, scalePattern);
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
