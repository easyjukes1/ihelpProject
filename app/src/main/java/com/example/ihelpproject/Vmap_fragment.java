package com.example.ihelpproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class Vmap_fragment extends Fragment implements OnMapReadyCallback {
SupportMapFragment mapFragment;
    View view;
    GoogleMap map;
    public Vmap_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vmap, container, false);

       // mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
///
      //  assert mapFragment != null;
//        mapFragment.getMapAsync(this);
        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
      //  map = googleMap;
    }
}
