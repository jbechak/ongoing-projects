package diceBuddy.odds;

public class RiskOddsResult {
    private int redSweepCount = 0;
    private int redWinCount = 0;
    private int whiteSweepCount = 0;
    private int whiteWinCount = 0;
    private int tieCount = 0;

    public int getRedSweepCount() {
        return redSweepCount;
    }

    public void incrementRedSweepCount() {
        redSweepCount++;
    }

    public void incrementWhiteSweepCount() {
        whiteSweepCount++;
    }

    public void incrementTieCount() {
        tieCount++;
    }

    public void incrementRedWinCount() {
        redWinCount++;
    }

    public void incrementWhiteWinCount() {
        whiteWinCount++;
    }

    public int getRedWinCount() {
        return redWinCount;
    }

    public int getWhiteSweepCount() {
        return whiteSweepCount;
    }

    public int getWhiteWinCount() {
        return whiteWinCount;
    }

    public int getTieCount() {
        return tieCount;
    }

}
