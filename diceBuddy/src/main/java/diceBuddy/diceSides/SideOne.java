package diceBuddy.diceSides;

public class SideOne extends DiceSide {

    public final int VALUE = 1;
    private String sideInterior;



    @Override
    public String getSideInterior() {
        sideInterior = "|       |\n" +
                      "|   o   |\n" +
                      "|       |";
        return sideInterior;
    }

//    public String getRowOne() {
//        return getBlankRow();
//    }

    @Override
    public String getRowTwo() {
        return "|   o   |  ";
    }

    @Override
    public int getVALUE() {
        return VALUE;
    }
}


/*

+-------+
| o   o |
| o   o |
| o   o |
+-------+
 */
