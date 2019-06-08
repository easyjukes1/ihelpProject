package com.example.ihelpproject.registerAndLogin;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ihelpproject.R;
import com.example.ihelpproject.classes.Supervisor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;


public class RegisterSupervisorFragment extends Fragment {
    private FirebaseAuth mAuth;
    TextInputLayout et_name, et_email, et_username, et_password;
    Supervisor supervisorUser;
    private ProgressDialog progressDialog;
    private static final int gallery = 2;
    StorageReference storageReference;
    Uri uri;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_register_supervisor, container, false);
        mAuth = FirebaseAuth.getInstance();
        Button btn_create, btn_img;
        imageView =view.findViewById(R.id.img_supervisorProfileImage);

        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        et_username = view.findViewById(R.id.et_username);
        progressDialog = new ProgressDialog(getActivity());
        btn_create = view.findViewById(R.id.btn_create);
        storageReference = FirebaseStorage.getInstance().getReference("supervisorImages");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, gallery);
            }
        });
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = et_email.getEditText().getText().toString();
                final String password = et_password.getEditText().getText().toString();
                final String name = et_name.getEditText().getText().toString();
                final String username = et_username.getEditText().getText().toString();
                if (validateEmail(email) | validatePassword(password) | validateName(name) | validateUsername(username)) {
                    progressDialog.setMessage("registering user...");
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    final StorageReference filepath = storageReference.child(name);
                                    StorageTask urlTask = filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String id = mAuth.getCurrentUser().getUid();
                                                    supervisorUser = new Supervisor(id, "supervisorUsers", name, email, username, password,uri.toString());
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
                                    });

                                }
                            });
                }


            }
        });


        return view;

    }
    @Override
    public void onResume() {
        super.onResume();
        if(uri != null){
            Picasso.get().load(uri).into(imageView);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == gallery && resultCode == RESULT_OK) {
            if (data.getData() != null) {
                uri = data.getData();
            }
        }
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

