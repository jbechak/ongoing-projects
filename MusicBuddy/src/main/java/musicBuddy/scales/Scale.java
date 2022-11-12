package musicBuddy.scales;

import musicBuddy.NoteGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class Scale extends NoteGroup {
    private int[] scalePattern;
    private List<String> scaleNotes = new ArrayList<>();

    public Scale(String key, int[] scalePattern) {
        super(key, scalePattern);

    }


}
