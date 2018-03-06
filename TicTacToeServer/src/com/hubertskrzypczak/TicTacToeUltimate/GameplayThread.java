package com.hubertskrzypczak.TicTacToeUltimate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class GameplayThread extends Thread {

    private String fieldAsString;
    private Socket circlePlayer, crossPlayer;
    private BufferedReader circleIn, crossIn;
    private PrintWriter circleOut, crossOut;


    public GameplayThread(Socket circlePlayer, Socket crossPlayer,
                        BufferedReader circleIn, BufferedReader crossIn,
                        PrintWriter circleOut, PrintWriter crossOut) {
        fieldAsString = "..........true;..........true;..........true;" +
                        "..........true;..........true;..........true;" +
                        "..........true;..........true;..........true;";
        this.circlePlayer = circlePlayer;
        this.crossPlayer = crossPlayer;
        this.circleIn = circleIn;
        this.crossIn = crossIn;
        this.circleOut = circleOut;
        this.crossOut = crossOut;
    }

    @Override
    public void run() {
        while(fieldAsString != null) {
            try {
                circleOut.println("TURN");
                circleOut.println(fieldAsString);
                fieldAsString = circleIn.readLine();
                System.out.println(fieldAsString);

                crossOut.println("TURN");
                crossOut.println(fieldAsString);
                fieldAsString = crossIn.readLine();
                System.out.println(fieldAsString);

            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Player has diconnected");
                break;
            }
        }
    }
}
