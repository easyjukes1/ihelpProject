package com.example.ihelpproject;

class Student extends Volunteers {

    private String uniID;
    private String SuperVisor;
    private String superVisorEmail;
    private String superVisorPhoneNumber;


    public Student(String id, String name, String email, String username, String password, String age, String address, String phonenumber, String role, String uniID, String superVisor, String superVisorEmail, String superVisorPhoneNumber) {
        super(id, name, email, username, password, age, address, phonenumber, role);
        this.uniID = uniID;
        SuperVisor = superVisor;
        this.superVisorEmail = superVisorEmail;
        this.superVisorPhoneNumber = superVisorPhoneNumber;
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

    public String getSuperVisorEmail() {
        return superVisorEmail;
    }

    public void setSuperVisorEmail(String superVisorEmail) {
        this.superVisorEmail = superVisorEmail;
    }

    public String getSuperVisorPhoneNumber() {
        return superVisorPhoneNumber;
    }

    public void setSuperVisorPhoneNumber(String superVisorPhoneNumber) {
        this.superVisorPhoneNumber = superVisorPhoneNumber;
    }
}
