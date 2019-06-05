package com.example.ihelpproject.recyclerView;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ihelpproject.R;
import com.example.ihelpproject.charity.CharityAddJob;
import com.example.ihelpproject.charity.CharityJobDetailsActivity;

import java.util.List;

public class RecyclerViewCharityJobsAdapter extends RecyclerView.Adapter<RecyclerViewCharityJobsAdapter.myViewHolder> {

    private Context context;
    private List<CharityAddJob> charityJobsData;

    public RecyclerViewCharityJobsAdapter(Context context, List<CharityAddJob> charityJobsData) {
        this.context = context;
        this.charityJobsData = charityJobsData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_charityjobs, viewGroup, false);
        myViewHolder myViewHolder = new myViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder myViewHolder, final int i) {
        myViewHolder.tv_jobName.setText(charityJobsData.get(i).getJobTitle());
        myViewHolder.tv_briefDescription.setText(charityJobsData.get(i).getJobType());
        myViewHolder.tv_date.setText(charityJobsData.get(i).getDate());

        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, CharityJobDetailsActivity.class);
                intent.putExtra("id", charityJobsData.get(i).getId());
                intent.putExtra("date", charityJobsData.get(i).getDate());
                intent.putExtra("description", charityJobsData.get(i).getDescription());
                intent.putExtra("jobName", charityJobsData.get(i).getJobTitle());
                intent.putExtra("jobType", charityJobsData.get(i).getJobType());
                intent.putExtra("phoneNumber", charityJobsData.get(i).getPhoneNumber());
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return charityJobsData.size();
    }


    static class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_jobName;
        private TextView tv_briefDescription;
        private LinearLayout parentLayout;
        private TextView tv_date;

        myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_jobName = itemView.findViewById(R.id.jobName);
            tv_briefDescription = itemView.findViewById(R.id.briefDescription);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            tv_date = itemView.findViewById(R.id.date);
        }
    }
}
