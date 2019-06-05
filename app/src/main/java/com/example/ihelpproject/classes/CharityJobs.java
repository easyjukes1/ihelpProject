package com.example.ihelpproject.classes;

public class CharityJobs {
    private String id;
    private String jobName;
    private String jobType;
    private String date;
    private String description;
    private String CharityId;

    public CharityJobs(String id, String jobName, String jobType, String date, String description, String charityId) {
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

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharityId() {
        return CharityId;
    }

    public void setCharityId(String charityId) {
        CharityId = charityId;
    }
}