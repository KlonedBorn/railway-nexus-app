package com.kloneborn.models.simulation;

import com.kloneborn.models.attributes.Graphic;
import com.kloneborn.models.attributes.GraphicHandler;
import com.kloneborn.models.attributes.GraphicSupplier;
import com.kloneborn.models.states.SignalCode;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SignalLight extends SystemEntity implements GraphicSupplier {
    private Track owner;
    private ObjectProperty<SignalCode> signalProperty;

    public SignalLight(Track owner) {
        super("signal light of " + owner.getName());
        owner = owner;
        signalProperty = new SimpleObjectProperty<SignalCode>(this, "track-signal-light", SignalCode.CLEAR_AHEAD);
    }

    public void toggle() {
        if (getSignal() == SignalCode.CLEAR_AHEAD)
            setSignal(SignalCode.OCCUPIED);
        else
            setSignal(SignalCode.CLEAR_AHEAD);
    }

    public ObjectProperty<SignalCode> signalProperty() {
        return signalProperty;
    }

    public void setSignal(SignalCode signal) {
        signalProperty.set(signal);
    }

    public SignalCode getSignal() {
        return signalProperty.get();
    }

    @Override
    public boolean verify() {
        // TODO Signal light verifier
        return true;
    }
    
    @Override
    public Node getGraphic() {
        return null;
    }
}