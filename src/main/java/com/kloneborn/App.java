package com.kloneborn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final String DEFAULT_FILE_PATH = "fxml/view/editor-panel";
    private static final String APP_ICON_PATH = "img/icon/train-station.png";
    private static final String TITLE = "Railway Nexus";
    private static final String VERSION = "1.0.0";

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        scene = new Scene(loadFXML(DEFAULT_FILE_PATH));
        App.stage.getIcons().add(getApplicationIcon());
        App.stage.setTitle(getWindowTitle());
        App.stage.setScene(scene);
        App.stage.show();
    }

    private String getWindowTitle() {
        return String.format("LogicSphere - %s - %s", App.TITLE, App.VERSION);
    }

    private Image getApplicationIcon() {
        return new Image(App.class.getResourceAsStream(APP_ICON_PATH));
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        if (!fxml.contains(".fxml"))
            fxml += ".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml));
            return fxmlLoader.load();
    }

    public static VBox createExceptionVBox(Exception exception) {
        VBox vbox = new VBox(10); // 10 is spacing between children
        vbox.setAlignment(Pos.CENTER);
        Label exceptionLabel = new Label("Exception: " + exception.getMessage());
        vbox.getChildren().add(exceptionLabel);
        return vbox;
    }

    public static void main(String[] args) {
        launch();
    }
}