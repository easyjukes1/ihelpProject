package com.example.ihelpproject;

public class Employees extends Volunteers {

    private String supervisor;
    private String CompanyName;


    Employees(String id, String name, String email, String username, String password, String age, String address, String phonenumber, String role, String supervisor, String companyName) {
        super(id, name, email, username, password, age, address, phonenumber, role);
        this.supervisor = supervisor;
        CompanyName = companyName;
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


}
