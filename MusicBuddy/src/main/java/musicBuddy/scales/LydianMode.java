package musicBuddy.scales;

public class LydianMode extends Scale {
    private static final int[] LYDIAN_MODE_PATTERN = { 2, 2, 2, 1, 2, 2, 1 };
    private String type;

    public LydianMode(String key) {
        super(key, LYDIAN_MODE_PATTERN);
        this.type = "lydian mode";
    }

    @Override
    public String getType() {
        return type;
    }
}
