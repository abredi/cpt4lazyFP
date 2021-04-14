package com.cpt4lazy.model;

public class Job {

    private String jobID;
    private String jobTitle;
    private String place;

    public Job() {

    }

    public Job(String jobID, String jobTitle, String place) {
        this.jobID = jobID;
        this.jobTitle = jobTitle;
        this.place = place;
    }

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobID='" + jobID + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
