package com.example.epidemicbigdata.ui.first;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.epidemicbigdata.R;
import com.example.epidemicbigdata.entity.MapMessage;
import com.example.epidemicbigdata.entity.netEaseJson.AreaTree;
import com.example.epidemicbigdata.entity.netEaseJson.BaseResponse;
import com.example.epidemicbigdata.entity.netEaseJson.Children;
import com.example.epidemicbigdata.entity.netEaseJson.ChinaTotal;
import com.example.epidemicbigdata.util.LoadingUtil;
import com.example.epidemicbigdata.util.RetrofitUtil;
import com.example.epidemicbigdata.util.WebUtil;
import com.google.gson.Gson;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinaFragment extends Fragment {

    private TextView jwsrNum;
    private TextView wzzgrzNum;
    private TextView xyqzNum;
    private TextView ljqzNum;
    private TextView ljswNum;
    private TextView ljzyNum;

    private TextView jwsr_jzr;
    private TextView wzzgrz_jzr;
    private TextView xyqz_jzr;
    private TextView ljqz_jzr;
    private TextView ljsw_jzr;
    private TextView ljzy_jzr;

    private TextView limitTimeNetEase;

    private WebView chinaMap;

    private SwipeRefreshLayout chinaSwipeRefreshLayout;

    private LoadingDialog ld;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_china, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        jwsrNum = view.findViewById(R.id.jwsr);
        wzzgrzNum = view.findViewById(R.id.wzzgrz);
        xyqzNum = view.findViewById(R.id.xyqz);
        ljqzNum = view.findViewById(R.id.ljqz);
        ljswNum = view.findViewById(R.id.ljsw);
        ljzyNum = view.findViewById(R.id.ljzy);

        jwsr_jzr = view.findViewById(R.id.jwsr_jzr);
        wzzgrz_jzr = view.findViewById(R.id.wzzgrz_jzr);
        xyqz_jzr = view.findViewById(R.id.xyqz_jzr);
        ljqz_jzr = view.findViewById(R.id.ljqz_jzr);
        ljsw_jzr = view.findViewById(R.id.ljsw_jzr);
        ljzy_jzr = view.findViewById(R.id.ljzy_jzr);

        limitTimeNetEase = view.findViewById(R.id.limitTime_china);

        chinaMap = view.findViewById(R.id.chinaMap);
        chinaMap.getSettings().setJavaScriptEnabled(true);
        chinaMap.getSettings().setLoadWithOverviewMode(true);

        chinaSwipeRefreshLayout = view.findViewById(R.id.chinaSwiperefreshlayout);
        chinaSwipeRefreshLayout.setColorSchemeResources(R.color.AppBlue);

        ld = new LoadingUtil().initLoadingObj(getContext());

        if (new WebUtil().isConnected(getContext())) {
            ld.loadFailed();
            Toast.makeText(getContext(), "网络似乎没有连接...", Toast.LENGTH_SHORT).show();
            chinaMap.loadUrl("file:///android_asset/china.html");
        } else {
            initAndSetData();
        }

        chinaSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                chinaSwipeRefreshLayout.setRefreshing(false);
                if (new WebUtil().isConnected(getContext())) {
                    Toast.makeText(getContext(), "网络似乎没有连接...", Toast.LENGTH_SHORT).show();
                    return;
                }
                initAndSetData();
                Toast.makeText(getContext(), "刷新完毕！", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void initAndSetData() {
        Call<BaseResponse> DataResult = new RetrofitUtil().getRetrofitService("https://c.m.163.com/").getNetEaseData();
        DataResult.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                ChinaTotal chinaTotal = response.body().getData().getChinaTotal();

                jwsrNum.setText(chinaTotal.getTotal().getInput() + "");
                wzzgrzNum.setText(chinaTotal.getExtData().getNoSymptom() + "");
                ljqzNum.setText(chinaTotal.getTotal().getConfirm() + "");
                ljswNum.setText(chinaTotal.getTotal().getDead() + "");
                ljzyNum.setText(chinaTotal.getTotal().getHeal() + "");
                xyqzNum.setText((chinaTotal.getTotal().getConfirm() - chinaTotal.getTotal().getHeal() - chinaTotal.getTotal().getDead()) + "");

                if (chinaTotal.getToday().getInput() == null) {
                    jwsr_jzr.setText("较昨日待公布");
                } else {
                    if (chinaTotal.getToday().getInput() >= 0) {
                        jwsr_jzr.setText("较昨日+" + chinaTotal.getToday().getInput());
                    } else {
                        jwsr_jzr.setText("较昨日" + chinaTotal.getToday().getInput());
                    }
                }
                if (chinaTotal.getExtData().getIncrNoSymptom() == null) {
                    wzzgrz_jzr.setText("较昨日待公布");
                } else {
                    if (chinaTotal.getExtData().getIncrNoSymptom() >= 0) {
                        wzzgrz_jzr.setText("较昨日+" + chinaTotal.getExtData().getIncrNoSymptom());
                    } else {
                        wzzgrz_jzr.setText("较昨日" + chinaTotal.getExtData().getIncrNoSymptom());
                    }
                }
                if (chinaTotal.getToday().getStoreConfirm() == null) {
                    xyqz_jzr.setText("较昨日待公布");
                } else {
                    if (chinaTotal.getToday().getStoreConfirm() >= 0) {
                        xyqz_jzr.setText("较昨日+" + chinaTotal.getToday().getStoreConfirm());
                    } else {
                        xyqz_jzr.setText("较昨日" + chinaTotal.getToday().getStoreConfirm());
                    }
                }
                if (chinaTotal.getToday().getConfirm() == null) {
                    ljqz_jzr.setText("较昨日待公布");
                } else {
                    if (chinaTotal.getToday().getConfirm() >= 0) {
                        ljqz_jzr.setText("较昨日+" + chinaTotal.getToday().getConfirm());
                    } else {
                        ljqz_jzr.setText("较昨日" + chinaTotal.getToday().getConfirm());
                    }
                }
                if (chinaTotal.getToday().getDead() == null) {
                    ljsw_jzr.setText("较昨日待公布");
                } else {
                    if (chinaTotal.getToday().getDead() >= 0) {
                        ljsw_jzr.setText("较昨日+" + chinaTotal.getToday().getDead());
                    } else {
                        ljsw_jzr.setText("较昨日" + chinaTotal.getToday().getDead());
                    }
                }
                if (chinaTotal.getToday().getHeal() == null) {
                    ljzy_jzr.setText("较昨日待公布");
                } else{
                    if (chinaTotal.getToday().getHeal() >= 0) {
                        ljzy_jzr.setText("较昨日+" + chinaTotal.getToday().getHeal());
                    } else {
                        ljzy_jzr.setText("较昨日" + chinaTotal.getToday().getHeal());
                    }
                }
                limitTimeNetEase.setText("截止" + response.body().getData().getLastUpdateTime());

                List<MapMessage> provinces = new ArrayList<>();
                List<AreaTree> areaTrees = response.body().getData().getAreaTree();
                for(int i = 0; i < areaTrees.size(); i ++) {
                    if (areaTrees.get(i).getName().equals("中国")) {
                        List<Children> children = areaTrees.get(i).getChildren();
                        for(int j = 0; j < children.size(); j ++) {
                            MapMessage province = new MapMessage();
                            province.setName(children.get(j).getName());
                            province.setValue((children.get(j).getTotal().getConfirm() - children.get(j).getTotal().getHeal() - children.get(j).getTotal().getDead()) + "");
                            provinces.add(province);
                        }
                        break;
                    }
                }
                MapMessage nanhaiislands = new MapMessage();
                nanhaiislands.setName("南海诸岛");
                nanhaiislands.setValue("0");
                provinces.add(nanhaiislands);
                chinaMap.loadUrl("file:///android_asset/china.html");
                setMapData(provinces);
                ld.loadSuccess();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                System.out.println("**********" + t.getMessage() + "**********");
                Toast.makeText(getContext(), "网络接口出错！", Toast.LENGTH_SHORT).show();
                chinaMap.loadUrl("file:///android_asset/china.html");
                ld.loadFailed();
            }
        });
    }

    @JavascriptInterface
    public void setMapData(List<MapMessage> provinces){
        Gson gson = new Gson();
        String result = gson.toJson(provinces);
        chinaMap.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view,String url)
            {
                view.loadUrl("javascript:setMapData(" + result + ")");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ld.close();
    }
}
