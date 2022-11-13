package com.zupple;

public class Instructions {
    private final String INSTRUCTIONS_1 = "Look through the word search puzzle " +
            "and find all of the words from the word list.\nThe words can be " +
            "found going from left to right or from top to bottom.";
    private final String INSTRUCTIONS_2 = "Look through the word search puzzle " +
            "and find all of the words from the word list.\nThe words can be " +
            "found going from left to right, top to bottom, or diagonally.";

    private final String INSTRUCTIONS_3 = "Look through the word search puzzle " +
            "and find all of the words from the word list.\nThe words can be " +
            "found going from left to right, top to bottom, diagonally, or\n" +
            "backwards in any of these directions.";
    private String customInstructions;
    private boolean instructionsEdited = false;

    public boolean isInstructionsEdited() {
        return instructionsEdited;
    }

    public void setInstructionsEdited(boolean instructionsEdited) {
        this.instructionsEdited = instructionsEdited;
    }

    public String getINSTRUCTIONS_1() {
        return INSTRUCTIONS_1;
    }

    public String getINSTRUCTIONS_2() {
        return INSTRUCTIONS_2;
    }

    public String getINSTRUCTIONS_3() {
        return INSTRUCTIONS_3;
    }

//    public void setInstructions4(String mediumInstructions) {
//        this.instructions4 = instructions4;
//    }
//
//    public void setInstructions2(String easyInstructions) {
//        this.instructions2 = instructions2;
//    }
//
//    public void setInstructions8(String hardInstructions) {
//        this.instructions8 = instructions8;
//    }

    public String getCustomInstructions() {
        return customInstructions;
    }

    public void setCustomInstructions(String customInstructions) {
        this.customInstructions = customInstructions;
    }
}
