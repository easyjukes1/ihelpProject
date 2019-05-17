package com.example.ihelpproject;

public class charityVolunteers {

    private String id;
    private String VolunteerName;


    public void setVolunteerName(String volunteerName) {
        VolunteerName = volunteerName;
    }

    public charityVolunteers(String id, String volunteerName) {
        this.id = id;
        VolunteerName = volunteerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVolunteerName() {
        return VolunteerName;
    }


}