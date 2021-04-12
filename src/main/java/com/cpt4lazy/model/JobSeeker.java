package com.cpt4lazy.model;

import java.util.List;

public class JobSeeker extends UserRole {

    private String preferredJob;
    private String preferredCompany;
    private List<String> skills;
    private List<Experience> experience;
    private List<Request> referralRequest;

    public JobSeeker(){}

    public JobSeeker(String preferredJob, String preferredCompany, List<String> skills, List<Experience> experience, List<Request> referralRequest) {
        this.preferredJob = preferredJob;
        this.preferredCompany = preferredCompany;
        this.skills = skills;
        this.experience = experience;
        this.referralRequest = referralRequest;
    }

    public JobSeeker(String name, String telephoneNumber, String address, String roleName, String preferredJob, String preferredCompany, List<String> skills, List<Experience> experience, List<Request> referralRequest) {
        super(name, telephoneNumber, address, roleName);
        this.preferredJob = preferredJob;
        this.preferredCompany = preferredCompany;
        this.skills = skills;
        this.experience = experience;
        this.referralRequest = referralRequest;
    }

    public String getPreferredJob() {
        return preferredJob;
    }

    public void setPreferredJob(String preferredJob) {
        this.preferredJob = preferredJob;
    }

    public String getPreferredCompany() {
        return preferredCompany;
    }

    public void setPreferredCompany(String preferredCompany) {
        this.preferredCompany = preferredCompany;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public List<Request> getReferralRequest() {
        return referralRequest;
    }

    public void setReferralRequest(List<Request> referralRequest) {
        this.referralRequest = referralRequest;
    }

    @Override
    public String toString() {
        return "JobSeeker{" +
                "preferredJob='" + preferredJob + '\'' +
                ", preferredCompany='" + preferredCompany + '\'' +
                ", skills='" + skills + '\'' +
                ", experience=" + experience +
                ", referralRequest=" + referralRequest +
                '}';
    }
}
