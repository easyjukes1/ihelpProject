package com.example.ihelpproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class charityVolunteerDetailsActivity extends AppCompatActivity {
    TextView tv_name, tv_email,tv_phonenumber,tv_age,tv_address, tv_role,tv_supervisorName,tv_supervisorEmail,tv_supervisorPhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_volunteer_details);

        final Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        final String phonenumber = intent.getStringExtra("phonenumber");
        String address = intent.getStringExtra("address");
        String age = intent.getStringExtra("Age");
        String role = intent.getStringExtra("role");
       // String supervisorName = intent.getStringExtra("supervisorName");
        //String supervisorEmail = intent.getStringExtra("supervisorEmail");
       // String supervisorPhoneNumber = intent.getStringExtra("supervisorPhoneNumber");




        tv_name = findViewById(R.id.tv_charityName);
        tv_email = findViewById(R.id.tv_charityEmail);
        tv_address = findViewById(R.id.tv_charityAddress);
        tv_phonenumber = findViewById(R.id.tv_charityPhoneNumber);
        tv_age = findViewById(R.id.tv_age);
        tv_role = findViewById(R.id.tv_role);
        tv_supervisorName = findViewById(R.id.tv_supervisorName);
        tv_supervisorEmail= findViewById(R.id.tv_supervisorEmail);
        tv_supervisorPhoneNumber = findViewById(R.id.tv_supervisorPhoneNumber);

        tv_name.setText(name);
        tv_address.setText(address);
        tv_email.setText(email);
        tv_phonenumber.setText(phonenumber);
        tv_age.setText(age);
        tv_role.setText(role);
       // tv_supervisorName.setText(supervisorName);
       // tv_supervisorEmail.setText(supervisorEmail);
       // tv_supervisorPhoneNumber.setText(supervisorPhoneNumber);

        tv_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Uri call  = Uri.parse("tel:"+phonenumber);
               Intent intent1 = new Intent(Intent.ACTION_DIAL,call);
                startActivity(intent1);
            }
        });
    }
}
