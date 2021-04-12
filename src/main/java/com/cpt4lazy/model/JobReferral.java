package com.cpt4lazy.model;

import java.util.List;

public class JobReferral extends Post{

    private String description;
    private List<Request> requests;

    public JobReferral(){}
    public JobReferral(String postText, String description, List<Request> requests) {
        super(postText);
        this.description = description;
        this.requests = requests;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
