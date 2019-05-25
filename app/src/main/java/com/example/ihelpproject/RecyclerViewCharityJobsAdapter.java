package com.example.ihelpproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerViewCharityJobsAdapter extends RecyclerView.Adapter<RecyclerViewCharityJobsAdapter.myViewHolder> {

    private Context context;
    private List<CharityJobs> charityJobsData;

    public RecyclerViewCharityJobsAdapter(Context context, List<CharityJobs> charityJobsData) {
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
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, final int i) {
        myViewHolder.tv_jobName.setText(charityJobsData.get(i).getJobName());
        myViewHolder.tv_briefDescription.setText(charityJobsData.get(i).getJobType());

        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setMessage("are you sure you want to delete this job?");
                alert.setCancelable(true);

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("CharityAddjob").child(charityJobsData.get(i).getId());
                        databaseReference.removeValue();
                        dialog.cancel();
                    }
                });
                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return charityJobsData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_jobName;
        private TextView tv_briefDescription;

        private LinearLayout parentLayout;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_jobName = itemView.findViewById(R.id.jobName);
            tv_briefDescription = itemView.findViewById(R.id.briefDescription);

            parentLayout = itemView.findViewById(R.id.img_delete);

        }
    }
}
