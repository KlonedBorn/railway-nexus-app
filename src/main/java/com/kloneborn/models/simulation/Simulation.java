package com.kloneborn.models.simulation;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class Simulation {
    private ListProperty<Station> stationsProperty;
    private ListProperty<Train> trainsProperty;
    private ListProperty<Track> tracksProperty;

    public Simulation() {
        this.stationsProperty = new SimpleListProperty<>(this, "simulation-stations",
                FXCollections.observableArrayList());
        this.trainsProperty = new SimpleListProperty<>(this, "simulation-trains", FXCollections.observableArrayList());
        this.tracksProperty = new SimpleListProperty<>(this, "simulation-tracks", FXCollections.observableArrayList());
    }

    public ListProperty<Station> stationsProperty() {
        return stationsProperty;
    }

    public ListProperty<Train> trainsProperty() {
        return trainsProperty;
    }

    public ListProperty<Track> tracksProperty() {
        return tracksProperty;
    }
}