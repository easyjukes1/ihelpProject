package com.example.ihelpproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, charityVolunteerDetailsActivity.class);
                intent.putExtra("name", charityVolunteersData.get(i).getName());
                intent.putExtra("email", charityVolunteersData.get(i).getEmail());
                intent.putExtra("Username", charityVolunteersData.get(i).getAddress());
                intent.putExtra("address", charityVolunteersData.get(i).getAddress());
                intent.putExtra("phonenumber", charityVolunteersData.get(i).getPhonenumber());
                intent.putExtra("role", charityVolunteersData.get(i).getRole());
                intent.putExtra("Age", charityVolunteersData.get(i).getAge());
          //      if (charityVolunteersData.get(i).getRole().equals("studentUser")) {
            //        intent.putExtra("supervisorName", charityVolunteersData.get(i).get);
              //      intent.putExtra("Age", charityVolunteersData.get(i).getAge());
                //    intent.putExtra("Age", charityVolunteersData.get(i).getAge());
                //}
                //if (charityVolunteersData.get(i).getRole().equals("employeeUser")) {
                  //  intent.putExtra("Age", charityVolunteersData.get(i).getAge());
                    //intent.putExtra("Age", charityVolunteersData.get(i).getAge());
                //}
                context.startActivity(intent);
            }
        });

        myViewHolder.deleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = v.getContext();
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setMessage("are you sure you want to delete this job?");
                alert.setCancelable(true);

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(charityVolunteersData.get(i).getRole())
                                .child(charityVolunteersData.get(i).getId());
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
        return charityVolunteersData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        //  private ImageView img_charityVolunteer;
        private TextView tv_volunteerName;
        private LinearLayout parentLayout;
        private LinearLayout deleteLayout;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            //    img_charityVolunteer = itemView.findViewById(R.id.img_charityVolunteer);
            tv_volunteerName = itemView.findViewById(R.id.volunteerName);
            parentLayout = itemView.findViewById(R.id.layoutVolunteer);
            deleteLayout = itemView.findViewById(R.id.img_delete);

        }
    }
}
