package com.example.ihelpproject;

public class VcharityJobs {
    private String charityName;
    private String charityAddress;
    private String charityType;
    private int photo;

    VcharityJobs(String charityName, String charityAddress, String charityType, int photo) {
        this.charityName = charityName;
        this.charityAddress = charityAddress;
        this.charityType = charityType;
        this.photo = photo;
    }

    public String getCharityName() {
        return charityName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    String getCharityAddress() {
        return charityAddress;
    }

    public void setCharityAddress(String charityAddress) {
        this.charityAddress = charityAddress;
    }

    String getCharityType() {
        return charityType;
    }

    public void setCharityType(String charityType) {
        this.charityType = charityType;
    }

    int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}