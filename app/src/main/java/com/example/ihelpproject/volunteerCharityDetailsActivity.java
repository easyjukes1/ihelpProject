package com.example.ihelpproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class volunteerCharityDetailsActivity extends AppCompatActivity {
    TextView tv_name, tv_address, tv_email, tv_phonenumber, tv_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_charity_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String email = intent.getStringExtra("email");
        String phonenumber = intent.getStringExtra("phonenumber");
        // String details= intent.getStringExtra("details");


        tv_name = findViewById(R.id.tv_charityName);
        tv_address = findViewById(R.id.tv_charityAddress);
        tv_email = findViewById(R.id.tv_charityEmail);
        tv_phonenumber = findViewById(R.id.tv_charityPhoneNumber);

        tv_name.setText(name);
        tv_address.setText(address);
        tv_email.setText(email);
        tv_phonenumber.setText(phonenumber);
    }
}
