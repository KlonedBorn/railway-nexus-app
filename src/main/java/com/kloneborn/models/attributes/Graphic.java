package com.kloneborn.models.attributes;

import javafx.scene.layout.Pane;

public interface Graphic {
    public void buildGraphic();
    public void onRemovedFromParent(Pane parent);
    public void onAddedToParent(Pane parent);
}