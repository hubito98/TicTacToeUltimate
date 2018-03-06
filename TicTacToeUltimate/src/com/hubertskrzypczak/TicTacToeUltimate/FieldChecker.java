package com.hubertskrzypczak.TicTacToeUltimate;

public class FieldChecker {

    public static boolean checkSolution(String fieldAsString, String symbol) {
        if(checkRowsAndColumns(fieldAsString, symbol) ||
                checkSkews(fieldAsString, symbol)) {
            return true;
        }
        return false;
    }

    private static boolean checkRowsAndColumns(String fieldAsString, String symbol) {
        for(int i = 0; i < 3; i++) {
            if( checkRow(fieldAsString, symbol, i) ||
                    checkColumn(fieldAsString, symbol, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkRow(String fieldAsString, String symbol, int i) {
        return fieldAsString.substring(i * 3, i * 3 + 3).equals(symbol + symbol + symbol);
    }

    private static boolean checkColumn(String fieldAsString, String symbol, int i) {
        char tempSymbol = symbol.charAt(0);
        return (fieldAsString.charAt(i) == tempSymbol &&
                fieldAsString.charAt(i + 3) == tempSymbol &&
                fieldAsString.charAt(i + 6) == tempSymbol);
    }

    private static boolean checkSkews(String fieldAsString, String symbol) {
        char tempSymbol = symbol.charAt(0);
        if(fieldAsString.charAt(4) == tempSymbol) {
            if(fieldAsString.charAt(0) == tempSymbol &&
                fieldAsString.charAt(8) == tempSymbol) {
                return true;
            }
            if(fieldAsString.charAt(2) == tempSymbol &&
                fieldAsString.charAt(6) == tempSymbol) {
                return true;
            }
        }

        return false;
    }

    public static boolean isDraw(String fieldAsString) {
        return (fieldAsString.substring(0, 9).replaceAll("[XO]", "").length() == 0);
    }
}
