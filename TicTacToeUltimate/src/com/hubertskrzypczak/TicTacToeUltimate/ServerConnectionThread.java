package com.hubertskrzypczak.TicTacToeUltimate;

import javafx.application.Platform;

import java.io.*;
import java.net.Socket;

public class ServerConnectionThread extends Thread{

    private BigField field;
    private String message;
    private BufferedReader in;
    private PrintWriter out;

    public ServerConnectionThread(BigField field) {
        this.field = field;
    }

    @Override
    public void run() {
        try(Socket clientSocket = new Socket("localhost", 5000)) {

            makeConnection(clientSocket);

            while (true) {
                message = in.readLine();
                System.out.println("First message: " + message);
                if(message.equals("TURN")) {
                    updateField();
                    field.setTurn(true);
                    synchronized (field) {
                        field.wait();
                    }
                    message = field.toString();
                    out.println(message);
                    System.out.println("Sent field: " + message);
                    field.setAllTinyFieldsActive(false);
                }
            }


        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }
    }

    private void makeConnection(Socket clientSocket) throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);

        message = in.readLine();
        field.setCurrentPlayerSymbol(message);

        out.println("Player " + field.getCurrentPlayerSymbol() + ": OK");

        while(!in.readLine().equals("START")) {
            out.println("Pong");
        }
    }

    private void updateField() {
        Platform.runLater(() -> {
            try {
                field.parse(in.readLine());
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        });

    }
}
