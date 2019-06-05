package com.example.ihelpproject.classes;

public class Student extends Volunteers {

    private String uniID;
    private String supervisor;
    private String supervisorEmail;
    private String supervisorphoneNumber;

    public Student(String id, String name, String email, String username, String password, String age, String address, String phonenumber, String role, String gender, String uniID, String supervisor, String supervisorEmail, String supervisorphoneNumber) {
        super(id, name, email, username, password, age, address, phonenumber, role, gender);
        this.uniID = uniID;
        this.supervisor = supervisor;
        this.supervisorEmail = supervisorEmail;
        this.supervisorphoneNumber = supervisorphoneNumber;
    }

    public String getUniID() {
        return uniID;
    }

    public void setUniID(String uniID) {
        this.uniID = uniID;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }

    public String getSupervisorphoneNumber() {
        return supervisorphoneNumber;
    }

    public void setSupervisorphoneNumber(String supervisorphoneNumber) {
        this.supervisorphoneNumber = supervisorphoneNumber;
    }
}
