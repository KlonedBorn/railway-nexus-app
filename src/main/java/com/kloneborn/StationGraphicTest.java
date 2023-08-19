package com.kloneborn;

import com.kloneborn.models.simulation.Station;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StationGraphicTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.getChildren().add(new Station("Boling").getGraphic());
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Station Graphic Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}