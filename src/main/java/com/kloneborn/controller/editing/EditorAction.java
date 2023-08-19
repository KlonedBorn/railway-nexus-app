package com.kloneborn.controller.editing;

import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;

public abstract class EditorAction {
    private Pane visualizer;
    private StringProperty onError;
    private StringProperty onInfo;

    public void attachToManager(Pane visualizer, StringProperty error, StringProperty info) {
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