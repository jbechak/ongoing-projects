package musicBuddy.scales;

import musicBuddy.MinorKeys;

public class LocrianMode extends Scale{
    private static final int[] LOCRIAN_MODE_PATTERN = { 1, 2, 2, 1, 2, 2, 2 };
    private static final String[] MINOR_FLAT_KEYS = { "Bb", "C", "D", "Eb", "F", "G" };
    private String type;

    public LocrianMode(String key) {
        super(key, LOCRIAN_MODE_PATTERN);
        this.type = "locrian mode";
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
