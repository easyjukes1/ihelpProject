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
import java.util.Objects;


public class CharityRequestedVolunteers_fragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Volunteers> listCharityVolunteers;
    RecyclerViewCharityRequestedVolunteersAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_charityvolunteers, container, false);
        recyclerView = view.findViewById(R.id.VolunteerRv);
        recyclerAdapter = new RecyclerViewCharityRequestedVolunteersAdapter(getContext(), listCharityVolunteers);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listCharityVolunteers = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("CharityAddjob").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Object charity = dataSnapshot1.getValue();
                    HashMap<String, String> charityData = (HashMap<String, String>) charity;
                    if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(charityData.get("charityId"))) {
                        FirebaseDatabase.getInstance().getReference(Objects.requireNonNull(dataSnapshot1.getKey())).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                    getStudent(dataSnapshot2.getValue().toString());
                                    getGeneralUser(dataSnapshot2.getValue().toString());
                                    getEmployee(dataSnapshot2.getValue().toString());

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                //on error
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

    void getStudent(String ID) {
        FirebaseDatabase.getInstance().getReference("studentUser").child(ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object user = dataSnapshot.getValue();
                if (user != null) {
                    HashMap<String, String> userData = (HashMap<String, String>) user;

                    String volunteerName = userData.get("name");
                    String volunteerAge = userData.get("age");
                    String volunteerAddress = userData.get("address");
                    String volunteerEmail = userData.get("email");
                    String volunteerId = userData.get("id");
                    String volunteerSuperVisor = userData.get("superVisor");
                    String volunteerUniID = userData.get("uniID");
                    String volunteerUsername = userData.get("username");
                    String volunteerRole = userData.get("role");
                    String volunteerNumber = userData.get("phonenumber");
                    Student studentUser = new Student(volunteerId, volunteerName, volunteerEmail, volunteerUsername,
                            null, volunteerAge, volunteerAddress, volunteerNumber, volunteerRole, volunteerUniID, volunteerSuperVisor,null,null);

                    listCharityVolunteers.add(studentUser);
                    assert volunteerId != null;
                    FirebaseDatabase.getInstance().getReference("charityVolunteers")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child(volunteerId)
                            .setValue(studentUser);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //on error
            }
        });

    }

    void getGeneralUser(String ID) {
        FirebaseDatabase.getInstance().getReference("generalUser").child(ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object user = dataSnapshot.getValue();
                if (user != null) {
                    HashMap<String, String> userData = (HashMap<String, String>) user;
                    String volunteerName = userData.get("name");
                    String volunteerAge = userData.get("age");
                    String volunteerAddress = userData.get("address");
                    String volunteerEmail = userData.get("email");
                    String volunteerId = userData.get("id");
                    String volunteerUsername = userData.get("username");
                    String volunteerRole = userData.get("role");
                    String volunteerNumber = userData.get("phonenumber");
                    GenralUser genralUser = new GenralUser(volunteerId, volunteerName, volunteerEmail, volunteerUsername,
                            null, volunteerAge, volunteerAddress, volunteerNumber, volunteerRole);

                    listCharityVolunteers.add(genralUser);
                    assert volunteerId != null;
                    FirebaseDatabase.getInstance().getReference("charityVolunteers")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child(volunteerId)
                            .setValue(genralUser);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //on error
            }
        });

    }

    void getEmployee(String ID) {
        FirebaseDatabase.getInstance().getReference("employeeUser").child(ID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object user = dataSnapshot.getValue();
                if (user != null) {
                    HashMap<String, String> userData = (HashMap<String, String>) user;
                    String volunteerName = userData.get("name");
                    String volunteerAge = userData.get("age");
                    String volunteerAddress = userData.get("address");
                    String volunteerEmail = userData.get("email");
                    String volunteerId = userData.get("id");
                    String volunteerSuperVisor = userData.get("superVisor");
                    String volunteerCompanyName = userData.get("companyName");
                    String volunteerUsername = userData.get("username");
                    String volunteerRole = userData.get("role");
                    String volunteerNumber = userData.get("phonenumber");
                    String volunteerSuperVisorEmail = userData.get("superVisorEmail");
                    String volunteerSuperVisorPhoneNumber = userData.get("superVisorPhoneNumber");
                    Employees employeesUser = new Employees(volunteerId, volunteerName, volunteerEmail, volunteerUsername,
                            null, volunteerAge, volunteerAddress, volunteerNumber, volunteerRole, volunteerSuperVisor,
                            volunteerCompanyName,volunteerSuperVisorEmail,volunteerSuperVisorPhoneNumber);
                    assert volunteerId != null;
                    FirebaseDatabase.getInstance().getReference("charityVolunteers")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child(volunteerId)
                            .setValue(employeesUser);
                    listCharityVolunteers.add(employeesUser);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(recyclerAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //on error
            }
        });

    }
}
