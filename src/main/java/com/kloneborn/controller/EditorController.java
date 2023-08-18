package com.kloneborn.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class EditorController {

    @FXML
    private Label lb_editorError;

    @FXML
    private Label lb_editorInfo;

    @FXML
    private Label lb_editorMode;

    @FXML
    private Label lb_modeInfo;

    @FXML
    private Label lb_zoomPrecemt;

    @FXML
    private MenuBar mb_startMenu;

    @FXML
    private Pane pn_visualizer;

    @FXML
    private VBox vb_sidebar;

    @FXML
    void onActionPerformSelectorAction(ActionEvent event) {

    }

    @FXML
    void onActionPerformUtilityAction(ActionEvent event) {

    }
}