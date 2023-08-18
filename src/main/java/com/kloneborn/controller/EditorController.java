package com.kloneborn.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.kloneborn.controller.editing.EditorManager;
import com.kloneborn.controller.editing.EditorMode;
import com.kloneborn.controller.editing.SimulationEditor;
import com.kloneborn.controller.editing.actions.Deleter;
import com.kloneborn.controller.editing.actions.Inspector;
import com.kloneborn.controller.editing.actions.Mover;
import com.kloneborn.controller.editing.actions.StationCreator;
import com.kloneborn.controller.editing.actions.TrackCreator;
import com.kloneborn.controller.editing.actions.TrainCreator;
import com.kloneborn.models.simulation.Simulation;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class EditorController implements Initializable {

    private SimulationEditor editor;

    @FXML
    private ScrollPane sp_scroller;

    @FXML
    private CheckBox cb_pannable;

    @FXML
    private Label lb_editorError;

    @FXML
    private Label lb_editorInfo;

    @FXML
    private Label lb_editorMode;

    @FXML
    private Label lb_modeInfo;

    @FXML
    private Label lb_zoomPercent;

    @FXML
    private MenuBar mb_startMenu;

    @FXML
    private Pane pn_visualizer;

    @FXML
    private VBox vb_sidebar;

    private static Simulation target = new Simulation();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editor = new SimulationEditor(
                EditorController.target,
                new EditorManager(
                        pn_visualizer,
                        new StringProperty[] {
                                lb_editorError.textProperty(), // Editor Error
                                lb_editorInfo.textProperty(), // Editor Info
                                lb_editorMode.textProperty(), // Editor Mode
                                lb_modeInfo.textProperty(), // Editor Mode Info
                        }));
        EditorManager manager = editor.getManager();
        manager.register(EditorMode.INSPECT, new Inspector());
        manager.register(EditorMode.MOVE, new Mover());
        manager.register(EditorMode.DELETE, new Deleter());
        manager.register(EditorMode.STATION, new StationCreator());
        manager.register(EditorMode.TRACK, new TrackCreator());
        manager.register(EditorMode.TRAIN, new TrainCreator());
        this.lb_editorError.textProperty().addListener((observable, oldValue, newValue) -> {
            fadeLabelIn(lb_editorError);
            holdForSeconds(15);
            fadeLabelOut(lb_editorError);
        });
        this.lb_editorInfo.textProperty().addListener((observable, oldValue, newValue) -> {
            fadeLabelIn(lb_editorInfo);
            holdForSeconds(15);
            fadeLabelOut(lb_editorInfo);
        });

        this.lb_editorError.setVisible(false);
        this.lb_editorInfo.setVisible(false);
    }

    @FXML
    void onActionPerformSelectorAction(ActionEvent event) {

    }

    @FXML
    void onActionPerformUtilityAction(ActionEvent event) {

    }

    private void fadeLabelIn(Label target) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), target);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    private void holdForSeconds(int seconds) {
        PauseTransition pause = new PauseTransition(Duration.seconds(seconds));
        pause.setOnFinished(event -> {
            // Do nothing, just wait
        });
        pause.play();
    }

    private void fadeLabelOut(Label target) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), target);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.play();
    }
}