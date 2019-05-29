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


public class Vjob_fragment extends Fragment {
    private List<CharityAddJob> listJobs;
    RecyclerView recyclerView;
    RecyclerViewVJobsAdapter recyclerAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_vjob, container, false);
        recyclerView = view.findViewById(R.id.jobRv);

        recyclerAdapter = new RecyclerViewVJobsAdapter(getContext(), listJobs);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listJobs = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("CharityAddjob").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Object addJob = dataSnapshot1.getValue();
                    HashMap<String, String> HashAddJob = (HashMap<String, String>) addJob;
                    String id = HashAddJob.get("id");
                    String jobTitle = HashAddJob.get("jobTitle");
                    String jobType = HashAddJob.get("jobType");
                    String description = HashAddJob.get("description");
                    String phoneNumber = HashAddJob.get("phoneNumber");
                    listJobs.add(new CharityAddJob(id, jobTitle, jobType, description,"",phoneNumber));
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //when there is an error
            }
        });


    }

}
