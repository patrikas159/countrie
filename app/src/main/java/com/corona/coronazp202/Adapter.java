package com.corona.coronazp202;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String ENTRY="com.corona.coronazp202.ENTRY";

    private Context context;
    private LayoutInflater inflater;
    List<Countries> data;

    // create constructor to initialize context and data sent from SearchActivity
    public Adapter(Context context, List<Countries> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_countries, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        Countries current = data.get(position);
        myHolder.textName.setText(current.getName());
        myHolder.textCapital.setText("Last update: " + current.getCapital());
        myHolder.textRegion.setText("Confirmed: " + current.getRegion());
        myHolder.textPopulation.setText("Deaths: " + current.getPopulation());
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textName;
        TextView textCapital;
        TextView textRegion;
        TextView textPopulation;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textName = (TextView) itemView.findViewById(R.id.textName);
            textCapital = (TextView) itemView.findViewById(R.id.textCapital);
            textRegion = (TextView) itemView.findViewById(R.id.textRegion);
            textPopulation = (TextView) itemView.findViewById(R.id.textPopulation);
            itemView.setOnClickListener(this);
        }

        // Click event for all items
        @Override
        public void onClick(View v) {
            Intent goToNewEntryActivity = new Intent(context, NewEntryActivity.class);
            int itemPosition = getAdapterPosition();
            Countries countries = data.get(itemPosition);

            goToNewEntryActivity.putExtra(ENTRY, (Parcelable) countries);
            context.startActivity(goToNewEntryActivity);
        }
    }
}
