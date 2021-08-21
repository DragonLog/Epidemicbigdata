package com.example.epidemicbigdata.ui.third;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.epidemicbigdata.R;
import com.example.epidemicbigdata.adapter.NewsAdapter;
import com.example.epidemicbigdata.entity.soJsonJson.EpidemicNews;
import com.example.epidemicbigdata.util.LoadingUtil;
import com.example.epidemicbigdata.util.RetrofitUtil;
import com.example.epidemicbigdata.util.WebUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

        private RecyclerView newsRecyclerView;
        private SwipeRefreshLayout newsSwipeRefreshLayout;
        private NewsAdapter newsAdapter;
        private LoadingDialog ld;
        private TextView loadError;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadError = view.findViewById(R.id.loadError);

        newsSwipeRefreshLayout = view.findViewById(R.id.newsSwiperefreshlayout);
        newsSwipeRefreshLayout.setColorSchemeResources(R.color.AppBlue);

        newsRecyclerView = view.findViewById(R.id.newsRecyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        newsRecyclerView.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(getContext());
        newsRecyclerView.setAdapter(newsAdapter);

        ld = new LoadingUtil().initLoadingObj(getContext());
        initAndSetNews();

        newsSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsSwipeRefreshLayout.setRefreshing(false);
                if (new WebUtil().isConnected(getContext())) {
                    newsRecyclerView.setVisibility(View.INVISIBLE);
                    loadError.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "网络似乎没有连接...", Toast.LENGTH_SHORT).show();
                    return;
                }
                loadError.setVisibility(View.INVISIBLE);
                newsRecyclerView.setVisibility(View.VISIBLE);
                initAndSetNews();
                Toast.makeText(getContext(), "刷新完毕！", Toast.LENGTH_SHORT).show();
            }
        });

        newsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                //得到当前显示的最后一个item的view
                View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount()-1);
                //得到lastChildView的bottom坐标值
                int lastChildBottom = lastChildView.getBottom();
                //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
                int recyclerBottom =  recyclerView.getBottom()-recyclerView.getPaddingBottom();
                //通过这个lastChildView得到这个view当前的position值
                int lastPosition  = recyclerView.getLayoutManager().getPosition(lastChildView);
                //判断lastChildView的bottom值跟recyclerBottom
                //判断lastPosition是不是最后一个position
                //如果两个条件都满足则说明是真正的滑动到了底部
                if(lastChildBottom == recyclerBottom && lastPosition == recyclerView.getLayoutManager().getItemCount()-1 ){
                    Toast.makeText(getContext(), "没有更多新闻了...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void initAndSetNews() {
        if (new WebUtil().isConnected(getContext())) {
            ld.loadFailed();
            newsRecyclerView.setVisibility(View.INVISIBLE);
            loadError.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), "网络似乎没有连接...", Toast.LENGTH_SHORT).show();
            return;
        }
        newsRecyclerView.setVisibility(View.VISIBLE);
        loadError.setVisibility(View.INVISIBLE);
        Call<ResponseBody> newsResult = new RetrofitUtil().getRetrofitService("https://cdn.mdeer.com/").getSoJsonData();
        newsResult.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String newsString = response.body().string();
                    newsString = newsString.substring(20, newsString.length() - 1);
                    Gson gson = new Gson();
                    List<EpidemicNews> epidemicNews = gson.fromJson(newsString, new TypeToken<List<EpidemicNews>>() {}.getType());
                    newsAdapter.setNews(epidemicNews);
                    ld.loadSuccess();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("**********" + t.getMessage() + "**********");
                Toast.makeText(getContext(), "网络接口出错！", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ld.close();
    }
}
