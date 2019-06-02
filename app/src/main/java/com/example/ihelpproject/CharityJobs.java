package com.example.ihelpproject;

public class CharityJobs {
    private String id;
    private String jobName;
    private String jobType;
    private String date;
    private String description;
    private String CharityId;

    CharityJobs(String id, String jobName, String jobType, String date, String description, String charityId) {
        this.id = id;
        this.jobName = jobName;
        this.jobType = jobType;
        this.date = date;
        this.description = description;
        CharityId = charityId;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    String getJobName() {
        return jobName;
    }
    String getJobType() {
        return jobType;
    }
    String getDate() {
        return date;
    }
    String getDescription() {
        return description;
    }

}