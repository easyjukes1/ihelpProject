package com.example.ihelpproject.charity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ihelpproject.R;
import com.example.ihelpproject.classes.CharityAddJob;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;

public class CharityAddJobActivity extends AppCompatActivity {
    TextView changeImages;
    DatabaseReference databaseAddjob;
    DatabaseReference databaseVolunteersJobs;
    private static final int gallery = 2;
    StorageReference storageReference;
    Uri uri;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_add_job);

        Button btn_create;
        final TextInputLayout et_jobTitle, et_briefDescription, et_description, et_phoneNumber;
        btn_create = findViewById(R.id.btn_create);
        et_jobTitle = findViewById(R.id.et_jobTitle);
        et_briefDescription = findViewById(R.id.et_briefDescription);
        et_description = findViewById(R.id.et_description);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);

        imageView=findViewById(R.id.img_genralProfileImage);
        Calendar calendar = Calendar.getInstance();
        final String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        storageReference = FirebaseStorage.getInstance().getReference("jobImages");
        databaseAddjob = FirebaseDatabase.getInstance().getReference("CharityAddjob").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseVolunteersJobs = FirebaseDatabase.getInstance().getReference("volunteersJobs");

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
                final String jobTitle = et_jobTitle.getEditText().getText().toString();
                final String briefDescription = et_briefDescription.getEditText().getText().toString();
                final String description = et_description.getEditText().getText().toString();
                final String charityId = FirebaseAuth.getInstance().getCurrentUser().getUid();
             //   final String changeImage = changeImages.getText().toString();
                final String phoneNumber = et_phoneNumber.getEditText().getText().toString();


                final StorageReference filepath = storageReference.child(jobTitle);
                StorageTask urlTask = filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                //to create unique id for charity add Jobs
                                String idAddJob = databaseAddjob.push().getKey();

                                CharityAddJob job = new CharityAddJob(idAddJob, jobTitle, briefDescription, description, charityId, phoneNumber, currentDate,uri.toString());

                                // we will store the data on the generated id .
                                databaseAddjob.child(idAddJob).setValue(job);
                                databaseVolunteersJobs.child(idAddJob).setValue(job);
                                Toast.makeText(CharityAddJobActivity.this, "job added", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), CharityHomePageActivity.class);
                                startActivity(i);


                                ;
                            }
                        });

                    }
                    });
            }
            }
        );}
    @Override
    public void onResume() {
        super.onResume();
        if(uri != null){
            Picasso.get().load(uri).into(imageView);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == gallery && resultCode == RESULT_OK) {
            if (data.getData() != null) {
                uri = data.getData();
            }
        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
