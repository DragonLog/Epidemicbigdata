package com.example.epidemicbigdata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epidemicbigdata.R;
import com.example.epidemicbigdata.entity.Province;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHoler> {

    private List<Province> provinceDetails;
    private Context context;

    public void setProvinceDetails(List<Province> provinceDetails) {
        this.provinceDetails = provinceDetails;
        notifyDataSetChanged();
    }

    public ProvinceAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ProvinceViewHoler onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ProvinceAdapter.ProvinceViewHoler(LayoutInflater.from(context).inflate(R.layout.item_province, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProvinceViewHoler holder, int position) {
        Province provinceDetail = provinceDetails.get(position);
        holder.provinceName.setText(provinceDetail.getProvinceName());
        holder.provinceXyqz.setText(provinceDetail.getProvinceXYQZ());
        holder.provinceLjqz.setText(provinceDetail.getProvinceLJQZ());
        holder.provinceLjsw.setText(provinceDetail.getProvinceLJSW());
        holder.provinceLjzy.setText(provinceDetail.getProvinceLJZY());
    }

    @Override
    public int getItemCount() {
        return provinceDetails == null ? 0 : provinceDetails.size();
    }

    class ProvinceViewHoler extends RecyclerView.ViewHolder {

        private TextView provinceName;
        private TextView provinceXyqz;
        private TextView provinceLjqz;
        private TextView provinceLjsw;
        private TextView provinceLjzy;

        public ProvinceViewHoler(@NonNull @NotNull View itemView) {
            super(itemView);
            provinceName = itemView.findViewById(R.id.name_province);
            provinceXyqz = itemView.findViewById(R.id.xyqz_province);
            provinceLjqz = itemView.findViewById(R.id.ljqz_province);
            provinceLjsw = itemView.findViewById(R.id.ljsw_province);
            provinceLjzy = itemView.findViewById(R.id.ljzy_province);
        }
    }
}
