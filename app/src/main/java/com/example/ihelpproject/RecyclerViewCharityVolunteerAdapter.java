package com.example.ihelpproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewCharityVolunteerAdapter extends RecyclerView.Adapter<RecyclerViewCharityVolunteerAdapter.myViewHolder> {

    private Context context;
    private List<Student> charityVolunteersStidentData;

    public RecyclerViewCharityVolunteerAdapter(Context context, List<Student> charityVolunteersStidentData) {
        this.context = context;
        this.charityVolunteersStidentData = charityVolunteersStidentData;
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
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        myViewHolder.tv_volunteerName.setText(charityVolunteersStidentData.get(i).getName());

        //   myViewHolder.img_charityVolunteer.setImageResource( charityVolunteersData.get(i).getImg_charityVolunteer());

    }

    @Override
    public int getItemCount() {
        return charityVolunteersStidentData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        //  private ImageView img_charityVolunteer;
        private TextView tv_volunteerName;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            //    img_charityVolunteer = itemView.findViewById(R.id.img_charityVolunteer);
            tv_volunteerName = itemView.findViewById(R.id.volunteerName);

        }
    }
}
