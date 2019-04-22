package com.ticka.application.models;

import java.io.Serializable;

public class DataModel implements Serializable {

    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
