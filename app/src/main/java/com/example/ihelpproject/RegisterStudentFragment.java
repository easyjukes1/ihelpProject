package com.example.ihelpproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterStudentFragment extends Fragment {

    private FirebaseAuth mAuth;
    TextInputLayout et_name, et_email, et_username, et_password, et_studentId, et_supervisorName, et_age, et_address, et_phonenumber;
    View view;
    Button btn_create;
    Student studentUser;
    DatabaseReference databaseRegisterStudent;

    public RegisterStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_register_student, container, false);
        mAuth = FirebaseAuth.getInstance();
        et_studentId = view.findViewById(R.id.et_ID);
        et_supervisorName = view.findViewById(R.id.et_supervisorName);
        et_address = view.findViewById(R.id.et_address);
        et_phonenumber = view.findViewById(R.id.et_phonenumber);
        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        et_username = view.findViewById(R.id.et_username);
        et_age = view.findViewById(R.id.et_age);
        databaseRegisterStudent = FirebaseDatabase.getInstance().getReference("studentUser");

        btn_create = view.findViewById(R.id.btn_create);


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getEditText().getText().toString();
                String password = et_password.getEditText().getText().toString();
                String name = et_name.getEditText().getText().toString();
                String username = et_username.getEditText().getText().toString();
                String address = et_address.getEditText().getText().toString();
                String superVisor = et_supervisorName.getEditText().getText().toString();
                String phoneNumber = et_phonenumber.getEditText().getText().toString();
                String studentId = et_studentId.getEditText().getText().toString();
                String age = et_age.getEditText().getText().toString();

                if (validateEmail(email) | validatePassword(password) | validateName(name) | validateUsername(username) | validateAddress(address) | validatePhoneNumber(phoneNumber) |
                        validateSupervisor(superVisor) | validateAge(age) | validateStudentId(studentId)) {

                    String id = databaseRegisterStudent.push().getKey();
                    studentUser = new Student(id, name, email, username, password, age, address, phoneNumber, "student", studentId, superVisor);

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    FirebaseDatabase.getInstance().getReference("studentUser")
                                            .child(mAuth.getCurrentUser().getUid())
                                            .setValue(studentUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Intent i = new Intent(getActivity(), LoginActivity.class);
                                                startActivity(i);
                                                Toast.makeText(getActivity(), "account created", Toast.LENGTH_LONG).show();
                                            } else {

                                                Toast.makeText(getActivity(), "there is something wrong", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                            });
                }


            }
        });


        return view;

    }

    private boolean validateSupervisor(String superVisor) {
        if (superVisor.isEmpty()) {
            et_supervisorName.setError("field cant be empty");
            return false;
        } else {
            et_supervisorName.setError(null);
            et_supervisorName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateStudentId(String studentId) {
        if (studentId.isEmpty()) {
            et_studentId.setError("field cant be empty");
            return false;
        } else {
            et_studentId.setError(null);
            et_studentId.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateAge(String age) {
        if (age.isEmpty()) {
            et_age.setError("field cant be empty");
            return false;
        } else {
            et_age.setError(null);
            et_age.setErrorEnabled(false);
            return true;
        }
    }//end validateAge

    private boolean validateUsername(String username) {
        if (username.isEmpty()) {
            et_username.setError("field cant be empty");
            return false;
        } else {
            et_username.setError(null);
            et_username.setErrorEnabled(false);
            return true;
        }
    }//end validateUsername


    private boolean validateEmail(String email) {

        if (email.isEmpty()) {
            et_email.setError("field cant be empty");
            return false;
        } else {
            et_email.setError(null);
            et_email.setErrorEnabled(false);
            return true;
        }
    }// end validateEmail method.

    private boolean validatePassword(String password) {

        if (password.isEmpty()) {
            et_password.setError("field cant be empty");
            return false;
        } else {
            et_password.setError(null);
            et_password.setErrorEnabled(false);
            return true;
        }
    }// end validatePassword method.

    private boolean validateName(String name) {

        if (name.isEmpty()) {
            et_name.setError("field cant be empty");
            return false;
        } else {
            et_name.setError(null);
            et_name.setErrorEnabled(false);
            return true;
        }
    }// end validateName method.

    private boolean validateAddress(String address) {

        if (address.isEmpty()) {
            et_address.setError("field cant be empty");
            return false;
        } else {
            et_address.setError(null);
            et_address.setErrorEnabled(false);
            return true;
        }
    }// end validateAddress method.

    private boolean validatePhoneNumber(String phoneNumber) {

        if (phoneNumber.isEmpty()) {
            et_phonenumber.setError("field cant be empty");
            return false;
        } else {
            et_phonenumber.setError(null);
            et_phonenumber.setErrorEnabled(false);
            return true;
        }

    }
}
