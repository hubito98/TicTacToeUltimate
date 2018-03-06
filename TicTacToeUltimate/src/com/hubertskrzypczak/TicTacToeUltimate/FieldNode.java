package com.hubertskrzypczak.TicTacToeUltimate;

import javafx.scene.control.Button;

public class FieldNode extends Button {

    private int rowIndex, colIndex;

    public FieldNode(int rowIndex, int colIndex) {
        setOnAction((event) -> ((TinyField)getParent()).click(this));
        setText(" ");
        setTextFill(null);
        setId("empty-node");
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public void putSymbol(String symbol) {
        setText(symbol);
        setId(symbol);
    }

    public boolean isEmpty() {
        return getText().equals(" ");
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }
}
