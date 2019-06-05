package com.example.ihelpproject;

public class Employees extends Volunteers {

    private String supervisor;
    private String CompanyName;
    private String supervisorEmail;
    private String supervisorphoneNumber;

    public Employees(String id, String name, String email, String username, String password, String age, String address, String phonenumber, String role, String gender, String supervisor, String companyName, String supervisorEmail, String supervisorphoneNumber) {
        super(id, name, email, username, password, age, address, phonenumber, role, gender);
        this.supervisor = supervisor;
        CompanyName = companyName;
        this.supervisorEmail = supervisorEmail;
        this.supervisorphoneNumber = supervisorphoneNumber;
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

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }
}
