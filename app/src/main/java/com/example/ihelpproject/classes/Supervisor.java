package com.example.ihelpproject.classes;

public class Supervisor {
    private String id;
    private String role;
    private String name;
    private String email;
    private String username;
    private String password;
    private String image;

    public Supervisor(String id, String role, String name, String email, String username, String password, String image) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
