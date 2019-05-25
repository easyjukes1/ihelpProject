package com.example.ihelpproject;

public class charityJobs {
    private String id;
    private String jobName;
    private String jobType;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }


    public String getJobType() {
        return jobType;
    }


    public charityJobs(String id, String jobName, String jobType) {
        this.id = id;
        this.jobName = jobName;
        this.jobType = jobType;
    }


}