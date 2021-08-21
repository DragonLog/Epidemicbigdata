package com.example.epidemicbigdata.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epidemicbigdata.MainActivity;
import com.example.epidemicbigdata.R;
import com.example.epidemicbigdata.entity.soJsonJson.EpidemicNews;
import com.example.epidemicbigdata.ui.third.NewsOnBrowserFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<EpidemicNews> news;
    private Context context;

    public void setNews(List<EpidemicNews> news) {
        this.news = news;
        notifyDataSetChanged();
    }

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NewsViewHolder holder, int position) {
        EpidemicNews epidemicNews = news.get(position);
        holder.newsTime.setText(epidemicNews.getRealPublishTime());
        holder.newsTitle.setText(epidemicNews.getTitle());
        if (position == 0) {
            holder.newsLatest.setVisibility(View.VISIBLE);
        } else {
            holder.newsLatest.setVisibility(View.GONE);
        }
        holder.newsUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("newsUrl", epidemicNews.getUrl());
                FragmentTransaction transaction = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.newsTarget, NewsOnBrowserFragment.class, bundle).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return news == null ? 0 : news.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView newsTime;
        TextView newsTitle;
        TextView newsLatest;
        Button newsUrl;

        public NewsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            newsTime = itemView.findViewById(R.id.newsTime);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsLatest = itemView.findViewById(R.id.newsLatest);
            newsUrl = itemView.findViewById(R.id.newsUrl);
        }
    }
}
