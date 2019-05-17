package com.example.ihelpproject;


import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;


public class RegisterCharityFragment extends Fragment {
    private FirebaseAuth mAuth;
    EditText et_name, et_phonenumber1, et_email, et_username, et_password, et_address;
    View view;
    Button btn_create, btn_img;
    Charity charityUser;

    public RegisterCharityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register_charity, container, false);
        mAuth = FirebaseAuth.getInstance();
        et_address = view.findViewById(R.id.et_address);
        et_phonenumber1 = view.findViewById(R.id.et_phoneNumber);
        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        et_username = view.findViewById(R.id.et_username);
        btn_create = view.findViewById(R.id.btn_create);


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = et_email.getText().toString();
                final String password = et_password.getText().toString();
                final String name = et_name.getText().toString();
                final String address = et_address.getText().toString();
                final String phoneNumber = et_phonenumber1.getText().toString();


                charityUser = new Charity("charity", name, address, email, password, phoneNumber);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                FirebaseDatabase.getInstance().getReference("charityUser")
                                        .child(mAuth.getCurrentUser().getUid())
                                        .setValue(charityUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Intent i = new Intent(getActivity(), RegisterAccountCheckActivity.class);
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

