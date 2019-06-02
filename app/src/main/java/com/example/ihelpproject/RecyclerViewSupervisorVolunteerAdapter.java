package com.example.ihelpproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewSupervisorVolunteerAdapter extends RecyclerView.Adapter<RecyclerViewSupervisorVolunteerAdapter.myViewHolder> {

    private Context context;
    private List<Student> supervisorVolunteersData;

    RecyclerViewSupervisorVolunteerAdapter(Context context, List<Student> supervisorVolunteersData) {
        this.context = context;
        this.supervisorVolunteersData = supervisorVolunteersData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_supervisor_volunteer, viewGroup, false);
        return new myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        myViewHolder.tv_VolunteerName.setText(supervisorVolunteersData.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return supervisorVolunteersData.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_VolunteerName;

        myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_VolunteerName = itemView.findViewById(R.id.VolunteerName);


        }
    }
}
