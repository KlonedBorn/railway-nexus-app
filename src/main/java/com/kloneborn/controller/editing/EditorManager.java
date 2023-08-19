package com.kloneborn.controller.editing;

import java.util.HashMap;
import java.util.Map;

import com.kloneborn.controller.editing.actions.UnSelector;
import com.kloneborn.models.simulation.Simulation;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Pane;

public class EditorManager {
    private Map<EditorMode,EditorAction> actionMap;
    private ObjectProperty<EditorMode> modeProperty;
    private ObjectProperty<EditorAction> actionProperty;
    private StringProperty editorMode, modeInfo, editorError, editorInfo;
    private Pane visualizer;
    private Simulation target;

    public EditorManager(Simulation target, Pane visualizer, StringProperty[] stringProperties) {
        this.target = target;
        this.visualizer = visualizer;
        this.actionMap = new HashMap<>();
        this.modeProperty = new SimpleObjectProperty<>(EditorMode.UNSELECT);
        this.actionProperty = new SimpleObjectProperty<>(new UnSelector());
        getAction().attachToManager(target, visualizer, editorError, editorInfo);
        this.actionMap.put(getMode(),getAction());
        this.editorError = stringProperties[0];
        this.editorInfo = stringProperties[1];
        this.editorMode = stringProperties[2];
        this.modeInfo = stringProperties[3];
        this.editorMode.bind(Bindings.createStringBinding(()-> EditorManager.this.getMode().getName(), modeProperty));
        this.modeInfo.bind(Bindings.createStringBinding(()-> EditorManager.this.getMode().getInfo(), modeProperty));
    }

    public EditorAction getAction() {
        return actionProperty.get();
    }

    public EditorMode getMode() {
        return modeProperty.get();
    }

    public boolean switchTo(EditorMode mode) {
        if (actionMap.containsKey(mode)) {
            EditorAction currentAction = getAction();
            EditorAction newAction = actionMap.get(mode);
            if (currentAction != null) {
                currentAction.cleanup();
            }
            actionProperty.set(newAction);
            modeProperty.set(mode);
            newAction.setup();
            return true;
        }
        return false;
    }

    public void register(EditorMode mode, EditorAction action) {
        this.actionMap.put(mode,action);
        action.attachToManager(target,visualizer, editorMode, modeInfo);
    }
}