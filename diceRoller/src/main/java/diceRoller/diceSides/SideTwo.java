package diceRoller.diceSides;

public class SideTwo extends DiceSide {
    private final int VALUE = 2;
    private String sideInterior;


    @Override
    public String getSideInterior() {
        sideInterior = "|     o |\n" +
                      "|       |\n" +
                      "| o     |";
        return sideInterior;
    }

    @Override
    public String getRowOne() {
        return "|     o |  ";
    }

    @Override
    public String getRowThree() {
        return "| o     |  ";
    }

    @Override
    public int getVALUE() {
        return VALUE;
    }
}
