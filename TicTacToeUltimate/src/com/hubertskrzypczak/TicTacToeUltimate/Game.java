package com.hubertskrzypczak.TicTacToeUltimate;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game {

    private Scene gameScene;
    private BorderPane sceneNodes;

    private BigField field;

    private final int FPS;
    private final String windowTitle;

    public Game(int FPS, String windowTitle) {
        this.FPS = FPS;
        this.windowTitle = windowTitle;
    }

    public void initialize(final Stage primaryStage) {
        primaryStage.setTitle(windowTitle);
        gameScene = new Scene(sceneNodes);
        field = new BigField();
        sceneNodes.setCenter(field);
        primaryStage.setScene(gameScene);
        primaryStage.show();

        ServerConnectionThread serverThread = new ServerConnectionThread(field);
        serverThread.start();
    }

    public void setSceneNodes(BorderPane sceneNodes) {
        this.sceneNodes = sceneNodes;
    }
}
