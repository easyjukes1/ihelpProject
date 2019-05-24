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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class charityvolunteer_fragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private List<Student> listCharityStudentsVolunteers;
    RecyclerViewCharityVolunteerAdapter recyclerAdapter;


    public charityvolunteer_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_charityvolunteers, container, false);
        recyclerView = view.findViewById(R.id.VolunteerRv);
        recyclerAdapter = new RecyclerViewCharityVolunteerAdapter(getContext(), listCharityStudentsVolunteers);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listCharityStudentsVolunteers = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("studentUser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Object getV = dataSnapshot1.getValue();

                    HashMap<String, String> HashGetV = (HashMap<String, String>) getV;
                    String volunteerName = HashGetV.get("name");
                    String volunteerAge = HashGetV.get("age");
                    String volunteerAddress = HashGetV.get("address");
                    String volunteerEmail = HashGetV.get("email");
                    String volunteerId = HashGetV.get("id");
                    String volunteerSuperVisor = HashGetV.get("superVisor");
                    String volunteerUniID = HashGetV.get("uniID");
                    String volunteerUsername = HashGetV.get("username");

                    listCharityStudentsVolunteers.add(new Student(volunteerId, volunteerName, volunteerEmail, volunteerUsername,
                            null, volunteerAge, volunteerAddress, volunteerAddress, null, volunteerUniID, volunteerSuperVisor));


                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
