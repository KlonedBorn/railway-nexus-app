package com.kloneborn.controller.editing.actions;

import com.kloneborn.component.dialog.StationDialog;
import com.kloneborn.controller.editing.EditorAction;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class StationCreator extends EditorAction{
    @Override
    public void setup() {
        this.getVisualizer().setOnMouseClicked(this::onMouseClickedSpawnStation);
    }

    @Override
    public void cleanup() {
        this.getVisualizer().setOnMouseClicked(null);
    }

    @FXML
    private void onMouseClickedSpawnStation(MouseEvent evt) {
        StationDialog prompt = new StationDialog();
        prompt.showAndWait();
    }
}