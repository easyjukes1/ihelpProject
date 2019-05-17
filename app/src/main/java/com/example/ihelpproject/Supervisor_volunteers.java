package com.example.ihelpproject;

public class Supervisor_volunteers {
    private String VolunteerName;
    private int idNumber;
    private String charityName;
    private int img_Volunteer;

    public Supervisor_volunteers(String volunteerName, int idNumber, String charityName, int img_Volunteer) {
        VolunteerName = volunteerName;
        this.idNumber = idNumber;
        this.charityName = charityName;
        this.img_Volunteer = img_Volunteer;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getVolunteerName() {
        return VolunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        VolunteerName = volunteerName;
    }


    public String getCharityName() {
        return charityName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    public int getImg_Volunteer() {
        return img_Volunteer;
    }

    public void setImg_Volunteer(int img_Volunteer) {
        this.img_Volunteer = img_Volunteer;
    }


}