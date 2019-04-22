package com.ticka.application.models;

import java.io.Serializable;

public class SideModel implements Serializable {

    private int icon;
    private String title;

    public SideModel(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
