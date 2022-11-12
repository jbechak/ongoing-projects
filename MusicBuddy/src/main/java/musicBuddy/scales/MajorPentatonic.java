package musicBuddy.scales;

public class MajorPentatonic extends Scale {
    private static final int[] MAJOR_PENTATONIC_PATTERN = { 2, 2, 3, 2, 3 };
    private String type;

    public MajorPentatonic(String key) {
        super(key, MAJOR_PENTATONIC_PATTERN);
        this.type = "major pentatonic scale";
    }

    @Override
    public String getType() {
        return type;
    }
}
