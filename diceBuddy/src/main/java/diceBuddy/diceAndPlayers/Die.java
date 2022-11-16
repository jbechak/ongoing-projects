package diceBuddy.diceAndPlayers;

import diceBuddy.diceSides.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Die {
    private List<DiceSide> sideList = new ArrayList<>();
    SideOne sideOne = new SideOne();
    SideTwo sideTwo = new SideTwo();
    SideThree sideThree = new SideThree();
    SideFour sideFour = new SideFour();
    SideFive sideFive = new SideFive();
    SideSix sideSix = new SideSix();
    private Random generate = new Random();
    private final int MAX_VALUE = 6;

    public Die() {
        sideList.add(sideOne);
        sideList.add(sideTwo);
        sideList.add(sideThree);
        sideList.add(sideFour);
        sideList.add(sideFive);
        sideList.add(sideSix);
    }

    public void rollDie() {
        int rollValue = generate.nextInt(6) + 1;
        for (DiceSide side : sideList) {
            if (side.getVALUE() == rollValue) {
                side.printSide();
            }
        }
    }

    public void showSides() {
        for (DiceSide side : sideList) {
            side.printSide();
        }
    }

    public DiceSide getDiceSide(int value) {
        for (DiceSide side : sideList) {
            if (side.getVALUE() == value) {
                return side;
            }
        }
        return sideOne;
    }

}
