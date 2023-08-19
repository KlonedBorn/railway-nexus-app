package com.kloneborn.controller.editing.actions;

import com.kloneborn.component.dialog.StationDialog;
import com.kloneborn.component.graphics.StationGraphic;
import com.kloneborn.controller.editing.EditorAction;
import com.kloneborn.models.simulation.Station;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class StationCreator extends EditorAction {
    @Override
    public void setup() {
        visualizer.setOnMouseClicked(this::onMouseClickedSpawnStation);
    }

    @Override
    public void cleanup() {
        visualizer.setOnMouseClicked(null);
    }

    @FXML
    private void onMouseClickedSpawnStation(MouseEvent evt) {
        StationDialog prompt = new StationDialog();
        Station station = prompt.showAndWait().orElse(null);
        if (station != null) {
            StationGraphic graphic = (StationGraphic) station.getGraphic();
            graphic.setLayoutX(evt.getX());
            graphic.setLayoutY(evt.getY());
            visualizer.getChildren().add(graphic);
            this.target.stationsProperty().add(station);
        }
    }
}