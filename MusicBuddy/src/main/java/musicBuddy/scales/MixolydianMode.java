package musicBuddy.scales;

public class MixolydianMode extends Scale {
    private static final int[] MIXOLYDIAN_MODE_PATTERN = { 2, 2, 1, 2, 2, 1, 2 };
    private String type;

    public MixolydianMode(String key) {
        super(key, MIXOLYDIAN_MODE_PATTERN);
        this.type = "mixolydian mode";
    }

    @Override
    public String getType() {
        return type;
    }
}
