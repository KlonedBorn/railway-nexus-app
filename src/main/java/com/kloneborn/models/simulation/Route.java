package com.kloneborn.models.simulation;

import java.util.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Route extends SystemEntity {

    private List<Track> tracks = new ArrayList<>();
    private Map<String, Station> stations = new HashMap<>();
    private Station startStation;
    private Station endStation;
    private ObjectProperty<Track> currentTrackProperty = new SimpleObjectProperty<>();

    public Route(Collection<? extends Station> stops) {
        if (stops.isEmpty()) {
            throw new IllegalArgumentException("Route must have at least one stop.");
        }

        Station[] stopsArray = stops.toArray(new Station[0]);
        for (int i = 0; i < stopsArray.length - 1; i++) {
            addTrack(new Track(stopsArray[i], stopsArray[i + 1]));
        }
    }

    public boolean isRoundTrip() {
        return startStation != null && endStation != null && startStation.equals(endStation);
    }

    public Station getStart() {
        return startStation;
    }

    public Station getEnd() {
        return endStation;
    }

    public Station getNextStation(String stationName) {
        // Implement logic to find the next station based on stationName.
        // Return null if not found or at the end of the route.
        return null;
    }

    public Station getPreviousStation(String stationName) {
        // Implement logic to find the previous station based on stationName.
        // Return null if not found or at the beginning of the route.
        return null;
    }

    public boolean canGetTo(String stationName) {
        // Implement logic to check if a station can be reached from the current route.
        return false;
    }

    public void addTrack(Track track) {
        tracks.add(track);
        stations.put(track.getStartStation().getName(), track.getStartStation());
        stations.put(track.getEndStation().getName(), track.getEndStation());

        if (startStation == null) {
            startStation = track.getStartStation();
        }
        endStation = track.getEndStation();
    }

    public void addTracks(Collection<Track> newTracks) {
        for (Track track : newTracks) {
            addTrack(track);
        }
    }

    public void removeTrack(String trackId) {
        // Implement logic to remove a track by trackId.
        // Update startStation and endStation if necessary.
    }

    public boolean containsTrack(String trackId) {
        // Implement logic to check if the route contains a track by trackId.
        return false;
    }

    public void changeLight(String startOfTrack) {
        // Implement logic to change a light on a track starting from the specified station.
    }

    public ObjectProperty<Track> currentTrackProperty() {
        return currentTrackProperty;
    }

    public Track getCurrentTrack() {
        return currentTrackProperty.get();
    }

    public void setCurrentTrack(Track currentTrack) {
        currentTrackProperty.set(currentTrack);
    }

    @Override
    public boolean verify() {
        return true;
    }
}