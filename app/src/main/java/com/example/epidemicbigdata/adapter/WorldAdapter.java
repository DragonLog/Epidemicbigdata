package com.example.epidemicbigdata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epidemicbigdata.R;
import com.example.epidemicbigdata.entity.Country;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WorldAdapter extends RecyclerView.Adapter<WorldAdapter.WorldViewHolder> {

    private List<Country> countries;
    private Context context;

    public void setCountries(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    public WorldAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public WorldViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new WorldAdapter.WorldViewHolder(LayoutInflater.from(context).inflate(R.layout.item_country, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WorldViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.countryName.setText(country.getName());
        holder.countryXzqz.setText(country.getXzqz());
        holder.countryLjqz.setText(country.getLjqz());
        holder.countryLjsw.setText(country.getLjsw());
        holder.countryLjzy.setText(country.getLjzy());
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }

    class WorldViewHolder extends RecyclerView.ViewHolder {

        private TextView countryName;
        private TextView countryXzqz;
        private TextView countryLjqz;
        private TextView countryLjsw;
        private TextView countryLjzy;

        public WorldViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.country_name);
            countryXzqz = itemView.findViewById(R.id.country_xzqz);
            countryLjqz = itemView.findViewById(R.id.country_ljqz);
            countryLjsw = itemView.findViewById(R.id.country_ljsw);
            countryLjzy = itemView.findViewById(R.id.country_ljzy);
        }
    }

}
