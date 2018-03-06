package com.hubertskrzypczak.TicTacToeUltimate;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class BigField extends GridPane {

    private boolean isTurn = false;
    private String currentPlayerSymbol;

    private TinyField[][] tinyFields;

    public BigField() {
        setAlignment(Pos.CENTER);
        setId("big-field");
        tinyFields = new TinyField[3][3];
        for(int row = 0; row < tinyFields.length; row++) {
            for(int col = 0; col < tinyFields[row].length; col++) {
                tinyFields[row][col] = new TinyField();
                add(tinyFields[row][col], col, row);
            }
        }
    }

    public void checkForWin(String symbol) {
        if(FieldChecker.checkSolution(stringifyForFieldChecker(), symbol)) {

        }
    }

    public void checkForDraw() {
        if(FieldChecker.isDraw(stringifyForFieldChecker())) {

        }
    }

    private String stringifyForFieldChecker() {
        StringBuilder sb = new StringBuilder();
        for(int row = 0; row < tinyFields.length; row++) {
            for(int col = 0; col < tinyFields[row].length; col++) {
                sb.append(tinyFields[row][col].getWinPlayerSymbol());
            }
        }
        return sb.toString().replaceAll(" ", ".");
    }

    public void setNextActiveTinyField(int rowIndex, int colIndex) {
        if(isNextActiveTinyFieldSolved(rowIndex, colIndex)) {
            setAllTinyFieldsActive(true);
            tinyFields[rowIndex][colIndex].setActive(false);
        } else {
            setAllTinyFieldsActive(false);
            tinyFields[rowIndex][colIndex].setActive(true);
        }
    }

    private boolean isNextActiveTinyFieldSolved (int rowIndex, int colIndex) {
        return !tinyFields[rowIndex][colIndex].getWinPlayerSymbol().equals(" ");
    }

    public void setAllTinyFieldsActive(boolean isActive) {
        for(int row = 0; row < tinyFields.length; row++) {
            for(int col = 0; col < tinyFields[row].length; col++) {
                tinyFields[row][col].setActive(isActive);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int row = 0; row < tinyFields.length; row++) {
            for(int col = 0; col < tinyFields[row].length; col++) {
                builder.append(tinyFields[row][col].toString() + ";");
            }
        }
        System.out.println(builder.toString());
        return builder.toString();
    }

    public void parse(String fieldAsString) {
        final String[] tinyFieldsAsString = fieldAsString.trim().split(";");
        Platform.runLater(() -> {
            for(int row = 0; row < tinyFields.length; row++) {
                for(int col = 0; col < tinyFields[row].length; col++) {
                    tinyFields[row][col].parse(tinyFieldsAsString[row*3 + col]);
                }
            }
        });
    }

    public void setCurrentPlayerSymbol(String currentPlayerSymbol) {
        this.currentPlayerSymbol = currentPlayerSymbol;
        for(int row = 0; row < tinyFields.length; row++) {
            for(int col = 0; col < tinyFields[row].length; col++) {
                tinyFields[row][col].setCurrentPlayerSymbol(currentPlayerSymbol);
            }
        }
    }

    public String getCurrentPlayerSymbol() {
        return currentPlayerSymbol;
    }

    public void setTurn(boolean turn) {
        this.isTurn = turn;
        for(int row = 0; row < tinyFields.length; row++) {
            for(int col = 0; col < tinyFields[row].length; col++) {
                tinyFields[row][col].setTurn(turn);
            }
        }
    }
}
