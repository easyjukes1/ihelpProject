package com.example.ihelpproject;


public class Charity {
    private String id;
    private String role;
    private String name;
    private String address;
    private String email;
    private String password;
    private String phonenumber;
    private String details;
    private String picture;
    private double Latitude, longitude;

    public Charity(String id, String role, String name, String address, String email, String password, String phonenumber, String details, String picture, double latitude, double longitude) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.details = details;
        this.picture = picture;
        Latitude = latitude;
        this.longitude = longitude;
    }

    public Charity(String role, String name, String address, String email, String password, String phonenumber) {
        this.role = role;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
