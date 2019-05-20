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


public class supervisorVolunteer_fragment extends Fragment {
    View view;
    private List<Employees> listSupervisorVolunteers;
    RecyclerView recyclerView;
    RecyclerViewSupervisorVolunteerAdapter recyclerAdapter;

    public supervisorVolunteer_fragment() {
        // Required empty public constructor
    }


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
        FirebaseDatabase.getInstance().getReference("employeeUser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Object getV = dataSnapshot1.getValue();
                    HashMap<String, String> HashAddJob = (HashMap<String, String>) getV;
                    String volunteerName= HashAddJob.get("name");
                   //temp string age ...
                   // String age = HashAddJob.get("age");
                    listSupervisorVolunteers.add(new  Employees(volunteerName));


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
