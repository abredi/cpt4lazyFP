package com.cpt4lazy.model.job;

public class Job {
    private String jobPosition;
    private String description;
    private String benefits;
    private Location location;
    private Company jobPlace;

    public Job(String jobPosition, String description, String benefits, Location location, Company jobPlace) {
        this.jobPosition = jobPosition;
        this.description = description;
        this.benefits = benefits;
        this.location = location;
        this.jobPlace = jobPlace;
    }

    public Job() {
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Company getJobPlace() {
        return jobPlace;
    }

    public void setJobPlace(Company jobPlace) {
        this.jobPlace = jobPlace;
    }
}



