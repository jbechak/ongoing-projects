package sudoku.game.shapes;

public class Block {
    private int value;
    private int boardIndex;
    private int rowIndex;
    private int columnIndex;
    private int squareIndex;
    private int tripleRowIndex;
    private int tripleColumnIndex;

    public Block(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public void setSquareIndex(int squareIndex) {
        this.squareIndex = squareIndex;
    }

    public void setTripleRowIndex(int tripleRowIndex) {
        this.tripleRowIndex = tripleRowIndex;
    }

    public void setTripleColumnIndex(int tripleColumnIndex) {
        this.tripleColumnIndex = tripleColumnIndex;
    }

    public int getValue() {
        return value;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getSquareIndex() {
        return squareIndex;
    }

    public int getTripleRowIndex() {
        return tripleRowIndex;
    }

    public int getTripleColumnIndex() {
        return tripleColumnIndex;
    }
}
