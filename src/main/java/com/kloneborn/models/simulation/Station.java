package com.kloneborn.models.simulation;

import java.util.Collection;

import com.kloneborn.models.attributes.TrainHandlerBehavior;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Station extends TrainHandler {

    private final ListProperty<Track> tracksProperty;

    public Station(Train train, Collection<? extends Track> tracks ) {
        super(train);
        setBehavior(new StationHandlerBehavior(this));
        this.tracksProperty = new SimpleListProperty<>(this, "paths", FXCollections.observableArrayList(tracks));
    }

    public ListProperty<Track> tracksProperty() {
        return tracksProperty;
    }
    
    public ObservableList<Track> getTracks(){
        return tracksProperty.get();
    }

    private class StationHandlerBehavior implements TrainHandlerBehavior{
        private Station owner;

        public StationHandlerBehavior(Station owner) {
            this.owner = owner;
        }
    }
}