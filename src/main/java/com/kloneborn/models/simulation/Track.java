package com.kloneborn.models.simulation;

import com.kloneborn.models.attributes.TrainHandlerBehavior;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Track extends TrainHandler{

    private ObjectProperty<Station> startStationProperty;
    private ObjectProperty<Station> endStationProperty;

    public Track(Station start, Station end, Train train) {
        super(train);
        setBehavior(new TrackHandlerBehavior(this));
        this.startStationProperty = new SimpleObjectProperty<Station>(this, "track-start-station", start);
        this.endStationProperty = new SimpleObjectProperty<Station>(this, "track-end-station", end);
    }

    public Track(Station start, Station end) {
        this(start,end,null);
    }

    public ObjectProperty<Station> startStationProperty(){
        return startStationProperty;
    }

    public ObjectProperty<Station> endStationProperty(){
        return endStationProperty;
    }

    public Station getStartStation() {
        return startStationProperty.get();
    }

    public void setStartStation(Station startStation) {
        this.startStationProperty.set(startStation);
    }

    public Station getEndStation() {
        return endStationProperty.get();
    }

    public void setEndStation(Station endStation) {
        this.endStationProperty.set(endStation);
    }

    private class TrackHandlerBehavior implements TrainHandlerBehavior {
        private Track owner;
        public TrackHandlerBehavior(Track owner) {
            this.owner = owner;
        }
    }
}