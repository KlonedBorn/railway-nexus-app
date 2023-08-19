package com.kloneborn.models.attributes;


import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class GraphicHandler {

    private Graphic graphic;
    private Node target;

    public <GraphicType extends Node & Graphic> GraphicHandler(GraphicType graphic) {
        this.graphic = graphic;
        this.target = graphic;
        setupParentListeners();
    }

    private void setupParentListeners() {
        target.parentProperty().addListener(new ChangeListener<Parent>() {
            @Override
            public void changed(ObservableValue<? extends Parent> observable, Parent oldParent, Parent newParent) {
                if (oldParent == null && newParent != null) {
                    graphic.onAddedToParent(findParentPane(newParent));
                } else if (oldParent != null && newParent == null) {
                    graphic.onRemovedFromParent(findParentPane(oldParent));
                }
            }
        });
    }

    private ReadOnlyObjectProperty<Parent> findParentProperty(Node node) {
        Parent parent = node.parentProperty().get();
        while (parent != null && !(parent instanceof Pane)) {
            parent = parent.getParent();
        }
        return parent == null ? null : parent.parentProperty();
    }

    private Pane findParentPane(Node node) {
        Parent parent = node.getParent();
        while (parent != null && !(parent instanceof Pane)) {
            parent = parent.getParent();
        }
        return (Pane) parent;
    }
}