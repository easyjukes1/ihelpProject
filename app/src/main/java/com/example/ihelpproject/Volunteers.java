package com.example.ihelpproject;

public class Volunteers {

    private String Name;
    private String Email;
    private String Username;
    private String Password;
    private String Age;
    private String Address;
    private int Phonenumber;
    private String role;

    public Volunteers(String name, String age) {
        Name = name;
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Volunteers(String name, String email, String username, String password, String age, String address, int phonenumber, String role) {
        Name = name;
        Email = email;
        Username = username;
        Password = password;
        Age = age;
        Address = address;
        Phonenumber = phonenumber;
        this.role = role;
    }
}
