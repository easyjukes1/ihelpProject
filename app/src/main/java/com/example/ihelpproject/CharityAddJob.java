package com.example.ihelpproject;

public class CharityAddJob {
    private String id;
    private String jobTitle;
    private String jobType;
    private String description;
    private String charityId;
    private String phoneNumber;


    CharityAddJob(String id, String jobTitle, String jobType, String description, String charityId, String phoneNumber) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.description = description;
        this.charityId = charityId;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String getJobTitle() {
        return jobTitle;
    }


    String getJobType() {
        return jobType;
    }


    String getDescription() {
        return description;
    }

}
