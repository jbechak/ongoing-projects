package diceRoller.diceSides;

public class SideSix extends SideFour {
    private final int VALUE = 6;
    private String sideInterior;

    @Override
    public String getSideInterior() {
        sideInterior = "| o   o |\n" +
                      "| o   o |\n" +
                      "| o   o |";
        return sideInterior;
    }

    @Override
    public String getRowTwo() {
        return "| o   o |  ";
    }

    @Override
    public int getVALUE() {
        return VALUE;
    }
}
