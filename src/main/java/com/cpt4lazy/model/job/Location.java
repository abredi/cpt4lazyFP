package com.cpt4lazy.model.job;

public class Location {
    private String display_name;
    private String[] area;

    public Location(String display_name, String[] area) {
        this.display_name = display_name;
        this.area = area;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public Location() {
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String[] getArea() {
        return area;
    }

    public void setArea(String[] area) {
        this.area = area;
    }
}
