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

public class RecyclerViewVcharityAdapter extends RecyclerView.Adapter<RecyclerViewVcharityAdapter.myViewHolder> {

    private Context context;
    private List<Charity> charityData;

    RecyclerViewVcharityAdapter(Context context, List<Charity> charityData) {
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
        // myViewHolder.iv_imgCharity.setImageResource(charityData.get(i).getPicture());

        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, volunteerCharityDetailsActivity.class);
                intent.putExtra("name", charityData.get(i).getName());
                intent.putExtra("address", charityData.get(i).getAddress());
                intent.putExtra("email", charityData.get(i).getEmail());
                intent.putExtra("phonenumber", charityData.get(i).getPhonenumber());
                //    intent.putExtra("details",charityData.get(i).getDetails());


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
        // private ImageView iv_imgCharity;
        private LinearLayout parentLayout;


        myViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.charityName);
            tv_address = itemView.findViewById(R.id.charityLocation);
            //  iv_imgCharity = itemView.findViewById(R.id.img_charity);
            parentLayout = itemView.findViewById(R.id.layoutCharity);
        }
    }
}
