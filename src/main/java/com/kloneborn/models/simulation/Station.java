package com.kloneborn.models.simulation;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import com.kloneborn.App;
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
        return (Node) this.graphics;
    }

    public static class StationGraphic extends VBox implements Graphic{
        private static final URL STATION_ICON_PATH = App.class.getResource("img/icon/train-in-station.png");
        private Station owner;
        private GraphicHandler handler;

        public StationGraphic(Station station) {
            this.owner = station;
            buildGraphic();
            this.handler = new GraphicHandler(this);
        }
        
        public Station getOwner(){
            return owner;
        }

        @Override
        public void onRemovedFromParent(Pane parent) {
            System.out.println("Remove");
        }

        @Override
        public void onAddedToParent(Pane parent) {
            System.out.println("Added");
        }

        @Override
        public void buildGraphic() {
            setSpacing(10);
            setPadding(new Insets(10));
            setAlignment(Pos.CENTER);

            Image icon = new Image(STATION_ICON_PATH.toExternalForm());
            ImageView stationImageView = new ImageView(icon);
            stationImageView.setFitHeight(32.0);
            stationImageView.setFitWidth(32.0);

            Circle circle = new Circle(8.0, Color.BLACK);
            Binding<Paint> onStatusChangeGetColor = Bindings.createObjectBinding(() -> owner.getStatus().getColor(),
                    owner.statusProperty());
            circle.fillProperty().bind(onStatusChangeGetColor);
            circle.setStroke(Color.BLACK);
            circle.setStrokeType(StrokeType.INSIDE);

            HBox iconBox = new HBox(5);
            iconBox.setAlignment(Pos.CENTER);
            iconBox.getChildren().addAll(stationImageView, circle);

            Label nameLabel = new Label(owner.getName());
            nameLabel.textProperty().bind(owner.nameProperty());
            nameLabel.setTextFill(Color.BLACK);

            getChildren().addAll(iconBox, nameLabel);
        }
    }
}