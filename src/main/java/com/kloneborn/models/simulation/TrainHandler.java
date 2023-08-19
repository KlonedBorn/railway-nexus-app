package com.kloneborn.models.simulation;

import com.kloneborn.models.attributes.TrainHandlerBehavior;
import com.kloneborn.models.states.RSStatus;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class TrainHandler extends SystemEntity {
    private ObjectProperty<Train> trainProperty;
    private ObjectProperty<TrainHandlerBehavior> behaviorProperty;

    public TrainHandler(Train train, TrainHandlerBehavior behavior) {
        this.trainProperty = new SimpleObjectProperty<>(this, "train-handler", train);
        this.behaviorProperty = new SimpleObjectProperty<TrainHandlerBehavior>(this, "train-handler-behavior",
                behavior);
    }

    public TrainHandler(Train train) {
        this(train, null);
    }

    public TrainHandler() {
        this(null);
    }

    public boolean accept(Train train) {
        if (isOpen()) {
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

    @Override
    public boolean verify() {
        return getTrain().verify();
    }

    public ObjectProperty<TrainHandlerBehavior> behaviorProperty() {
        return behaviorProperty;
    }

    public TrainHandlerBehavior getBehavior() {
        return this.behaviorProperty.get();
    }

    public void setBehavior(TrainHandlerBehavior behavior) {
        this.behaviorProperty.set(behavior);
    }
}