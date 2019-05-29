package com.example.ihelpproject;

public class Volunteers {
    private String id;
    private String Name;
    private String Email;
    private String Username;
    private String Password;
    private String Age;
    private String Address;
    private String Phonenumber;
    private String role;


    public Volunteers(String id, String name, String email, String username, String password, String age, String address, String phonenumber, String role) {
        this.id = id;
        Name = name;
        Email = email;
        Username = username;
        Password = password;
        Age = age;
        Address = address;
        Phonenumber = phonenumber;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String  getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }


}
