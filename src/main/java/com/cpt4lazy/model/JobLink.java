package com.cpt4lazy.model;

import java.util.List;

public class JobLink extends Post{

    private List<String> url;

    public JobLink(){}
    public JobLink(String postText, List<String> url) {
        super(postText);
        this.url = url;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }
}
