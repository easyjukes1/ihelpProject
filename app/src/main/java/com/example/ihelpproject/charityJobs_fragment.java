package com.example.ihelpproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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


public class charityJobs_fragment extends Fragment {
    View view;
    private List<charityJobs> listCharityJobs;
    FloatingActionButton addFAb;
    RecyclerViewCharityJobsAdapter recyclerAdapter;
    RecyclerView recyclerView;


    public charityJobs_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_charityjob, container, false);
        addFAb = view.findViewById(R.id.fab_add);

        addFAb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), CharityAddJobActivity.class);
                startActivity(i);

            }
        });
        recyclerView = view.findViewById(R.id.jobRv);
        recyclerAdapter = new RecyclerViewCharityJobsAdapter(getContext(), listCharityJobs);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listCharityJobs = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("CharityAddjob").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCharityJobs.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Object addJob = dataSnapshot1.getValue();
                    HashMap<String, String> HashAddJob = (HashMap<String, String>) addJob;
                    String id = HashAddJob.get("id");
                    String jobTitle = HashAddJob.get("jobTitle");
                    String jobType = HashAddJob.get("jobType");
                    String description = HashAddJob.get("description ");



                    listCharityJobs.add(new charityJobs(id,jobTitle , jobType, description));


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
