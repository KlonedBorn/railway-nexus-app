package com.kloneborn.models.simulation;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import com.kloneborn.App;
import com.kloneborn.component.graphics.StationGraphic;
import com.kloneborn.models.attributes.Graphic;
import com.kloneborn.models.attributes.GraphicHandler;
import com.kloneborn.models.attributes.GraphicSupplier;
import com.kloneborn.models.attributes.StationHandlerBehavior;
import com.kloneborn.models.attributes.TrainHandlerBehavior;
import com.kloneborn.models.states.RSStatus;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class Station extends TrainHandler implements GraphicSupplier {

    private final ListProperty<Track> tracksProperty;
    private StationGraphic graphics;

    public Station(String name) {
        this(name, null, List.of());
    }

    public Station(String name, Train train, Collection<? extends Track> tracks) {
        super(train);
        this.setName(name);
        setBehavior(new StationHandlerBehavior(this));
        this.tracksProperty = new SimpleListProperty<>(this, "paths", FXCollections.observableArrayList(tracks));
        this.graphics = new StationGraphic(this);
    }

    public ListProperty<Track> tracksProperty() {
        return tracksProperty;
    }

    public ObservableList<Track> getTracks() {
        return tracksProperty.get();
    }

    @Override
    public Node getGraphic() {
        return this.graphics;
    }

    public boolean isConnectedTo(Station endNode) {
        return false;
    }
}