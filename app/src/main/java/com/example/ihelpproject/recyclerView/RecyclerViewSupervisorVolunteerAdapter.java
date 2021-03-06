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
import com.example.ihelpproject.supervisor.supervisorVolunteerDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewSupervisorVolunteerAdapter extends RecyclerView.Adapter<RecyclerViewSupervisorVolunteerAdapter.myViewHolder> {

    private Context context;
    private List<Volunteers> volunteersData;

    public RecyclerViewSupervisorVolunteerAdapter(Context context, List<Volunteers> supervisorVolunteersData) {
        this.context = context;
        this.volunteersData = supervisorVolunteersData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_supervisor_volunteer, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, final int i) {
        myViewHolder.tv_VolunteerName.setText(volunteersData.get(i).getName());
        myViewHolder.tv_volunteerAge.setText(volunteersData.get(i).getAge());
        myViewHolder.tv_volunteerGender.setText(volunteersData.get(i).getGender());
        Picasso.get().load(volunteersData.get(i).getImage()).into(myViewHolder.img_charityVolunteer);
        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, supervisorVolunteerDetailsActivity.class);
                intent.putExtra("name", volunteersData.get(i).getName());
                intent.putExtra("email", volunteersData.get(i).getEmail());
                intent.putExtra("Username", volunteersData.get(i).getAddress());
                intent.putExtra("address", volunteersData.get(i).getAddress());
                intent.putExtra("phonenumber", volunteersData.get(i).getPhonenumber());
                intent.putExtra("role", volunteersData.get(i).getRole());
                intent.putExtra("Age", volunteersData.get(i).getAge());
                intent.putExtra("id", volunteersData.get(i).getId());
                intent.putExtra("image", volunteersData.get(i).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return volunteersData.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_VolunteerName, tv_volunteerAge, tv_volunteerGender;
        private LinearLayout parentLayout;
        private ImageView img_charityVolunteer;

        myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_VolunteerName = itemView.findViewById(R.id.volunteerName);
            parentLayout = itemView.findViewById(R.id.layoutVolunteer);
            tv_volunteerAge = itemView.findViewById(R.id.volunteerAge);
            tv_volunteerGender = itemView.findViewById(R.id.volunteerGender);
            img_charityVolunteer = itemView.findViewById(R.id.img_charityVolunteer);
        }
    }
}
