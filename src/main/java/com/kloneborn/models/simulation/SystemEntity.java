package com.kloneborn.models.simulation;

import java.util.UUID;

import com.kloneborn.models.states.RSStatus;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class SystemEntity implements Toggler, Verifier{
    private UUID uid;
    private StringProperty nameProperty;
    private ObjectProperty<RSStatus> statusProperty;

    public SystemEntity(String name, RSStatus status){
        this.uid = UUID.randomUUID();
        this.nameProperty = new SimpleStringProperty(this,"system-entity-name",name);
        this.statusProperty = new SimpleObjectProperty<RSStatus>(this,"system-entity-status",status);
    }

    public SystemEntity(String name){
        this(name,RSStatus.OPEN);
    }

    public SystemEntity(){
        this("Entity");
    }

    public UUID getID() {
        return uid;
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public ObjectProperty<RSStatus> statusProperty() {
        return statusProperty;
    }

    public String getName() {
        return nameProperty.get();
    }

    public void setName(String name) {
        this.nameProperty.set(name);
    }

    public RSStatus getStatus() {
        return statusProperty.get();
    }

    public void setStatus(RSStatus status) {
        this.statusProperty.set(status);
    }
}