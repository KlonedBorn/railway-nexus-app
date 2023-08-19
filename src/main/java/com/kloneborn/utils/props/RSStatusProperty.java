package com.kloneborn.utils.props;

import com.kloneborn.models.states.RSStatus;

import javafx.beans.property.SimpleObjectProperty;

public class RSStatusProperty extends SimpleObjectProperty<RSStatus>{
    public RSStatusProperty() {
    }

    public RSStatusProperty(RSStatus initialValue) {
        super(initialValue);
    }

    public RSStatusProperty(Object bean, String name) {
        super(bean, name);
    }

    public RSStatusProperty(Object bean, String name, RSStatus initialValue) {
        super(bean, name, initialValue);
    }
}