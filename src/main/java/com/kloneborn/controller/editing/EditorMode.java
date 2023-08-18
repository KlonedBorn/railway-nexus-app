package com.kloneborn.controller.editing;

public enum EditorMode {
    INSPECT("Inspect", "Allows inspecting entities in the editor."),
    DELETE("Delete", "Enables deleting entities from the editor."),
    MOVE("Move", "Enables moving entities within the editor."),
    STATION("Station", "Enables creating a new train station entity."),
    TRACK("Track", "Enables creating new track segments for trains."),
    TRAIN("Train", "Allows creating a new train entity."),
    UNSELECT("Unselect", "No editing is enabled.");
    
    private final String name;
    private final String info;

    EditorMode(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
}