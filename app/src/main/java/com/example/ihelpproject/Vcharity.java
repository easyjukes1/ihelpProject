package com.example.ihelpproject;

public class Vcharity {
    private String name;
    private String address;
    private String email;
    private String password;
    private String charityDetails;
    private int photo;
    private int number;

    public Vcharity(String name, String address, String email, String password, String charityDetails, int photo, int number) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.charityDetails = charityDetails;
        this.photo = photo;
        this.number = number;
    }

    public Vcharity(String charityDetails, int photo) {
        this.charityDetails = charityDetails;
        this.photo = photo;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCharityDetails() {
        return charityDetails;
    }

    public void setCharityDetails(String charityDetails) {
        this.charityDetails = charityDetails;
    }
}
