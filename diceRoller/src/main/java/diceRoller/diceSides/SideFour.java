package diceRoller.diceSides;

public class SideFour extends DiceSide {
    private final int VALUE = 4;
    private String sideInterior;


    @Override
    public String getSideInterior() {
        sideInterior = "| o   o |\n" +
                      "|       |\n" +
                      "| o   o |";
        return sideInterior;
    }

    @Override
    public String getRowOne() {
        return "| o   o |  ";
    }

    @Override
    public String getRowThree() {
        return "| o   o |  ";
    }

    @Override
    public int getVALUE() {
        return VALUE;
    }
}
