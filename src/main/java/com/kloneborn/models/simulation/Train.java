package com.kloneborn.models.simulation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import com.kloneborn.models.attributes.Simulated;
import com.kloneborn.models.attributes.Verifier;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Train implements Verifier, Simulated {

    private UUID uid;
    private BooleanProperty registeredProperty;
    private ObjectProperty<LocalDateTime> timeRegisteredProperty = new SimpleObjectProperty<>();
    private ObjectProperty<LocalTime> startTimeProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Station> locationProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Route> routeProperty = new SimpleObjectProperty<>();

    public Train(Station location,Route route) {
        this.uid = UUID.randomUUID();
        this.registeredProperty = new SimpleBooleanProperty(this, "train-is-registered",false);
        this.timeRegisteredProperty = new SimpleObjectProperty<>(this, "time-train-registered");
        this.startTimeProperty = new SimpleObjectProperty<>(this, "train-start-time");
        this.locationProperty = new SimpleObjectProperty<Station>(this, "train-current-location-at-station", location);
        this.routeProperty = new SimpleObjectProperty<Route>(this, "train-route", route);
    }

    public boolean register(LocalDateTime time) {
        if (isRegistered())
            return false;
        setTimeRegistered(time);
        setRegistered(true);
        return true;
    }

    public Station getCurrentStation(){
        return getLocation();
    }

    public Station getNextStation(){
        return getRoute().getNextStation(getLocation().getName());
    }

    public BooleanProperty registeredProperty(){
        return registeredProperty;
    }

    public void setRegistered(boolean b) {
        this.registeredProperty.set(b);
    }

    @Override
    public void start(){

    }

    @Override
    public void advance(){

    }

    public boolean isRegistered() {
        return this.registeredProperty.get();
    }

    public UUID getID() {
        return uid;
    }

    public ObjectProperty<LocalDateTime> timeRegisteredProperty() {
        return timeRegisteredProperty;
    }

    public ObjectProperty<LocalTime> startTimeProperty() {
        return startTimeProperty;
    }

    public ObjectProperty<Station> locationProperty() {
        return locationProperty;
    }

    public ObjectProperty<Route> routeProperty() {
        return routeProperty;
    }

    public LocalDateTime whenRegistered() {
        return timeRegisteredProperty.get();
    }

    public void setTimeRegistered(LocalDateTime timRegistered) {
        this.timeRegisteredProperty.set(timRegistered);
    }

    public LocalTime getStartTime() {
        return startTimeProperty.get();
    }

    public void setStartTime(LocalTime startTime) {
        this.startTimeProperty.set(startTime);
    }

    public Station getLocation() {
        return locationProperty.get();
    }

    public void setLocation(Station location) {
        this.locationProperty.set(location);
    }

    public Route getRoute() {
        return routeProperty.get();
    }

    public void changeRoute(Route route) {
        this.routeProperty.set(route);
    }

    @Override
    public boolean verify() {
        return true;
    }
}