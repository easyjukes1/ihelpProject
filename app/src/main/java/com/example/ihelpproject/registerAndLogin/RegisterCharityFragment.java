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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihelpproject.R;
import com.example.ihelpproject.classes.Charity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;


public class RegisterCharityFragment extends Fragment {
    private FirebaseAuth mAuth;
    TextInputLayout et_name, et_phonenumber1, et_email, et_username, et_password, et_address, et_details;
    View view;
    ImageView imageView;
    Charity charityUser;
    TextView xView, yView;
    double x, y;
    private ProgressDialog progressDialog;
    private Uri uri;
    private static final int gallery = 2;
    StorageReference storageReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_register_charity, container, false);
        mAuth = FirebaseAuth.getInstance();
        Button btn_create, btn_location;
        imageView=view.findViewById(R.id.img_charityProfileImage);
        progressDialog = new ProgressDialog(getActivity());
        storageReference = FirebaseStorage.getInstance().getReference("charityImages");
        et_address = view.findViewById(R.id.et_address);
        et_phonenumber1 = view.findViewById(R.id.et_phoneNumber);
        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        et_username = view.findViewById(R.id.et_username);
        et_details = view.findViewById(R.id.et_details);
        btn_create = view.findViewById(R.id.btn_create);
        btn_location = view.findViewById(R.id.btn_location);

        xView = view.findViewById(R.id.x);
        yView = view.findViewById(R.id.y);


        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getLocationIntent = new Intent(getActivity(), GoogleMapActivity.class);
                startActivityForResult(getLocationIntent, 1);
            }
        });
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
                final String email = et_email.getEditText().getText().toString().trim();
                final String password = et_password.getEditText().getText().toString().trim();
                final String name = et_name.getEditText().getText().toString().trim();
                final String address = et_address.getEditText().getText().toString().trim();
                final String phoneNumber = et_phonenumber1.getEditText().getText().toString().trim();
                final String details = et_details.getEditText().getText().toString().trim();


                if (validateEmail(email) && validatePassword(password) && validateName(name) && validateAddress(address) && validatePhoneNumber(phoneNumber)
                )
                   // if (validateEmail(email1) == validatePassword(password1))
                {
                    final Double xValue = Double.valueOf(xView.getText().toString().trim());
                    //.trim remove space
                    final Double yValue = Double.valueOf(yView.getText().toString().trim());
                    progressDialog.setMessage("registering user...");
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(email, password)
                            //here we added the pass and email to the authantication not the db
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
                                                    //an id was auto created and given for email and password
                                                    charityUser = new Charity(id, "charityUser", name, address, email, password, phoneNumber, details, uri.toString(), xValue, yValue);
                                                    //create object Charity from class charity
                                                    FirebaseDatabase.getInstance().getReference("charityUser")
                                                            //db ref= class.method.method from db written(path: tbl name) we created a table named charityuser
                                                            .child(mAuth.getCurrentUser().getUid())
                                                            //.child row in table
                                                            .setValue(charityUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            //the info will be saved in this row child
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
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                x = data.getDoubleExtra("x", 0);
                y = data.getDoubleExtra("y", 0);
                String valuex = Double.toString(x);
                String valuey = Double.toString(y);
                xView.setText(valuex);
                yView.setText(valuey);
            }
        }
        if (requestCode == gallery && resultCode == RESULT_OK) {
            if (data.getData() != null) {
                uri = data.getData();
            }
        }

    }

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
            et_phonenumber1.setError("field cant be empty");
            return false;
        } else {
            et_phonenumber1.setError(null);
            et_phonenumber1.setErrorEnabled(false);
            return true;
        }
    }// end validateAddress method.


}



