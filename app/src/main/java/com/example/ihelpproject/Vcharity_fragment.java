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
import java.util.Objects;


public class Vcharity_fragment extends Fragment {
    View view;
    private List<Charity> listCharity;
    RecyclerView recyclerView;
    RecyclerViewVcharityAdapter recyclerAdapter;


    public Vcharity_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vcharity, container, false);
        recyclerView = view.findViewById(R.id.charityRv);

        recyclerAdapter = new RecyclerViewVcharityAdapter(getContext(), listCharity);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listCharity = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("charityUser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Object charityUserStrings = dataSnapshot1.getValue();
                    HashMap<String, String> charityUser = (HashMap<String, String>) charityUserStrings;
                    String password = charityUser.get("password");
                    String name = charityUser.get("name");
                    String email = charityUser.get("email");
                    String address = charityUser.get("address");
                    String phoneumber = charityUser.get("phonenumber");
                    String details = charityUser.get("details");


                    //double Latitude= Double.parseDouble(charityUser.get("Latitude"));
                    //double longitude= Double.parseDouble(charityUser.get("longitude"));
                   // Object charityUserDoubles = dataSnapshot1.getValue();
                   /// HashMap<String, Double> charityUserD = (HashMap<String, Double>) charityUserDoubles;
                    //double Latitude = (double) Double.parseDouble(Objects.<String>requireNonNull(String.valueOf(charityUserD.get("Latitude"))));
                    //double longitude = (double) Double.parseDouble(Objects.<String>requireNonNull(String.valueOf(charityUserD.get("longitude"))));


                    listCharity.add(new Charity("charity", name, address, email, password, phoneumber, details, null,null,null, null));


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
