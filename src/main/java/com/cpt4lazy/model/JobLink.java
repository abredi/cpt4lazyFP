package com.cpt4lazy.model;

public class JobLink extends Post{

    private String url;

    public JobLink(){}
    public JobLink(String postText, String url) {
        super(postText);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
