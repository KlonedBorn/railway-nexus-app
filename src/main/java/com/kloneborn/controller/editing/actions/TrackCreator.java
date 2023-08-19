package com.kloneborn.controller.editing.actions;

import com.kloneborn.component.graphics.StationGraphic;
import com.kloneborn.controller.editing.EditorAction;
import com.kloneborn.models.simulation.Station;
import com.kloneborn.models.simulation.Track;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class TrackCreator extends EditorAction{
    private GuideLine guide = new GuideLine(0, 0, 0, 0);
    private Station startNode, endNode;

    @Override
    public void setup() {
        visualizer.setOnMouseClicked(evt -> visualizer.getChildren().remove(guide));
        visualizer.setOnDragDetected(evt -> {
            evt.setDragDetect(true);
            visualizer.startFullDrag();
            evt.consume();
        });
        for (Station s : target.stationsProperty().get()) {
            StationGraphic sb = (StationGraphic)s.getGraphic();
            sb.setOnDragDropped(evt -> visualizer.getChildren().remove(guide));
            sb.setOnMousePressed(this::onMousePressedStartDrag);
            sb.setOnMouseDragged(this::onMouseDraggedUpdateGuideline);
            sb.setOnMouseDragReleased(this::onMouseDragReleasedCreateTrack);
        }
        visualizer.setOnDragDone(evt -> evt.setDropCompleted(true));
    }

    @FXML
    private void onMousePressedStartDrag(MouseEvent evt) {
        StationGraphic graphic = (StationGraphic) evt.getTarget();
        startNode = graphic.getOwner();
        Point2D toParent = graphic.localToParent(evt.getX(), evt.getY());
        guide.setStartX(toParent.getX());
        guide.setStartY(toParent.getY());
        guide.setEndX(toParent.getX());
        guide.setEndY(toParent.getY());
        visualizer.getChildren().add(guide);
    }

    @FXML
    private void onMouseDraggedUpdateGuideline(MouseEvent evt) {
        StationGraphic graphic = (StationGraphic) evt.getTarget();
        Point2D rel = graphic.localToParent(evt.getX(), evt.getY());
        guide.setEndX(rel.getX());
        guide.setEndY(rel.getY());
    }

    @FXML
    private void onMouseDragReleasedCreateTrack(MouseEvent evt) {
        StationGraphic graphic = (StationGraphic) evt.getTarget();
        endNode = graphic.getOwner();
        if (startNode.isConnectedTo(endNode)) {
            setError("Stations already connected");
        } else if (startNode != endNode) {
            target.tracksProperty().add(new Track(startNode, endNode));
        }
        visualizer.getChildren().remove(guide);
    }

    @Override
    public void cleanup() {
        visualizer.setOnMouseClicked(null);
        visualizer.setOnDragDetected(null);
        for (Station s : target.stationsProperty().get()) {
            StationGraphic sb = (StationGraphic)s.getGraphic();
            sb.setOnDragDropped(null);
            sb.setOnMousePressed(null);
            sb.setOnMouseDragged(null);
            sb.setOnMouseReleased(null);
        }
        visualizer.setOnDragDone(null);
    }

    private class GuideLine extends Line {
        public GuideLine(double startX, double startY, double endX, double endY) {
            super(startX, startY, endX, endY);
            setStroke(Color.GRAY); // Set line color to gray
            getStrokeDashArray().addAll(8d, 8d); // Set dashes and gaps for the guideline
            setStrokeWidth(4); // Set line width
            setMouseTransparent(true); // Make the guideline non-intractable
        }
    }
}