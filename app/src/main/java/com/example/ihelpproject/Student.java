package com.example.ihelpproject;

public class Student extends Volunteers {

    private String uniID;
    private String SuperVisor;

    public Student(String id, String name, String email, String username, String password, String age, String address, int phonenumber, String role) {
        super(id, name, email, username, password, age, address, phonenumber, role);
    }

    public Student(String name) {
        super(name);
    }

    public Student(String id, String name, String email, String username, String password, String age, String address, int phonenumber, String role, String uniID, String superVisor) {
        super(id, name, email, username, password, age, address, phonenumber, role);
        this.uniID = uniID;
        SuperVisor = superVisor;
    }

    public String getUniID() {
        return uniID;
    }

    public void setUniID(String uniID) {
        this.uniID = uniID;
    }

    public String getSuperVisor() {
        return SuperVisor;
    }

    public void setSuperVisor(String superVisor) {
        SuperVisor = superVisor;
    }
}
