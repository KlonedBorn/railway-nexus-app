package com.kloneborn.models.simulation;

import com.kloneborn.models.attributes.TrainHandlerBehavior;
import com.kloneborn.models.states.SignalCode;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Track extends TrainHandler{

    private ObjectProperty<Station> startStationProperty;
    private ObjectProperty<Station> endStationProperty;
    private SignalLight signalLight;

    public Track(Station start, Station end, Train train) {
        super(train);
        setBehavior(new TrackHandlerBehavior(this));
        this.startStationProperty = new SimpleObjectProperty<Station>(this, "track-start-station", start);
        this.endStationProperty = new SimpleObjectProperty<Station>(this, "track-end-station", end);
        this.signalLight = new SignalLight(this);
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

    public void toggleSignalLight(){
        this.signalLight.toggle();
    }

    public class SignalLight extends SystemEntity{
        private Track owner;
        private ObjectProperty<SignalCode> signalProperty;
        
        public SignalLight(Track owner) {
            super("signal light of " + owner.getName());
            this.owner = owner;
            this.signalProperty = new SimpleObjectProperty<SignalCode>(this, "track-signal-light", SignalCode.CLEAR_AHEAD);
        }

        public void toggle() {
            if(getSignal() == SignalCode.CLEAR_AHEAD)
                setSignal(SignalCode.OCCUPIED);
            else
                setSignal(SignalCode.CLEAR_AHEAD);
        }

        public ObjectProperty<SignalCode> signalProperty(){
            return signalProperty;
        }

        public void setSignal(SignalCode signal){
            this.signalProperty.set(signal);
        }

        public SignalCode getSignal(){
            return this.signalProperty.get();
        }

        @Override
        public boolean verify() {
            // TODO Signal light verifier
            return true;
        }
    }
    private class TrackHandlerBehavior implements TrainHandlerBehavior {
        private Track owner;
        public TrackHandlerBehavior(Track owner) {
            this.owner = owner;
        }
    }
}