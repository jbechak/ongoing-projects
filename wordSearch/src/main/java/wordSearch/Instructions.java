package wordSearch;

public class Instructions {
    private String instructions2 = "Look through the word search puzzle " +
            "and find all of the words from the word list.\nThe words can be " +
            "found going from left to right or from up to down.";
    private String instructions4 = "Look through the word search puzzle " +
            "and find all of the words from the word list.\nThe words can be " +
            "found going from left to right, up to down, or diagonally.";

    private String instructions8 = "Look through the word search puzzle " +
            "and find all of the words from the word list.\nThe words can be " +
            "found going from left to right, up to down, diagonally, or\n" +
            "backwards in any of these directions.";
    private String customInstructions;
    private boolean instructionsEdited = false;

    public boolean isInstructionsEdited() {
        return instructionsEdited;
    }

    public void setInstructionsEdited(boolean instructionsEdited) {
        this.instructionsEdited = instructionsEdited;
    }

    public String getInstructions2() {
        return instructions2;
    }

    public String getInstructions4() {
        return instructions4;
    }

    public String getInstructions8() {
        return instructions8;
    }

    public void setInstructions4(String mediumInstructions) {
        this.instructions4 = instructions4;
    }

    public void setInstructions2(String easyInstructions) {
        this.instructions2 = instructions2;
    }

    public void setInstructions8(String hardInstructions) {
        this.instructions8 = instructions8;
    }

    public String getCustomInstructions() {
        return customInstructions;
    }

    public void setCustomInstructions(String customInstructions) {
        this.customInstructions = customInstructions;
    }
}
