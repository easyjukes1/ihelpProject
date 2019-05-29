package com.example.ihelpproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewVJobsAdapter extends RecyclerView.Adapter<RecyclerViewVJobsAdapter.myViewHolder> {

    private Context context;
    private List<CharityAddJob> CharityJobsData;

    RecyclerViewVJobsAdapter(Context context, List<CharityAddJob> CharityJobsData) {
        this.context = context;
        this.CharityJobsData = CharityJobsData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_vcharityjob, viewGroup, false);
        return new myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, final int i) {
        myViewHolder.tv_jobTitle.setText(CharityJobsData.get(i).getJobTitle());
        myViewHolder.tv_jobType.setText(CharityJobsData.get(i).getJobType());
        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, VolunteerSubmitJobsActivity.class);
                intent.putExtra("jobTitle", CharityJobsData.get(i).getJobTitle());
                intent.putExtra("jobType", CharityJobsData.get(i).getJobType());
                intent.putExtra("description", CharityJobsData.get(i).getDescription());
                intent.putExtra("phoneNumber", CharityJobsData.get(i).getPhoneNumber());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return CharityJobsData.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        //  private ImageView iv_jobImage;
        private TextView tv_jobTitle;
        private TextView tv_jobType;

        private LinearLayout parentLayout;


        myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_jobTitle = itemView.findViewById(R.id.charityName);

            tv_jobType = itemView.findViewById(R.id.tv_jobType);
            // iv_jobImage = itemView.findViewById(R.id.imgCharityJobs);
            parentLayout = itemView.findViewById(R.id.layoutJob);
        }
    }
}
