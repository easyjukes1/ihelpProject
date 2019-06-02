package com.example.ihelpproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class charityRequestedVolunteerDetailsActivity extends AppCompatActivity {
    TextView tv_name, tv_email, tv_phonenumber, tv_age, tv_address, tv_role, tv_supervisorName, tv_supervisorEmail, tv_supervisorPhoneNumber;
    Button btn_accept, btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_requested_volunteer_details);

        final Intent intent = getIntent();
        final String volunteerId = intent.getStringExtra("id");
        final String volunteerName = intent.getStringExtra("name");
        String volunteerEmail = intent.getStringExtra("email");
        final String volunteerNumber = intent.getStringExtra("phonenumber");
        String volunteerAddress = intent.getStringExtra("address");
        String volunteerAge = intent.getStringExtra("Age");
        String volunteerRole = intent.getStringExtra("role");
        // String supervisorName = intent.getStringExtra("supervisorName");
        //String supervisorEmail = intent.getStringExtra("supervisorEmail");
        // String supervisorPhoneNumber = intent.getStringExtra("supervisorPhoneNumber");

        final GenralUser genralUser = new GenralUser(volunteerId, volunteerName, volunteerEmail, null,
                null, volunteerAge, volunteerAddress, volunteerNumber, volunteerRole);


        tv_name = findViewById(R.id.tv_charityName);
        tv_email = findViewById(R.id.tv_charityEmail);
        tv_address = findViewById(R.id.tv_charityAddress);
        tv_phonenumber = findViewById(R.id.tv_charityPhoneNumber);
        tv_age = findViewById(R.id.tv_age);
        tv_role = findViewById(R.id.tv_role);
        tv_supervisorName = findViewById(R.id.tv_supervisorName);
        tv_supervisorEmail = findViewById(R.id.tv_supervisorEmail);
        tv_supervisorPhoneNumber = findViewById(R.id.tv_supervisorPhoneNumber);

        btn_accept = findViewById(R.id.btn_accept);
        btn_delete = findViewById(R.id.btn_delete);

        tv_name.setText(volunteerName);
        tv_address.setText(volunteerAddress);
        tv_email.setText(volunteerEmail);
        tv_phonenumber.setText(volunteerNumber);
        tv_age.setText(volunteerAge);
        tv_role.setText(volunteerRole);
        // tv_supervisorName.setText(supervisorName);
        // tv_supervisorEmail.setText(supervisorEmail);
        // tv_supervisorPhoneNumber.setText(supervisorPhoneNumber);

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(charityRequestedVolunteerDetailsActivity.this);
                alert.setMessage("are you sure you want to accept this volunteer?");
                alert.setCancelable(true);

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference("acceptedVolunteers")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child(volunteerId)
                                .setValue(genralUser);
                        Intent intent = new Intent(charityRequestedVolunteerDetailsActivity.this, CharityHomePageActivity.class);
                        startActivity(intent);
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

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(charityRequestedVolunteerDetailsActivity.this);
                alert.setMessage("are you sure you want to decline this request?");
                alert.setCancelable(true);

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //     DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                        //           .child(charityVolunteersData.get(i).getId());
                        //   databaseReference.removeValue();
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

        tv_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("tel:" + volunteerName);
                Intent intent1 = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent1);
            }
        });
    }
}
