package diceBuddy.diceSides;

public class SideFive extends SideFour {
    private final int VALUE = 5;
    private String sideInterior;

    @Override
    public String getSideInterior() {
        sideInterior = "| o   o |\n" +
                      "|   o   |\n" +
                      "| o   o |";
        return sideInterior;
    }

    @Override
    public String getRowTwo() {
        return "|   o   |  ";
    }

    @Override
    public int getVALUE() {
        return VALUE;
    }

}
