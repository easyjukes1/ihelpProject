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



public class RegisterSupervisorFragment extends Fragment {

    private FirebaseAuth mAuth;
    TextInputLayout et_name, et_email, et_username, et_password;


    supervisor supervisorUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;
        view = inflater.inflate(R.layout.fragment_register_supervisor, container, false);
        mAuth = FirebaseAuth.getInstance();
        Button btn_create;
        final DatabaseReference databaseRegisterSuperVisor;

        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        et_username = view.findViewById(R.id.et_username);
        databaseRegisterSuperVisor = FirebaseDatabase.getInstance().getReference("supervisorUsers");

        btn_create = view.findViewById(R.id.btn_create);


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getEditText().getText().toString();
                String password = et_password.getEditText().getText().toString();
                String name = et_name.getEditText().getText().toString();
                String username = et_username.getEditText().getText().toString();
                if (validateEmail(email) | validatePassword(password) | validateName(name) | validateUsername(username)){

                    String id = databaseRegisterSuperVisor.push().getKey();
                    supervisorUser = new supervisor(id,"supervisor", name, email, username, password);

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    FirebaseDatabase.getInstance().getReference("supervisorUsers")
                                            .child(mAuth.getCurrentUser().getUid())
                                            .setValue(supervisorUser).addOnCompleteListener(new OnCompleteListener<Void>() {
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

}

