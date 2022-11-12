package musicBuddy.scales;

public class MajorScale extends Scale {
    private static final int[] MAJOR_SCALE_PATTERN = { 2, 2, 1, 2, 2, 2, 1 };
    private String type;

    public MajorScale(String key) {
        super(key, MAJOR_SCALE_PATTERN);
        this.type = "major scale";
    }

    @Override
    public String getType() {
        return type;
    }
}
