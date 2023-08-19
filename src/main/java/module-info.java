module com.kloneborn {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.kloneborn to javafx.fxml;
    exports com.kloneborn;

    opens com.kloneborn.component.graphics to javafx.fxml;
    exports com.kloneborn.component.graphics;

    opens com.kloneborn.component.dialog to javafx.fxml;
    exports com.kloneborn.component.dialog;

    opens com.kloneborn.controller to javafx.fxml;
    exports com.kloneborn.controller;
}