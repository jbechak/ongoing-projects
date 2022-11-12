package sudoku.game;

import sudoku.game.shapes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private List<TripleRow> board = new ArrayList<>();
    private List<Row> rowList = new ArrayList<>();
    private List<Block> blockList = new ArrayList<>();
    private List<Column> columnList = new ArrayList<>();
    private List<Square> squareList = new ArrayList<>();
    private Random generate = new Random();
    private int level;

    public Row getRow(int index) {
        return rowList.get(index);
    }

    public Board() {
    }

    public List<TripleRow> getBoard() {
        return board;
    }

    public void putAllBlocksInList() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Row currentRow = getRow(i);
                blockList.add(currentRow.getBlock(j));
            }
        }
        int i = 0;
        for (Block block : blockList) {
            block.setBoardIndex(i);
            i++;
        }
    }

    public void createAllColumns() {
        for (int column = 0; column < 9; column++) {
            Column blockColumn = new Column(column);
            for (int row = 0; row < 9; row++) {
                blockColumn.addBlock(getRow(row).getBlock(column));
            }
            columnList.add(blockColumn);
        }
    }

    public void createAllSquares() {

        for (int row = 0; row < 7; row += 3) {
            for (int column = 0; column < 7; column += 3) {
                Square square = new Square(column, row);
                for (int y = row; y < row + 3; y++) {
                    for (int x = column; x < column + 3; x++) {
                        square.addBlock(getRow(y).getBlock(x));
                    }
                }
                squareList.add(square);
            }
        }
    }

    public void printIndexOfAllBlocks() {
        for (Block block : blockList) {
            System.out.print(block.getBoardIndex() + " ");
        }
    }

    public void addRow(Row row) {
        rowList.add(row);
    }

    public void addTripleRow(TripleRow tripleRow) {
        board.add(tripleRow);
    }

    public void setTripleRow(int index, TripleRow tripleRow) {
        board.set(index, tripleRow);
    }

    public TripleRow getTripleRow(int index) {
        return board.get(index);
    }


    @Override
    public String toString() {
        String puzzle = doubleHorizontalLine();
        for (TripleRow row : board) {
            puzzle += row + doubleHorizontalLine();
        }
        puzzle += "\n";
        return puzzle;
    }

    public String toHtml() {
        HtmlEncoder htmlEncoder = new HtmlEncoder();
        String htmlString = htmlEncoder.htmlHeader() +
                            htmlEncoder.htmlTitle(levelString()) +
                            htmlEncoder.htmlPuzzle(rowList);
        return htmlString;
    }
    public String doubleHorizontalLine() {
        String line = "";
        for (int i = 0; i < 9; i++) {
            line += "====";
            if (i == 8) {
                line += "=====\n";
            }
        }
        return line;
    }

    public int getLevel() {
        return level;
    }

    public String levelString() {
        return "Level " + level + " Puzzle";
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void createBlanks(int blanks) {
        boolean alreadyBlank;
        for (int i = 0; i < blanks; i++) {
            do {
                int blockNum = generate.nextInt(81);
                alreadyBlank = setBlock(blockNum, 0);
            } while (alreadyBlank);
        }
    }

    public boolean setBlock(int blockNum, int value) {
        if (blockList.get(blockNum).getValue() == 0) {
            return true;
        }
        blockList.get(blockNum).setValue(value);
        return false;
    }

    public Column getColumn(int index) {
        return columnList.get(index);
    }

    public Square getBlockSquare(int index) {
        return squareList.get(index);
    }

    public void updateColumns(int rowIndex) {
        for (int i = 0; i < 9; i++) {
            Column column = getColumn(i);
            int currentValue = rowList.get(rowIndex).getNum(i);
            column.setBlockValue(rowIndex, currentValue);
        }
    }

    public void updateSquares(int currentRow) {
        int firstSquare = currentRow / 3 * 3;
        for (int i = firstSquare; i < firstSquare + 3; i++) {
            Square currentSquare = getBlockSquare(i);
            update1Square(currentSquare, currentRow);
        }
    }


    public void update1Square(Square square, int currentRow) {
        int rowToUpdate = currentRow % 3;
        int firstSquareIndex = rowToUpdate * 3;
        for (int index = square.getX(), sqIndex = firstSquareIndex ; index < square.getX() + 3; index++, sqIndex++) {
            square.setBlockValue(sqIndex, getRow(currentRow).getNum(index));
        }
    }
}
