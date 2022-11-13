package com.zupple.sudoku.shapes;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private List<Block> blockColumn = new ArrayList<>();
    private int columnIndex;

    public Column(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public void addBlock(Block block) {
        blockColumn.add(block);
    }

    public int getBlockValue(int index) {
        return blockColumn.get(index).getValue();
    }

    public void setBlockValue(int index, int value) {
        blockColumn.get(index).setValue(value);
    }
}


//    public void printColumn() {
//        for (Block block : blockColumn) {
//            System.out.println(block.getValue());
//        }
//    }
//
//    public void updateColumn(List<Column> columnList, Row row) {
//        int rowIndex = row.getRowIndex();
//        for (int i = 0; i < 9; i++) {
//            Column column = columnList.get(i);
//            int valueFromRow = row.getNum(i);
//            column.setBlockValue(i, valueFromRow);
//        }
//    }
