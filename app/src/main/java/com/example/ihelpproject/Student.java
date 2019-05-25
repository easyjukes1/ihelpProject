package com.example.ihelpproject;

class Student extends Volunteers {

    private String uniID;
    private String SuperVisor;





    Student(String id, String name, String email, String username, String password, String age, String address, String phonenumber, String role, String uniID, String superVisor) {
        super(id, name, email, username, password, age, address, phonenumber, role);
        this.uniID = uniID;
        SuperVisor = superVisor;
    }


}
