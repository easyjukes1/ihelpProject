package com.example.ihelpproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CharityAddJobActivity extends AppCompatActivity {

    DatabaseReference databaseAddjob;
    DatabaseReference databaseVolunteersJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_add_job);

        Button btn_create;
        final TextInputLayout et_jobTitle, et_briefDescription, et_description,et_phoneNumber;
        btn_create = findViewById(R.id.btn_create);
        et_jobTitle = findViewById(R.id.et_jobTitle);
        et_briefDescription = findViewById(R.id.et_briefDescription);
        et_description = findViewById(R.id.et_description);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);

        databaseAddjob = FirebaseDatabase.getInstance().getReference("CharityAddjob").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseVolunteersJobs = FirebaseDatabase.getInstance().getReference("volunteersJobs");

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobTitle = et_jobTitle.getEditText().getText().toString();
                String briefDescription = et_briefDescription.getEditText().getText().toString();
                String description = et_description.getEditText().getText().toString();
                String charityId= FirebaseAuth.getInstance().getCurrentUser().getUid();
                String phoneNumber= et_phoneNumber.getEditText().getText().toString();


                //to create unique id for charity add Jobs
                String idAddJob = databaseAddjob.push().getKey();
                String idVolunteersJobs = databaseAddjob.push().getKey();
                //creating an object of charity add job
                CharityAddJob job = new CharityAddJob(idAddJob, jobTitle, briefDescription, description,charityId,phoneNumber);

                // we will store the data on the generated id .
                databaseAddjob.child(idAddJob).setValue(job);
                databaseVolunteersJobs.child(idVolunteersJobs).setValue(job);
                Toast.makeText(CharityAddJobActivity.this, "job added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), CharityHomePageActivity.class);
                startActivity(i);

            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
