package com.example.ihelpproject;

public class charityJobs {
    private String id;
    private String jobName;
    private String jobType;
    private String jobAddress;
    private int img_charityJob;
    String disc;

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

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public int getImg_charityJob() {
        return img_charityJob;
    }

    public void setImg_charityJob(int img_charityJob) {
        this.img_charityJob = img_charityJob;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public charityJobs(String id, String jobName, String jobType, String disc) {
        this.id = id;
        this.jobName = jobName;
        this.jobType = jobType;
        this.disc = disc;
    }

    public charityJobs(String id, String jobName, String jobType, String jobAddress, int img_charityJob, String disc) {
        this.id = id;
        this.jobName = jobName;
        this.jobType = jobType;
        this.jobAddress = jobAddress;
        this.img_charityJob = img_charityJob;
        this.disc = disc;
    }
}