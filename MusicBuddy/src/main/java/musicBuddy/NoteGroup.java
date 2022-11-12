package musicBuddy;

import java.util.ArrayList;
import java.util.List;

public abstract class NoteGroup {

    private static final String[] NOTE_ARRAY = {"A", "A", "A#", "Bb",
            "B", "B", "Cb", "C", "C", "B#", "C#", "Db", "D", "D", "D#", "Eb", "E", "E",
            "F", "F", "E#", "F#", "Gb", "G", "G", "Fx", "G#", "Ab"};
    private static final String[] VOWEL_SOUNDING_NOTES = {"A", "Ab", "E", "Eb", "F", "F#"};
    private static List<List> noteList = new ArrayList<>();
    private String key;
    private int[] intervalPattern;
    private int currentNoteIndex;
    private int j;
    private List<String> resultNotes = new ArrayList<>();
    private String type;

    public NoteGroup(String key, int[] intervalPattern) {
        this.key = key;
        this.intervalPattern = intervalPattern;

        j = 0;
        if (isFlatKey(key)) {
            j = 1;
        }
        currentNoteIndex = startingNoteIndex(key, j);
        int i;
        for (i = 0; i < intervalPattern.length; i++) {
            if (i == 6 && intervalPattern[i] == 3) {
                resultNotes.add((String) noteList.get(currentNoteIndex).get(0));
            } else {
                resultNotes.add((String) noteList.get(currentNoteIndex).get(j));
            }
            currentNoteIndex += intervalPattern[i];
            currentNoteIndex = keepWithinOctave(currentNoteIndex);
        }
        if (i == 3 && intervalPattern[i - 1] == 3) {
            resultNotes.add((String) noteList.get(currentNoteIndex).get(1));
        } else {
            resultNotes.add((String) noteList.get(currentNoteIndex).get(j));
        }
    }

    public NoteGroup(String key) {
        this.key = key;
    }

    public static void setNoteList() {
        for (int i = 0; i < NOTE_ARRAY.length; i += 2) {
            List<String> group = new ArrayList<>();
            group.add(NOTE_ARRAY[i]);
            group.add(NOTE_ARRAY[i + 1]);
            if (NOTE_ARRAY[i].equals("B") || NOTE_ARRAY[i].equals("C") ||
                    NOTE_ARRAY[i].equals("F") || NOTE_ARRAY[i].equals("G")) {
                group.add(NOTE_ARRAY[i + 2]);
                i++;
            }
            noteList.add(group);
        }
    }

    public static List<List> getNoteList() {
        return noteList;
    }

    public int startingNoteIndex(String key, int j) {
        for (int i = 0; i < 12; i++) {
            if (noteList.get(i).get(j).equals(key)) {
                return i;
            }
        }
        return 0;
    }

    public static int getKeyIndex(String note) {
        for (int i = 0; i < 12; i++) {
            if (noteList.get(i).contains(note)) {
                //found keynote
                return i;
            }
        }
        return 0;
    }

    public boolean isFlatKey(String key) {
        if (key.substring(key.length() - 1).equals("b") || key.equals("F")) {
            return true;
        }
        return false;
    }

    public int keepWithinOctave(int currentNoteIndex) {
        //from G#/Ab back to A
        if (currentNoteIndex >= 12) {
            currentNoteIndex -= 12;
        }
        // from negative to positive
        if (currentNoteIndex < 0) {
            currentNoteIndex += 12;
        }
        return currentNoteIndex;
    }

    public List<String> getResultNotes() {
        return resultNotes;
    }


    public String getType() {
        return type;
    }

    public void printResultNotes() {
        String article = "a ";
        if (getType().equals("melody")) {
            article = "your ";
        } else {
            for (String note : VOWEL_SOUNDING_NOTES) {
                if (key.equalsIgnoreCase(note)) {
                    article = "an ";
                    break;
                }
            }
        }
        System.out.print("The notes in " + article + key + " " + getType() + " are: ");
        for (String note : getResultNotes()) {
            System.out.print(note + " ");
        }
    }
}



