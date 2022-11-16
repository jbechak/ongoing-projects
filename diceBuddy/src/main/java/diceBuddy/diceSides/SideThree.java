package diceBuddy.diceSides;

public class SideThree extends SideTwo {
    private final int VALUE = 3;
    private String sideInterior;

    @Override
    public String getSideInterior() {
        sideInterior = "|     o |\n" +
                      "|   o   |\n" +
                      "| o     |";
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
