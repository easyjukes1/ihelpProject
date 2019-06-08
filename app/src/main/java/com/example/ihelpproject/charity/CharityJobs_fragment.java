package com.example.ihelpproject.charity;


import android.app.ProgressDialog;
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

import com.example.ihelpproject.R;
import com.example.ihelpproject.classes.CharityAddJob;
import com.example.ihelpproject.recyclerView.RecyclerViewCharityJobsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CharityJobs_fragment extends Fragment {

    private List<CharityAddJob> listCharityJobs;
    RecyclerViewCharityJobsAdapter recyclerAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_charityjob, container, false);



        FloatingActionButton addFAb;
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
        FirebaseDatabase.getInstance().getReference("CharityAddjob").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCharityJobs.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Object addJob = dataSnapshot1.getValue();
                    HashMap<String, String> HashAddJob = (HashMap<String, String>) addJob;
                    String id = HashAddJob.get("id");
                    String jobTitle = HashAddJob.get("jobTitle");
                    String jobType = HashAddJob.get("jobType");
                    String currentDate = HashAddJob.get("date");
                    String phoneNumber = HashAddJob.get("phoneNumber");
                    String description = HashAddJob.get("description");
                    String image = HashAddJob.get("image");
                    listCharityJobs.add(new CharityAddJob(id, jobTitle, jobType,description, null,phoneNumber,currentDate,image));

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
