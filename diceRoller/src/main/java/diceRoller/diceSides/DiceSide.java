package diceRoller.diceSides;

public abstract class DiceSide {
    private String sideInterior;
    public int VALUE;
    //public String blankRow = "|       |";

    public String getHorizontalLine() {
        return "+-------+  ";
    }

    public String getBlankRow() {
        return "|       |  ";
    }

    public String getRowOne() {
        return getBlankRow();
    }

    public String getRowTwo() {
        return getBlankRow();
    }

    public String getRowThree() {
        return getBlankRow();
    }

    public String getSideInterior() {
        return sideInterior;
    }

    public void printSide() {
        System.out.println(getHorizontalLine());
        System.out.println(getSideInterior());
        System.out.println(getHorizontalLine());
    }

    public int getVALUE() {
        return VALUE;
    }
}
