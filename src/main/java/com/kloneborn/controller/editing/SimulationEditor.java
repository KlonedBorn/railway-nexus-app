package com.kloneborn.controller.editing;

import com.kloneborn.models.simulation.Simulation;

public class SimulationEditor {
    private EditorManager manager;
    private Simulation simulation;
    public SimulationEditor(Simulation simulation, EditorManager editorManager) {
        this.simulation = simulation;
        this.manager = editorManager;
    }

    public EditorManager getManager() {
        return this.manager;
    }
}