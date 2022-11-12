package musicBuddy;

import java.util.ArrayList;
import java.util.List;

public class Melody extends NoteGroup{
    private int[] intervalPattern;
    private int currentNoteIndex;
    String key = "";
    private int j;
    private List<String> resultNotes = new ArrayList<>();
    private int keyIndex = 0;
    private String type = "melody";

    public Melody(String key, int[] intervalPattern) {
        super(key);

        this.key = key;
        this.intervalPattern = intervalPattern;

        j = 0;
        if (isFlatKey(key)) {
            j = 1;
        }
        keyIndex = startingNoteIndex(key, j);
        currentNoteIndex = keyIndex + intervalPattern[0];
        currentNoteIndex = keepWithinOctave(currentNoteIndex);

        for (int i = 1; i < intervalPattern.length; i++) {
            resultNotes.add((String) getNoteList().get(currentNoteIndex).get(j));
            currentNoteIndex += intervalPattern[i];
            currentNoteIndex = keepWithinOctave(currentNoteIndex);
        }
        resultNotes.add((String) getNoteList().get(currentNoteIndex).get(j));
    }


    public List<String> getResultNotes() {
        return resultNotes;
    }

    @Override
    public String getType() {
        return type;
    }
}
