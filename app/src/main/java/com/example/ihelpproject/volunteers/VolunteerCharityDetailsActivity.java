package com.example.ihelpproject.volunteers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.ihelpproject.R;

public class VolunteerCharityDetailsActivity extends AppCompatActivity {
    TextView tv_name, tv_address, tv_email, tv_phonenumber, tv_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_charity_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String email = intent.getStringExtra("email");
        String details = intent.getStringExtra("details");
        final String phoneNumber = intent.getStringExtra("phonenumber");


        tv_name = findViewById(R.id.tv_charityName);
        tv_address = findViewById(R.id.tv_charityAddress);
        tv_email = findViewById(R.id.tv_charityEmail);
        tv_phonenumber = findViewById(R.id.tv_charityPhoneNumber);
        tv_details = findViewById(R.id.tv_charityDetails);

        tv_name.setText(name);
        tv_address.setText(address);
        tv_email.setText(email);
        tv_phonenumber.setText(phoneNumber);
        tv_details.setText(details);

        tv_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("tel:" + phoneNumber);
                Intent intent1 = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent1);
            }
        });
    }
}
