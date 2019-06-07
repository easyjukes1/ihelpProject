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
import com.example.ihelpproject.volunteers.VolunteerCharityDetailsActivity;
import com.example.ihelpproject.classes.Charity;

import java.util.List;

public class RecyclerViewVcharityAdapter extends RecyclerView.Adapter<RecyclerViewVcharityAdapter.myViewHolder> {

    private Context context;
    private List<Charity> charityData;

    public RecyclerViewVcharityAdapter(Context context, List<Charity> charityData) {
        this.context = context;
        this.charityData = charityData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_vcharity, viewGroup, false);
        return new myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder myViewHolder, final int i) {
        myViewHolder.tv_name.setText(charityData.get(i).getName());
        myViewHolder.tv_address.setText(charityData.get(i).getAddress());
        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, VolunteerCharityDetailsActivity.class);
                intent.putExtra("name", charityData.get(i).getName());
                intent.putExtra("address", charityData.get(i).getAddress());
                intent.putExtra("email", charityData.get(i).getEmail());
                intent.putExtra("phonenumber", charityData.get(i).getPhonenumber());
                intent.putExtra("details", charityData.get(i).getDetails());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return charityData.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_address;
        private LinearLayout parentLayout;


        myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.charityName);
            tv_address = itemView.findViewById(R.id.charityLocation);
            parentLayout = itemView.findViewById(R.id.layoutCharity);
        }
    }
}
