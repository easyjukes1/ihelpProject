package com.example.ihelpproject;

public class Employees extends Volunteers {

    private String supervisor;
    private String CompanyName;

    public Employees(String name, String email, String username, String password,String age, String address, int phonenumber, String role, String supervisor, String companyName) {
        super(name, email, username, password, age, address, phonenumber, role);
        this.supervisor = supervisor;
        CompanyName = companyName;
    }

    public Employees(String name, String age) {
        super(name, age);
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
