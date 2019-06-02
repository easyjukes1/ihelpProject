package com.example.ihelpproject;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SupervisorVolunteer_fragment extends Fragment {
    View view;
    private List<Student> listSupervisorVolunteers;
    RecyclerView recyclerView;
    RecyclerViewSupervisorVolunteerAdapter recyclerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_supervisorvolunteers, container, false);
        recyclerView = view.findViewById(R.id.VolunteerRv);

        recyclerAdapter = new RecyclerViewSupervisorVolunteerAdapter(getContext(), listSupervisorVolunteers);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        listSupervisorVolunteers = new ArrayList<>();

      //  FirebaseDatabase.getInstance().getReference("supervisorUsers")
                //.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
               // .addValueEventListener(
                //new ValueEventListener() {
                  //  @Override
                    //public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      //  for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        //    Object getV = dataSnapshot1.getValue();

//                            HashMap<String, String> HashGetV = (HashMap<String, String>) getV;
  //                          String supervisorId = HashGetV.get("id");
    //                        String supervisorEmail = HashGetV.get("email");
      //                      if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(supervisorId)){

                            //if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(supervisorId)) {
          //                      getEmployee(supervisorEmail);}
                            //}
                        }
        //            }

            //        @Override
              //      public void onCancelled(@NonNull DatabaseError databaseError) {

          //          }
        //        }
        //);


    //}

    private void getEmployee(final String superVisorEmail) {
        FirebaseDatabase.getInstance().getReference("studentUser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Object getV = dataSnapshot1.getValue();
                    HashMap<String, String> hashGetv = (HashMap<String, String>) getV;
                    if (superVisorEmail.equals(hashGetv.get("superVisorEmail"))) {
                        String volunteerName = hashGetv.get("name");
                        String volunteerAge = hashGetv.get("age");
                        String volunteerAddress = hashGetv.get("address");
                        String volunteerEmail = hashGetv.get("email");
                        String volunteerId = hashGetv.get("id");
                        String volunteerSuperVisor = hashGetv.get("superVisor");
                        String volunteerUniID = hashGetv.get("uniID");
                        String volunteerUsername = hashGetv.get("username");
                        String volunteerRole = hashGetv.get("role");
                        String volunteerNumber = hashGetv.get("phonenumber");
                        Student studentUser = new Student(volunteerId, volunteerName, volunteerEmail, volunteerUsername,
                                null, volunteerAge, volunteerAddress, volunteerNumber, volunteerRole, volunteerUniID, volunteerSuperVisor,null,null);

                        listSupervisorVolunteers.add(studentUser);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(recyclerAdapter);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //when there is an error
            }
        });
    }
}
