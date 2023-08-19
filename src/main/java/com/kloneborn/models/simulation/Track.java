package com.kloneborn.models.simulation;

import com.kloneborn.models.attributes.TrainHandlerBehavior;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Track extends TrainHandler {

        private ObjectProperty<Station> startStationProperty;
        private ObjectProperty<Station> endStationProperty;
        private SignalLight signalLight;

        public Track(Station start, Station end, Train train) {
                super(train);
                setBehavior(new TrackHandlerBehavior(this));
                startStationProperty = new SimpleObjectProperty<Station>(this, "track-start-station", start);
                endStationProperty = new SimpleObjectProperty<Station>(this, "track-end-station", end);
                signalLight = new SignalLight(this);
        }

        public Track(Station start, Station end) {
                this(start, end, null);
        }

        public ObjectProperty<Station> startStationProperty() {
                return startStationProperty;
        }

        public ObjectProperty<Station> endStationProperty() {
                return endStationProperty;
        }

        public Station getStartStation() {
                return startStationProperty.get();
        }

        public void setStartStation(Station startStation) {
                startStationProperty.set(startStation);
        }

        public Station getEndStation() {
                return endStationProperty.get();
        }

        public void setEndStation(Station endStation) {
                endStationProperty.set(endStation);
        }

        public void toggleSignalLight() {
                signalLight.toggle();
        }

        private class TrackHandlerBehavior implements TrainHandlerBehavior {
                private Track owner;

                public TrackHandlerBehavior(Track owner) {
                        owner = owner;
                }
        }

}