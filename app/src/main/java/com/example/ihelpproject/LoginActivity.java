package com.example.ihelpproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth mAuth;
    Button btn_login, btn_map;
    EditText et_email, et_password;
    TextView clickHere;
    Spinner spinner;
    String role;
    DatabaseReference dbRef;
    ArrayList<CharSequence> RoleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        btn_login = findViewById(R.id.btn_login);
        btn_map = findViewById(R.id.btn_map);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        clickHere = findViewById(R.id.clickHere);
        spinner = findViewById(R.id.spinner);


        RoleList = new ArrayList<CharSequence>(Arrays.asList(getResources().getStringArray(R.array.roles)));
        ArrayAdapter<CharSequence> adapter;


        adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, RoleList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, map2.class);
                startActivity(i);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = et_email.getText().toString();
                String password1 = et_password.getText().toString();

                mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dbRef = FirebaseDatabase.getInstance().getReference(spinner.getSelectedItem().toString());
                            dbRef.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Object user = dataSnapshot.getValue();
                                    if (user != null) {
                                        HashMap<String, String> user1 = (HashMap<String, String>) user;
                                        if (user1.get("role").equals("charity")) {
                                            Intent icharityHomePageActivity = new Intent(LoginActivity.this, charityHomePageActivity.class);
                                            startActivity(icharityHomePageActivity);
                                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                                        } else if (user1.get("role").equals("employee")) {
                                            Intent iVolunteerHomePageActivity = new Intent(LoginActivity.this, VolunteerHomePageActivity.class);
                                            startActivity(iVolunteerHomePageActivity);
                                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

                                        } else if (user1.get("role").equals("generalUser")) {
                                            Intent i = new Intent(LoginActivity.this, VolunteerHomePageActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

                                        } else if (user1.get("role").equals("student")) {
                                            Intent istudentUser = new Intent(LoginActivity.this, VolunteerHomePageActivity.class);
                                            startActivity(istudentUser);
                                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);

                                        } else if (user1.get("role").equals("supervisor")) {
                                            Intent isuperVisorHomePageActivity = new Intent(LoginActivity.this, superVisorHomePageActivity.class);
                                            startActivity(isuperVisorHomePageActivity);
                                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
                                        }

                                    } else {
                                        Toast.makeText(LoginActivity.this, "please select the right user type", Toast.LENGTH_LONG).show();
                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }//end if
                        else {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });


            }


        });


        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, registerActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        role = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
