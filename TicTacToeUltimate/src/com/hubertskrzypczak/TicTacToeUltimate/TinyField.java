package com.hubertskrzypczak.TicTacToeUltimate;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class TinyField extends GridPane {

    private boolean isTurn;
    private String currentPlayerSymbol;
    private boolean isActive;
    private String winPlayerSymbol = " ";

    private FieldNode[][] fieldNodes;

    public TinyField() {
        setAlignment(Pos.CENTER);
        setId("tiny-field");
        fieldNodes = new FieldNode[3][3];
        for(int row = 0; row < fieldNodes.length; row++) {
            for(int col = 0; col < fieldNodes[row].length; col++) {
                fieldNodes[row][col] = new FieldNode(row, col);
                add(fieldNodes[row][col], col, row);
            }
        }
    }

    public synchronized void click(FieldNode node) {
        if (isAbleToPutSymbolOnNode(node)) {
            BigField parent = (BigField)getParent();
            node.putSymbol(currentPlayerSymbol);
            if(FieldChecker.checkSolution(this.toString(), currentPlayerSymbol)) {
                setWinPlayerSymbol(currentPlayerSymbol);
                parent.checkForWin(currentPlayerSymbol);
            }
            else if (FieldChecker.isDraw(this.toString())){
                setDrawSymbol();
                parent.checkForDraw();
            }
            parent.setNextActiveTinyField(node.getRowIndex(), node.getColIndex());
            parent.setTurn(false);
            synchronized (parent) {
                parent.notify();
            }
        }
    }

    private boolean isAbleToPutSymbolOnNode(FieldNode node) {
        return (isTurn && isActive && winPlayerSymbol.equals(" ") && node.isEmpty());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int row = 0; row < fieldNodes.length; row++) {
            for(int col = 0; col < fieldNodes[row].length; col++) {
                builder.append(fieldNodes[row][col].getText());
            }
        }
        builder.append(winPlayerSymbol);
        builder.append(String.valueOf(isActive));
        return builder.toString().replaceAll(" ", ".");
    }

    public synchronized void parse(String fieldAsString) {
        for(int row = 0; row < fieldNodes.length; row++) {
            for(int col = 0; col < fieldNodes[row].length; col++) {
                if(fieldAsString.charAt(row * 3 + col) == 'O') {
                    fieldNodes[row][col].putSymbol("O");
                } else if(fieldAsString.charAt(row * 3 + col) == 'X') {
                    fieldNodes[row][col].putSymbol("X");
                }
            }
        }
        if(fieldAsString.charAt(9) != '.') {
            setWinPlayerSymbol(String.valueOf(fieldAsString.charAt(9)));
        } else {
            setActive(Boolean.valueOf(fieldAsString.replaceAll("[OX.]", "")));
        }
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public String getCurrentPlayerSymbol() {
        return currentPlayerSymbol;
    }

    public synchronized void setCurrentPlayerSymbol(String currentPlayerSymbol) {
        this.currentPlayerSymbol = currentPlayerSymbol;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        if(winPlayerSymbol.equals(" ")) {
            isActive = active;
            if (active) {
                if(currentPlayerSymbol.equals("O"))
                    setStyle("-fx-border-color: lightgreen; -fx-border-width: 3");
                else if(currentPlayerSymbol.equals("X"))
                    setStyle("-fx-border-color: red; -fx-border-width: 3");
            } else {
                setStyle("-fx-border-color: " + "transparent;");
            }
        }
    }

    public String getWinPlayerSymbol() {
        return winPlayerSymbol;
    }

    private void setWinPlayerSymbol(String winPlayerSymbol) {
        if(winPlayerSymbol.equals("X")) {
            setStyle("-fx-background-color: red");
        } else if(winPlayerSymbol.equals("O")) {
            setStyle("-fx-background-color: lightgreen");
        } else if(winPlayerSymbol.equals("-")) {
            setDrawSymbol();
        }
        this.winPlayerSymbol = winPlayerSymbol;
    }

    private void setDrawSymbol() {
        setStyle("-fx-background-color: saddlebrown");
        this.winPlayerSymbol = "-";
    }
}
