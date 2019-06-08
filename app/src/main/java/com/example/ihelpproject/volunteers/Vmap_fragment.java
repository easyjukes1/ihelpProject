package com.example.ihelpproject.volunteers;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ihelpproject.R;
import com.example.ihelpproject.classes.Charity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Vmap_fragment extends Fragment implements OnMapReadyCallback {
    private List<Charity> listCharity;
    View view;
    SupportMapFragment mapFragment;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_map, container, false);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
        if (mapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapView, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return view;

    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        listCharity = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("charityUser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Object charityUserStrings = dataSnapshot1.getValue();
                    HashMap<String, String> hashGetCharity = (HashMap<String, String>) charityUserStrings;
                    HashMap<String, Double> charityUser1 = (HashMap<String, Double>) charityUserStrings;
                    String charityName = hashGetCharity.get("name");
                    Double latitude = charityUser1.get("latitude");
                    Double longitude = charityUser1.get("longitude");

                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(latitude, longitude))
                            .title(charityName));

                    LatLng Amman = new LatLng(31.947572, 35.933473);
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Amman, 10f));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //when there is an error
            }
        });


    }

}
