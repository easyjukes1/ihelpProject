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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihelpproject.R;
import com.example.ihelpproject.classes.Student;
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

import java.util.Objects;

public class RegisterStudentFragment extends Fragment {

    private FirebaseAuth mAuth;
    TextInputLayout et_name, et_email, et_username, et_password, et_studentId, et_supervisorName,
            et_age, et_address, et_phonenumber, et_supervisorEmail, et_supervisor_phoneNumber;
    View view;
    RadioButton btn_radio;
    RadioGroup radioGroup;
    private String gender;
    Student studentUser;
    private ProgressDialog progressDialog;
    private Uri uri;
    private static final int gallery = 2;
    StorageReference storageReference;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register_student, container, false);
        mAuth = FirebaseAuth.getInstance();
        Button btn_create;

        et_studentId = view.findViewById(R.id.et_ID);
        et_address = view.findViewById(R.id.et_address);
        et_phonenumber = view.findViewById(R.id.et_phonenumber);
        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        et_username = view.findViewById(R.id.et_username);
        et_age = view.findViewById(R.id.et_age);
        radioGroup = view.findViewById(R.id.radioGroup);
        progressDialog = new ProgressDialog(getActivity());
        imageView =view.findViewById(R.id.img_studentProfileImage);

        storageReference = FirebaseStorage.getInstance().getReference("studentImages");
        et_supervisorName = view.findViewById(R.id.et_supervisorName);
        et_supervisorEmail = view.findViewById(R.id.et_supervisorEmail);
        et_supervisor_phoneNumber = view.findViewById(R.id.et_supervisorPhoneNumber);


        btn_create = view.findViewById(R.id.btn_create);

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
                btn_radio = view.findViewById(radioId);
                gender = (String) btn_radio.getText();
    if (validateEmail(email) && validatePassword(password) && validateName(name) && validateUsername(username) && validateAddress(address) && validatePhoneNumber(phoneNumber) &&
            validateSupervisor(superVisor) && validateAge(age) && validateStudentId(studentId)) {

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
                                        studentUser = new Student(id, name, email, username, password, age, address, phoneNumber, "studentUser", gender, uri.toString(), studentId, superVisor, superVisorEmail, superVisorPhoneNumber);
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

        if (data.getData() != null) {
            uri = data.getData();
        }
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
