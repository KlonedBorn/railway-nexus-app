package com.kloneborn.component.dialog;

import java.net.URL;
import java.util.ResourceBundle;

import com.kloneborn.App;
import com.kloneborn.component.ComponentFactory;
import com.kloneborn.models.simulation.Station;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StationDialog extends Dialog<Station> implements Initializable {
    private static final URL FXML = App.class.getResource("fxml/component/station-dialog.fxml");
    private static final String STATION_NAME_REGEX = "^[a-zA-Z0-9\\s\\-_'\"&,]{3,50}$";

    @FXML
    private Label lb_nameError;

    @FXML
    private TextField lb_stationName;

    private Button bt_finish;

    public StationDialog() {
        ComponentFactory.attach(FXML, getDialogPane(), this);
    }

    @FXML
    void onActionValidateStationNameAndExit(ActionEvent event) {
        if (valid())
            bt_finish.fire();
    }

    private Station convertResults(ButtonType btn) {
        if (btn == ButtonType.FINISH)
            return new Station(lb_stationName.getText());
        return null;
    }

    @FXML
    private void onFinishEventFilter(ActionEvent evt) {
        if (!valid())
            evt.consume();
    }

    private boolean valid() {
        boolean bValid = this.lb_stationName.getText().matches(STATION_NAME_REGEX);
        if (!bValid)
            lb_nameError.setText("Error: Station Name Invalid");
        else
            lb_nameError.setText("");
        return bValid;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lb_nameError.setText("");
        setTitle(App.TITLE + " - Station Creator");
        bt_finish = (Button) getDialogPane().lookupButton(ButtonType.FINISH);
        bt_finish.addEventFilter(ActionEvent.ACTION, this::onFinishEventFilter);
        setResultConverter(this::convertResults);
    }
}