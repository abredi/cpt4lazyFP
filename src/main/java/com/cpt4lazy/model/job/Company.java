package com.cpt4lazy.model.job;

public class Company {
    private String display_name;

    public Company(String display_name) {
        this.display_name = display_name;
    }

    public Company() {
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
