package com.example.ihelpproject.charity;

public class CharityAddJob {
    private String id;
    private String jobTitle;
    private String jobType;
    private String description;
    private String charityId;
    private String phoneNumber;
    private String date;

    public CharityAddJob(String id, String jobTitle, String jobType, String description, String charityId, String phoneNumber, String date) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.description = description;
        this.charityId = charityId;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharityId() {
        return charityId;
    }

    public void setCharityId(String charityId) {
        this.charityId = charityId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
