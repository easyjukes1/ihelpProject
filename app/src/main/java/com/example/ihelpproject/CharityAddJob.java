package com.example.ihelpproject;

public class CharityAddJob {
   private  String id;
    private String jobTitle;
    private String jobType;
    private String description;
    private  String charityId;


    public CharityAddJob(String id, String jobTitle, String jobType, String description, String charityId) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.description = description;
        this.charityId=charityId;
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
}
