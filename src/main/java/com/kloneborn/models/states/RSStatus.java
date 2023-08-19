package com.kloneborn.models.states;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum RSStatus {
    OPEN("Open for service",Color.LIGHTGREEN), 
    CLOSED("Closed for maintenance",Color.RED);

    private String message;
    private Paint color;
    private RSStatus(String message, Color color){
        this.message = message;
        this.color = color;
    }
    
    public String getMessage() {
        return message;
    }

    public Paint getColor() {
        return color;
    }
}