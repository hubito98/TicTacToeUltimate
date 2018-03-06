package com.hubertskrzypczak.TicTacToeUltimate;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static Socket circlePlayer, crossPlayer;
    private static BufferedReader circleIn, crossIn;
    private static PrintWriter circleOut, crossOut;

    public static void main(String[] args) {
        try(ServerSocket server = new ServerSocket(5000)) {
            while(true) {
                makeConnectionWithPlayers(server);
                new GameplayThread(circlePlayer, crossPlayer, circleIn, crossIn, circleOut, crossOut).start();
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    private static void makeConnectionWithPlayers(ServerSocket server)
            throws IOException {
        connectFirstPlayer(server);
        connectSecondPlayer(server);
        checkAndConfirmPlayersConnection(server);
    }

    private static void connectFirstPlayer(ServerSocket server)
            throws IOException {
        circlePlayer = server.accept();

        System.out.println("First user connected");

        circleOut = new PrintWriter(circlePlayer.getOutputStream(), true);
        circleIn = new BufferedReader(new InputStreamReader(circlePlayer.getInputStream()));

        circleOut.println("O");
        System.out.println(circleIn.readLine());
    }

    private static void connectSecondPlayer(ServerSocket server)
            throws IOException {
        crossPlayer = server.accept();

        System.out.println("Second user connected");

        crossOut = new PrintWriter(crossPlayer.getOutputStream(), true);
        crossIn = new BufferedReader(new InputStreamReader(crossPlayer.getInputStream()));

        crossOut.println("X");
        System.out.println(crossIn.readLine());
    }

    private static void checkAndConfirmPlayersConnection(ServerSocket server)
            throws IOException {
        checkPlayersConnection(server);
        confirmPlayersConnection();
    }

    private static void checkPlayersConnection(ServerSocket server)
            throws IOException {
        String circleMessage = null, crossMessage = null;
        while(circleMessage == null || crossMessage == null) {
            circleOut.println("Ping");
            circleMessage = circleIn.readLine();
            if (circleMessage == null) {
                connectFirstPlayer(server);
            }
            crossOut.println("Ping");
            crossMessage = crossIn.readLine();
            if (crossMessage == null) {
                connectSecondPlayer(server);
            }
        }
    }

    private static void confirmPlayersConnection() {
        circleOut.println("START");
        crossOut.println("START");
    }
}
