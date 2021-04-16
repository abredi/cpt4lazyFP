package com.cpt4lazy.model;

import  com.cpt4lazy.model.job.Job;

import java.util.List;

public class JobPost {
    private List<Job> results;

    public JobPost() {
    }

    public JobPost(List<Job> results) {
        this.results = results;
    }

    public List<Job> getResults() {
        return results;
    }

    public void setResults(List<Job> results) {
        this.results = results;
    }
}
