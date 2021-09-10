package com.example.theasia;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.recyclerViewHolder> {

    private LayoutInflater inflater;
    private List<Country> countryList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Country> countryList){
        this.inflater = LayoutInflater.from(context);
        this.countryList = countryList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.recyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.country, parent, false);
        return new recyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerViewHolder holder, int position) {
        Country country = countryList.get(position);
        holder.countryName.setText(country.getName());
        Glide.with(context).load(country.getFlags()).into(holder.flagImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryInfo.class);
                intent.putExtra("name", country.getName());
                intent.putExtra("capital", country.getCapital());
                intent.putExtra("region", country.getRegion());
                intent.putExtra("subregion", country.getSubregion());
                intent.putExtra("population", country.getPopulation());
                intent.putExtra("flag", country.getFlags());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class recyclerViewHolder extends RecyclerView.ViewHolder{

        TextView countryName;
        ImageView flagImage;

        public recyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.countryName);
            flagImage = itemView.findViewById(R.id.flag);
        }
    }
}
