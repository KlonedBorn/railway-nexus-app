package com.kloneborn.models.simulation;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class TrainHandler extends SystemEntity {
    private ObjectProperty<Train> trainProperty;

    public TrainHandler(Train train) {
        this.trainProperty = new SimpleObjectProperty<>(this, "train-handler", train);
    }
    
    public boolean accept(Train train) {
        if(isOpen()){
            trainProperty.set(train);
            return true;
        }
        return false;
    }

    public Train release() {
        Train train = trainProperty.get();
        trainProperty.set(null);
        return train;
    }

    public boolean hasTrain() {
        return trainProperty.isNotNull().get();
    }

    public boolean isOpen() {
        return trainProperty.isNull().get();
    }

    public Train getTrain() {
        return trainProperty.get();
    }

    public ObjectProperty<Train> getTrainProperty() {
        return trainProperty;
    }
}