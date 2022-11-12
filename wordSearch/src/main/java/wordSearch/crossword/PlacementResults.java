package wordSearch.crossword;

public class PlacementResults {

    private boolean success = false;
    private int startingX;
    private int startingY;

    public PlacementResults(boolean success, int startingX, int startingY) {
        this.success = success;
        this.startingX = startingX;
        this.startingY = startingY;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStartingX() {
        return startingX;
    }

    public void setStartingX(int startingX) {
        this.startingX = startingX;
    }

    public int getStartingY() {
        return startingY;
    }

    public void setStartingY(int startingY) {
        this.startingY = startingY;
    }
}
