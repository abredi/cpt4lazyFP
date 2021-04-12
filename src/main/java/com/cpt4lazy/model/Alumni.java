package com.cpt4lazy.model;

import java.util.List;

public class Alumni extends UserRole {

    private String currentJob;
    private String currentCompany;
    private List<Post> post;

    public Alumni(){}

    public Alumni(String currentJob, String currentCompany, List<Post> post) {
        this.currentJob = currentJob;
        this.currentCompany = currentCompany;
        this.post = post;
    }

    public Alumni(String name, String telephoneNumber, String address, String roleName, String currentJob, String currentCompany, List<Post> post) {
        super(name, telephoneNumber, address, roleName);
        this.currentJob = currentJob;
        this.currentCompany = currentCompany;
        this.post = post;
    }

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
    }

    public String getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}
