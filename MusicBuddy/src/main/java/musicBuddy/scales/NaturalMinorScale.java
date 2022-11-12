package musicBuddy.scales;

import musicBuddy.scales.Scale;

public class NaturalMinorScale extends Scale {
    private static final int[] NATURAL_MINOR_SCALE_PATTERN = { 2, 1, 2, 2, 1, 2, 2 };
    private static final String[] MINOR_FLAT_KEYS = { "Bb", "C", "D", "Eb", "F", "G" };
    private String type = "natural minor scale";

    public NaturalMinorScale(String key) {
        super(key, NATURAL_MINOR_SCALE_PATTERN);
    }

    public NaturalMinorScale(String key, int[] scalePattern) {
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
