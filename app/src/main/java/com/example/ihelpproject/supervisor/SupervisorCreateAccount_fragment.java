package com.example.ihelpproject.supervisor;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ihelpproject.R;
import com.example.ihelpproject.classes.Employees;
import com.example.ihelpproject.classes.Student;
import com.example.ihelpproject.registerAndLogin.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class SupervisorCreateAccount_fragment extends Fragment {
    private FirebaseAuth mAuth;
    TextInputLayout et_name, et_email, et_username, et_password, et_studentId, et_supervisorName,
            et_age, et_address, et_phonenumber, et_supervisorEmail, et_supervisor_phoneNumber;
    View view;
    RadioButton btn_radio, btn_radioUserType;
    RadioGroup radioGroup, rg_userType;
    private String gender, userType;
    Student studentUser;
    Employees employeeUser;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_supervisoroverview, container, false);
        mAuth = FirebaseAuth.getInstance();
        Button btn_create;

        progressDialog = new ProgressDialog(getActivity());
        et_studentId = view.findViewById(R.id.et_ID);
        et_address = view.findViewById(R.id.et_address);
        et_phonenumber = view.findViewById(R.id.et_phonenumber);
        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        et_username = view.findViewById(R.id.et_username);
        et_age = view.findViewById(R.id.et_age);
        radioGroup = view.findViewById(R.id.radioGroup);
        rg_userType = view.findViewById(R.id.rg_userType);

        et_supervisorName = view.findViewById(R.id.et_supervisorName);
        et_supervisorEmail = view.findViewById(R.id.et_supervisorEmail);
        et_supervisor_phoneNumber = view.findViewById(R.id.et_supervisorPhoneNumber);


        btn_create = view.findViewById(R.id.btn_create);


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = Objects.requireNonNull(et_email.getEditText()).getText().toString();
                final String password = Objects.requireNonNull(et_password.getEditText()).getText().toString();
                final String name = Objects.requireNonNull(et_name.getEditText()).getText().toString();
                final String username = Objects.requireNonNull(et_username.getEditText()).getText().toString();
                final String address = Objects.requireNonNull(et_address.getEditText()).getText().toString();
                final String superVisor = Objects.requireNonNull(et_supervisorName.getEditText()).getText().toString();
                final String phoneNumber = Objects.requireNonNull(et_phonenumber.getEditText()).getText().toString();
                final String studentId = Objects.requireNonNull(et_studentId.getEditText()).getText().toString();
                final String age = Objects.requireNonNull(et_age.getEditText()).getText().toString();
                final String superVisorEmail = Objects.requireNonNull(et_supervisorEmail.getEditText()).getText().toString();
                final String superVisorPhoneNumber = Objects.requireNonNull(et_phonenumber.getEditText()).getText().toString();
                int radioId = radioGroup.getCheckedRadioButtonId();
                int radioButtonIdUserType = rg_userType.getCheckedRadioButtonId();
                btn_radio = view.findViewById(radioId);
                btn_radioUserType = view.findViewById(radioButtonIdUserType);

                gender = (String) btn_radio.getText();
                userType = (String) btn_radioUserType.getText();


                if (validateEmail(email) && validatePassword(password) && validateName(name) && validateUsername(username) && validateAddress(address) && validatePhoneNumber(phoneNumber) &&
                        validateSupervisor(superVisor) && validateAge(age) && validateStudentId(studentId)) {

                    progressDialog.setMessage("registering user...");
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    String id = mAuth.getCurrentUser().getUid();
                                    if (userType == "studentUser") {
                                        studentUser = new Student(id, name, email, username, password, age, address, phoneNumber, userType, gender,null, studentId, superVisor, superVisorEmail, superVisorPhoneNumber);


                                        FirebaseDatabase.getInstance().getReference(userType)
                                                .child(mAuth.getCurrentUser().getUid())
                                                .setValue(studentUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Intent i = new Intent(getActivity(), SuperVisorHomePageActivity.class);
                                                    startActivity(i);
                                                    Toast.makeText(getContext(), "account created", Toast.LENGTH_LONG).show();
                                                } else {

                                                    Toast.makeText(getActivity(), "there is something wrong", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    } else {

                                        employeeUser = new Employees(id, name, email, username, password, age, address, phoneNumber, userType, gender,null, superVisor, null, superVisorEmail, superVisorPhoneNumber);
                                        FirebaseDatabase.getInstance().getReference(userType)
                                                .child(mAuth.getCurrentUser().getUid())
                                                .setValue(employeeUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Intent i = new Intent(getActivity(), SuperVisorHomePageActivity.class);
                                                    startActivity(i);
                                                    Toast.makeText(getContext(), "account created", Toast.LENGTH_LONG).show();
                                                } else {

                                                    Toast.makeText(getActivity(), "there is something wrong", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    }
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
