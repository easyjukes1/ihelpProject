package com.example.ihelpproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class VolunteerCharityDetailsActivity extends AppCompatActivity {
    TextView tv_name, tv_address, tv_email, tv_phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_charity_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String email = intent.getStringExtra("email");
        final String phonenumber = intent.getStringExtra("phonenumber");


        tv_name = findViewById(R.id.tv_charityName);
        tv_address = findViewById(R.id.tv_charityAddress);
        tv_email = findViewById(R.id.tv_charityEmail);
        tv_phonenumber = findViewById(R.id.tv_charityPhoneNumber);

        tv_name.setText(name);
        tv_address.setText(address);
        tv_email.setText(email);
        tv_phonenumber.setText(phonenumber);

        tv_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("tel:" + phonenumber);
                Intent intent1 = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent1);
            }
        });
    }
}
