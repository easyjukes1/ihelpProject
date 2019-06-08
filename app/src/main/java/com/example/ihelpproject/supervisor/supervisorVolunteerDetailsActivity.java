package com.example.ihelpproject.supervisor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ihelpproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class supervisorVolunteerDetailsActivity extends AppCompatActivity {
    TextView tv_name, tv_email, tv_phonenumber, tv_age, tv_address, tv_role, tv_supervisorName, tv_supervisorEmail, tv_supervisorPhoneNumber;
    Button btn_delete;
    LinearLayout ll_name, ll_phoneNumber, ll_email;
    ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_volunteer_details);

        final Intent intent = getIntent();
        final String volunteerId = intent.getStringExtra("id");
        final String volunteerName = intent.getStringExtra("name");
        String volunteerEmail = intent.getStringExtra("email");
        final String volunteerNumber = intent.getStringExtra("phonenumber");
        String volunteerAddress = intent.getStringExtra("address");
        String volunteerAge = intent.getStringExtra("Age");
        String volunteerImage = intent.getStringExtra("image");
        String volunteerRole = intent.getStringExtra("role");

        tv_name = findViewById(R.id.tv_charityName);
        tv_email = findViewById(R.id.tv_charityEmail);
        tv_address = findViewById(R.id.tv_charityAddress);
        tv_phonenumber = findViewById(R.id.tv_charityPhoneNumber);
        tv_age = findViewById(R.id.tv_age);
        tv_role = findViewById(R.id.tv_role);
        tv_supervisorName = findViewById(R.id.tv_supervisorName);
        tv_supervisorEmail = findViewById(R.id.tv_supervisorEmail);
        tv_supervisorPhoneNumber = findViewById(R.id.tv_supervisorPhoneNumber);
        ll_email = findViewById(R.id.ll_email);
        ll_name = findViewById(R.id.ll_name);
        ll_phoneNumber = findViewById(R.id.ll_phonenumber);
        image1 = findViewById(R.id.image);

        if ((volunteerRole.equals("employeeUser") || (volunteerRole.equals("studentUser")))) {
            if (volunteerRole.equals("employeeUser")) {

                FirebaseDatabase.getInstance().getReference("employeeUser").child(volunteerId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Object getV = dataSnapshot.getValue();

                        HashMap<String, String> hashGetV = (HashMap<String, String>) getV;
                        String volunteerSuperVisorEmail = hashGetV.get("supervisorEmail");
                        String volunteerSuperVisorPhoneNumber = hashGetV.get("supervisorphoneNumber");
                        String volunteerSuperVisor = hashGetV.get("supervisor");
                        tv_supervisorName.setText(volunteerSuperVisor);
                        tv_supervisorEmail.setText(volunteerSuperVisorEmail);
                        tv_supervisorPhoneNumber.setText(volunteerSuperVisorPhoneNumber);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } else {

                FirebaseDatabase.getInstance().getReference("studentUser").child(volunteerId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Object getV = dataSnapshot.getValue();
                        HashMap<String, String> hashGetV = (HashMap<String, String>) getV;
                        String volunteerSuperVisorEmail = hashGetV.get("supervisorEmail");
                        String volunteerSuperVisorPhoneNumber = hashGetV.get("supervisorphoneNumber");
                        String volunteerSuperVisor = hashGetV.get("supervisor");
                        tv_supervisorName.setText(volunteerSuperVisor);
                        tv_supervisorEmail.setText(volunteerSuperVisorEmail);
                        tv_supervisorPhoneNumber.setText(volunteerSuperVisorPhoneNumber);
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        } else {
            ll_name.setVisibility(View.GONE);
            ll_email.setVisibility(View.GONE);
            ll_phoneNumber.setVisibility(View.GONE);
        }


        btn_delete = findViewById(R.id.btn_delete1);

        tv_name.setText(volunteerName);
        tv_address.setText(volunteerAddress);
        tv_email.setText(volunteerEmail);
        tv_phonenumber.setText(volunteerNumber);
        tv_age.setText(volunteerAge);
        tv_role.setText(volunteerRole);
        Picasso.get().load(volunteerImage).into(image1);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(supervisorVolunteerDetailsActivity.this);
                alert.setMessage("are you sure you want to decline this request?");
                alert.setCancelable(true);

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteStudent(volunteerId);
                        deleteEmployee(volunteerId);

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("supervisorVolunteers")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child(volunteerId);
                        databaseReference.removeValue();

                        Intent startActivityCharity = new Intent(supervisorVolunteerDetailsActivity.this, SuperVisorHomePageActivity.class);
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

        tv_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call = Uri.parse("tel:" + volunteerName);
                Intent intent1 = new Intent(Intent.ACTION_DIAL, call);
                startActivity(intent1);
            }
        });
    }

    void deleteStudent(String id) {
        FirebaseDatabase.getInstance().getReference("studentUser").child(id).removeValue();

    }

    void deleteEmployee(String id) {
        FirebaseDatabase.getInstance().getReference("employeeUser").child(id).removeValue();
    }
}

