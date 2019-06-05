package com.example.ihelpproject.charity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ihelpproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CharityJobDetailsActivity extends AppCompatActivity {
    TextView tv_jobName, tv_jobType, tv_jobDate, tv_jobDescription;
    Button btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_job_details);

        final Intent intent = getIntent();
        final String jobId = intent.getStringExtra("id");
        final String jobDate = intent.getStringExtra("date");
        final String jobDescription = intent.getStringExtra("description");
        final String jobName = intent.getStringExtra("jobName");
        final String jobType = intent.getStringExtra("jobType");

        tv_jobName = findViewById(R.id.tv_jobName);
        tv_jobDate = findViewById(R.id.tv_jobDate);
        tv_jobType = findViewById(R.id.tv_jobType);
        tv_jobDescription = findViewById(R.id.tv_jobDescription);


        btn_delete = findViewById(R.id.btn_delete1);

        tv_jobName.setText(jobName);
        tv_jobDate.setText(jobDate);
        tv_jobType.setText(jobType);
        tv_jobDescription.setText(jobDescription);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(CharityJobDetailsActivity.this);
                alert.setMessage("are you sure you want to decline this request?");
                alert.setCancelable(true);

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("volunteersJobs").child(jobId);
                        databaseReference1.removeValue();
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("CharityAddjob")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child(jobId);
                        databaseReference.removeValue();

                        Intent startActivityCharity = new Intent(CharityJobDetailsActivity.this, CharityHomePageActivity.class);
                        startActivity(startActivityCharity);
                        dialog.cancel();

                    }
                });
                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });


    }
}
