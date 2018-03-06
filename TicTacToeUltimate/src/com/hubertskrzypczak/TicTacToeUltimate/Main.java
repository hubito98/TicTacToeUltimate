package com.hubertskrzypczak.TicTacToeUltimate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Game game;
    private static int FPS;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("root.fxml"));
        game = new Game(FPS, "Tic Tac Toe Ultimate");
        game.setSceneNodes(root);
        game.initialize(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
