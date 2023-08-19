package com.kloneborn.controller.editing;

import com.kloneborn.models.simulation.Simulation;

import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;

public abstract class EditorAction {
    protected Pane visualizer;
    private StringProperty onError;
    private StringProperty onInfo;
    protected Simulation target;

    public void attachToManager(Simulation target, Pane visualizer, StringProperty error, StringProperty info) {
        this.target = target;
        this.visualizer = visualizer;
        this.onError = error;
        this.onInfo = info;
    }

    protected void setError(String err) {
        this.onError.set(err);
    }

    protected void setInfo(String info) {
        this.onInfo.set(info);
    }

    protected Pane getVisualizer() {
        return visualizer;
    }

    abstract public void setup();

    abstract public void cleanup();
}