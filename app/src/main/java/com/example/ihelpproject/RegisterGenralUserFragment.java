package com.example.ihelpproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterGenralUserFragment extends Fragment {
    private FirebaseAuth mAuth;
    EditText et_name, et_email, et_username, et_password, et_age, et_address, et_phonenumber;
    View view;
    Button btn_create;
    GenralUser genralUser;
    DatabaseReference databaseRegisterGeneralUser;


    public RegisterGenralUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_register_genral_user, container, false);
        mAuth = FirebaseAuth.getInstance();

        et_address = view.findViewById(R.id.et_address);
        et_phonenumber = view.findViewById(R.id.et_phonenumber);
        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        et_username = view.findViewById(R.id.et_username);
        et_age = view.findViewById(R.id.et_age);
        databaseRegisterGeneralUser = FirebaseDatabase.getInstance().getReference("generalUser");

        btn_create = view.findViewById(R.id.btn_create);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                String name = et_name.getText().toString();
                String username = et_username.getText().toString();
                String address = et_address.getText().toString();
                int phoneNumber = Integer.parseInt(et_phonenumber.getText().toString());
                String age = et_age.getText().toString();
                String id = databaseRegisterGeneralUser.push().getKey();

                genralUser = new GenralUser(id,name,email,username,password,age,address,phoneNumber,"generalUser");
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                FirebaseDatabase.getInstance().getReference("generalUser")
                                        .child(mAuth.getCurrentUser().getUid())
                                        .setValue(genralUser).addOnCompleteListener(new OnCompleteListener<Void>() {
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
        });


        return view;

    }

}
