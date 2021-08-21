package com.example.epidemicbigdata.ui.second;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.epidemicbigdata.R;
import com.example.epidemicbigdata.adapter.WorldAdapter;
import com.example.epidemicbigdata.entity.Country;
import com.example.epidemicbigdata.entity.sinaJson.BaseResponse;
import com.example.epidemicbigdata.entity.sinaJson.Otherhistorylist;
import com.example.epidemicbigdata.entity.sinaJson.Otherlist;
import com.example.epidemicbigdata.util.LoadingUtil;
import com.example.epidemicbigdata.util.RetrofitUtil;
import com.example.epidemicbigdata.util.WebUtil;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorldFragment extends Fragment {

    private TextView worldLjqz;
    private TextView worldXyqz;
    private TextView worldLjsw;
    private TextView worldLjzy;

    private TextView worldLjqz_jzr;
    private TextView worldXyqz_jzr;
    private TextView worldLjsw_jzr;
    private TextView worldLjzy_jzr;

    private TextView limitTimeWorld;

    private WebView worldChart;

    private RecyclerView worldTable;
    private WorldAdapter worldAdapter;

    private LoadingDialog ld;

    private SwipeRefreshLayout worldSwipeRefreshLayout;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_world, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        worldLjqz = view.findViewById(R.id.world_ljqz);
        worldXyqz = view.findViewById(R.id.world_xyqz);
        worldLjsw = view.findViewById(R.id.world_ljsw);
        worldLjzy = view.findViewById(R.id.world_ljzy);

        worldLjqz_jzr = view.findViewById(R.id.world_ljqz_jzr);
        worldXyqz_jzr = view.findViewById(R.id.world_xyqz_jzr);
        worldLjsw_jzr = view.findViewById(R.id.world_ljsw_jzr);
        worldLjzy_jzr = view.findViewById(R.id.world_ljzy_jzy);

        limitTimeWorld = view.findViewById(R.id.limitTime_world);

        worldChart = view.findViewById(R.id.worldchart);
        worldChart.getSettings().setJavaScriptEnabled(true);
        worldChart.getSettings().setLoadWithOverviewMode(true);

        worldTable = view.findViewById(R.id.worldtable);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        worldTable.setLayoutManager(layoutManager);
        worldAdapter = new WorldAdapter(getContext());
        worldTable.setAdapter(worldAdapter);

        worldSwipeRefreshLayout = view.findViewById(R.id.worldSwiperefreshlayout);
        worldSwipeRefreshLayout.setColorSchemeResources(R.color.AppBlue);

        ld = new LoadingUtil().initLoadingObj(getContext());
        if (new WebUtil().isConnected(getContext())) {
            Toast.makeText(getContext(), "网络似乎没有连接...", Toast.LENGTH_SHORT).show();
            worldChart.loadUrl("file:///android_asset/chinese.html");
            ld.loadFailed();
        } else {
            initAndSetBaseAndChartAndTableData();
        }

        worldSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                worldSwipeRefreshLayout.setRefreshing(false);
                if (new WebUtil().isConnected(getContext())) {
                    Toast.makeText(getContext(), "网络似乎没有连接...", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    initAndSetBaseAndChartAndTableData();
                    Toast.makeText(getContext(), "刷新完毕！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initAndSetBaseAndChartAndTableData() {
        Call<BaseResponse> baseDataResult = new RetrofitUtil().getRetrofitService("https://interface.sina.cn/").getSinaData();
        baseDataResult.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                worldChart.loadUrl("file:///android_asset/world.html");

               List<String> date = new ArrayList<>();
               List<String> xzqz = new ArrayList<>();
               List<String> xzzy = new ArrayList<>();
               List<Otherhistorylist> otherhistorylist = response.body().getData().getOtherhistorylist();
               for (int i = 0; i < 15; i ++) {
                   date.add(otherhistorylist.get(i).getDate().substring(5, 10));
                   xzqz.add(otherhistorylist.get(i).getCertain_inc());
                   xzzy.add(otherhistorylist.get(i).getRecure_inc());
               }

                setChartData(date.toString().substring(1, date.toString().length() - 1).replaceAll(" ", "") + "," + xzqz.toString().substring(1, xzqz.toString().length() - 1).replaceAll(" ", "") + "," + xzzy.toString().substring(1, xzzy.toString().length() - 1).replaceAll(" ", ""));

                worldLjqz.setText(response.body().getData().getOthertotal().getCertain());
                worldXyqz.setText(response.body().getData().getOthertotal().getEcertain());
                worldLjsw.setText(response.body().getData().getOthertotal().getDie());
                worldLjzy.setText(response.body().getData().getOthertotal().getRecure());

                worldLjqz_jzr.setText("较昨日" + response.body().getData().getOthertotal().getCertain_inc());
                worldXyqz_jzr.setText("较昨日" + response.body().getData().getOthertotal().getEcertain_inc());
                worldLjsw_jzr.setText("较昨日" + response.body().getData().getOthertotal().getDie_inc());
                worldLjzy_jzr.setText("较昨日" + response.body().getData().getOthertotal().getRecure_inc());

                limitTimeWorld.setText(response.body().getData().getTimes());

                List<Country> countries = new ArrayList<>();
                List<Otherlist> otherlist = response.body().getData().getOtherlist();
                for (int i = 0; i < otherlist.size(); i ++) {
                    Country country = new Country();
                    country.setName(otherlist.get(i).getName());
                    country.setXzqz(otherlist.get(i).getConadd());
                    country.setLjqz(otherlist.get(i).getConNum());
                    country.setLjsw(otherlist.get(i).getDeathNum());
                    country.setLjzy(otherlist.get(i).getCureNum());
                    countries.add(country);
                }
                Collections.sort(countries);
                worldAdapter.setCountries(countries);
                ld.loadSuccess();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                System.out.println("**********" + t.getMessage() + "**********");
                Toast.makeText(getContext(), "网络接口出错！", Toast.LENGTH_SHORT).show();
                worldChart.loadUrl("file:///android_asset/chinese.html");
                ld.loadFailed();
            }
        });
    }

    @JavascriptInterface
    public void setChartData(String string) {
        worldChart.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:setString(\'" + string + "\')");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
