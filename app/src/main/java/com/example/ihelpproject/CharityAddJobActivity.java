package com.example.ihelpproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CharityAddJobActivity extends AppCompatActivity {

    DatabaseReference databaseAddjob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_add_job);

        Button btn_create;
        final EditText et_jobTitle, et_briefDescription, et_description;
        btn_create = findViewById(R.id.btn_create);
        et_jobTitle = findViewById(R.id.et_jobTitle);
        et_briefDescription = findViewById(R.id.et_briefDescription);
        et_description = findViewById(R.id.et_description);

        databaseAddjob = FirebaseDatabase.getInstance().getReference("CharityAddjob");

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jobTitle = et_jobTitle.getText().toString();
                String briefDescription = et_briefDescription.getText().toString();
                String description = et_description.getText().toString();

                //to create unique id for charity add Jobs
                String id = databaseAddjob.push().getKey();
                //creating an object of charity add job
                CharityAddJob addJob = new CharityAddJob(id, jobTitle, briefDescription, description);

                // we will store the data on the genrated id .
                databaseAddjob.child(id).setValue(addJob);
                Toast.makeText(CharityAddJobActivity.this, "job added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), charityHomePageActivity.class);
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
