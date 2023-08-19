package com.kloneborn.component.graphics;

import java.net.URL;
import java.util.ResourceBundle;

import com.kloneborn.App;
import com.kloneborn.component.ComponentFactory;
import com.kloneborn.models.attributes.Graphic;
import com.kloneborn.models.attributes.GraphicHandler;
import com.kloneborn.models.simulation.Station;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class StationGraphic extends VBox implements Initializable, Graphic{
    private static final URL FXML = App.class.getResource("fxml/component/graphics/station-graphics.fxml");
    private Station owner;
    private GraphicHandler handler;
    public StationGraphic(Station owner) {
        this.owner = owner;
        this.handler = new GraphicHandler(this);
        ComponentFactory.attach(FXML, this, this);
    }

    @FXML
    private Circle cr_stationStatus;

    @FXML
    private Label lb_stationName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.lb_stationName.textProperty().bind(owner.nameProperty());
        this.cr_stationStatus.fillProperty().bind(Bindings.createObjectBinding(()->owner.getStatus().getColor(), owner.statusProperty()));
    }

    @Override
    public void buildGraphic() {}

    @Override
    public void onRemovedFromParent(Pane parent) {

    }

    @Override
    public void onAddedToParent(Pane parent) {

    }

    public Station getOwner() {
        return owner;
    }
    
}