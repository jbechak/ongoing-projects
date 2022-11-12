package musicBuddy.scales;

import musicBuddy.scales.NaturalMinorScale;

import java.util.ArrayList;
import java.util.List;

public class HarmonicMinorScale extends NaturalMinorScale {
    private static final int[] HARMONIC_MINOR_SCALE_PATTERN = { 2, 1, 2, 2, 1, 3, 1 };
    private int currentNoteIndex;
    private String key;
    private int j;
    private List<String> resultNotes = new ArrayList<>();
    private String type = "harmonic minor scale";

    public HarmonicMinorScale(String key) {
        super(key, HARMONIC_MINOR_SCALE_PATTERN);
        this.key = key;

        j = 0;
        if (isFlatKey(key)) {
            j = 1;
        }
        currentNoteIndex = startingNoteIndex(key, j);
        for (int i = 0; i < HARMONIC_MINOR_SCALE_PATTERN.length; i++) {
            if ((i == 6 && (key.equals("C#") || key.equals("F#") || key.equals("G#"))) || i == 5 && key.equals("Eb")) {
                resultNotes.add((String) getNoteList().get(currentNoteIndex).get(2));
            } else if (i == 6) {
                resultNotes.add((String) getNoteList().get(currentNoteIndex).get(0));
            } else {
                resultNotes.add((String) getNoteList().get(currentNoteIndex).get(j));
            }
            currentNoteIndex += HARMONIC_MINOR_SCALE_PATTERN[i];

            //from G#/Ab back to A
            if (currentNoteIndex >= 12) {
                currentNoteIndex -= 12;
            }
        }
        resultNotes.add((String) getNoteList().get(currentNoteIndex).get(j));
    }

    @Override
    public String getType() {
        return type;
    }

    public List<String> getResultNotes() {
        return resultNotes;
    }
}
