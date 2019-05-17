package com.example.ihelpproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewCharityJobsAdapter extends RecyclerView.Adapter<RecyclerViewCharityJobsAdapter.myViewHolder> {

    private Context context;
    private List<charityJobs> charityJobsData;

    public RecyclerViewCharityJobsAdapter(Context context, List<charityJobs> charityJobsData) {
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
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        myViewHolder.tv_jobName.setText(charityJobsData.get(i).getJobName());
        myViewHolder.tv_Description.setText(charityJobsData.get(i).getDisc());
        myViewHolder.tv_briefDescription.setText(charityJobsData.get(i).getJobType());
    }

    @Override
    public int getItemCount() {
        return charityJobsData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_jobName;
        private TextView tv_briefDescription;
        private TextView tv_Description;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_jobName = itemView.findViewById(R.id.jobName);
            tv_briefDescription = itemView.findViewById(R.id.briefDescription);
             tv_Description = itemView.findViewById(R.id.Description);

        }
    }
}
