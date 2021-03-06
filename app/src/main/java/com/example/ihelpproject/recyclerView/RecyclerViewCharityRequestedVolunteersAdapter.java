package com.example.ihelpproject.recyclerView;


import android.content.Context;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ihelpproject.R;
import com.example.ihelpproject.classes.Volunteers;
import com.example.ihelpproject.charity.charityRequestedVolunteerDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewCharityRequestedVolunteersAdapter extends RecyclerView.Adapter<RecyclerViewCharityRequestedVolunteersAdapter.myViewHolder> {

    private Context context;
    private List<Volunteers> charityVolunteersData;


    public RecyclerViewCharityRequestedVolunteersAdapter(Context context, List<Volunteers> charityVolunteersData) {
        this.context = context;
        this.charityVolunteersData = charityVolunteersData;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_charityvolunteers, viewGroup, false);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, final int i) {
        myViewHolder.tv_volunteerName.setText(charityVolunteersData.get(i).getName());
        myViewHolder.tv_volunteerAge.setText(charityVolunteersData.get(i).getAge());
        myViewHolder.tv_volunteerGender.setText(charityVolunteersData.get(i).getGender());
        Picasso.get().load(charityVolunteersData.get(i).getImage()).into(myViewHolder.img_charityVolunteer);

        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, charityRequestedVolunteerDetailsActivity.class);
                intent.putExtra("name", charityVolunteersData.get(i).getName());
                intent.putExtra("email", charityVolunteersData.get(i).getEmail());
                intent.putExtra("Username", charityVolunteersData.get(i).getAddress());
                intent.putExtra("address", charityVolunteersData.get(i).getAddress());
                intent.putExtra("phonenumber", charityVolunteersData.get(i).getPhonenumber());
                intent.putExtra("role", charityVolunteersData.get(i).getRole());
                intent.putExtra("Age", charityVolunteersData.get(i).getAge());
                intent.putExtra("id", charityVolunteersData.get(i).getId());
                intent.putExtra("image", charityVolunteersData.get(i).getImage());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return charityVolunteersData.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_volunteerName, tv_volunteerAge, tv_volunteerGender;
        private LinearLayout parentLayout;
        private ImageView img_charityVolunteer;

        myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_volunteerName = itemView.findViewById(R.id.volunteerName);
            parentLayout = itemView.findViewById(R.id.layoutVolunteer);
            tv_volunteerAge = itemView.findViewById(R.id.volunteerAge);
            tv_volunteerGender = itemView.findViewById(R.id.volunteerGender);
            img_charityVolunteer = itemView.findViewById(R.id.img_charityVolunteer);
        }
    }
}
