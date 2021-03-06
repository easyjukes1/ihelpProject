package com.example.ihelpproject.volunteers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihelpproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class VolunteerSubmitJobsActivity extends AppCompatActivity {
    TextView tv_jobTitle, tv_jobType, tv_charityPhoneNumber;
    FirebaseAuth mAuth;
    Button submit;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_charity_jobs);
        tv_jobTitle = findViewById(R.id.tv_jobTitle);
        tv_jobType = findViewById(R.id.tv_jobType);
        tv_charityPhoneNumber = findViewById(R.id.tv_charityPhoneNumber1);
        submit = findViewById(R.id.btn_submit);
        image = findViewById(R.id.image);

        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        String jobTitle = intent.getStringExtra("jobTitle");
        String jobType = intent.getStringExtra("jobType");
        String jobPohneNumber = intent.getStringExtra("phoneNumber");
        String jobImage = intent.getStringExtra("image");
        final String charityPhoneNumber = intent.getStringExtra("PhoneNumber");
        tv_jobTitle.setText(jobTitle);
        tv_jobType.setText(jobType);
        tv_charityPhoneNumber.setText(jobPohneNumber);
        Picasso.get().load(jobImage).into(image);

        tv_charityPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("tel:" + charityPhoneNumber);
                Intent intent1 = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent1);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String jobType = tv_jobType.getText().toString();
                final String jobTittle = tv_jobTitle.getText().toString();
                FirebaseDatabase.getInstance().getReference("volunteersJobs").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Object job = dataSnapshot1.getValue();
                            HashMap<String, String> job1 = (HashMap<String, String>) job;

                            if (jobType.equals(job1.get("jobType")) && jobTittle.equals(job1.get("jobTitle"))) {
                                FirebaseDatabase.getInstance().getReference(job1.get("id")).child(mAuth.getCurrentUser().getUid()).setValue(mAuth.getCurrentUser().getUid())
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(getApplicationContext(), "job submitted", Toast.LENGTH_LONG).show();
                                                Intent i = new Intent(VolunteerSubmitJobsActivity.this, VolunteerHomePageActivity.class);
                                                startActivity(i);
                                            }
                                        });


                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //on error
                    }
                });


            }
        });
    }

}
