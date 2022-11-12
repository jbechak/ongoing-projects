package musicBuddy.chords;

import musicBuddy.chords.Chord;

public class Major7Chord extends Chord {
    private static final int[] MAJ7_CHORD_PATTERN = { 4, 3, 4 };
    private String type = "major 7th chord";

    public Major7Chord(String key) {
        super(key, MAJ7_CHORD_PATTERN);
        this.type = "major 7th chord";
    }

    @Override
    public String getType() {
        return type;
    }
}
