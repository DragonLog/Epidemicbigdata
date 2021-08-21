package com.example.epidemicbigdata.ui.first;

import android.Manifest;
import android.content.pm.PackageManager;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.epidemicbigdata.R;
import com.example.epidemicbigdata.adapter.ProvinceAdapter;
import com.example.epidemicbigdata.entity.Province;
import com.example.epidemicbigdata.entity.netEaseJson.AreaTree;
import com.example.epidemicbigdata.entity.netEaseJson.BaseResponse;
import com.example.epidemicbigdata.entity.netEaseJson.Children;
import com.example.epidemicbigdata.entity.netEaseJson.ChinaDayList;
import com.example.epidemicbigdata.util.RetrofitUtil;
import com.example.epidemicbigdata.util.WebUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChineseFragment extends Fragment {

    private LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    private RecyclerView provinceTable;

    private TextView province_ljqz;
    private TextView province_ljsw;
    private TextView province_ljzy;
    private TextView province_ljqz_jzr;
    private TextView province_ljsw_jzr;
    private TextView province_ljzy_jzr;

    private TextView locationNow;

    private TextView provinceName;

    private WebView chineseChart;

    private ProvinceAdapter provinceAdapter;

    private TextView limitTimeProvince;

    private SwipeRefreshLayout chineseSwipeRefreshLayout;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chinese, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        province_ljqz = view.findViewById(R.id.province_ljqz);
        province_ljsw = view.findViewById(R.id.province_ljsw);
        province_ljzy = view.findViewById(R.id.province_ljzy);
        province_ljqz_jzr = view.findViewById(R.id.province_ljqz_jzr);
        province_ljsw_jzr = view.findViewById(R.id.province_ljsw_jzr);
        province_ljzy_jzr = view.findViewById(R.id.province_ljzy_jzr);
        provinceName = view.findViewById(R.id.province_name);

        chineseChart = view.findViewById(R.id.chinesechart);
        chineseChart.getSettings().setJavaScriptEnabled(true);
        chineseChart.getSettings().setLoadWithOverviewMode(true);

        locationNow = view.findViewById(R.id.location_now);

        limitTimeProvince = view.findViewById(R.id.limitTime_province);

        chineseSwipeRefreshLayout = view.findViewById(R.id.chineseSwiperefreshlayout);
        chineseSwipeRefreshLayout.setColorSchemeResources(R.color.AppBlue);

        provinceTable = view.findViewById(R.id.provincetable);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        provinceTable.setLayoutManager(layoutManager);
        provinceAdapter = new ProvinceAdapter(getContext());
        provinceTable.setAdapter(provinceAdapter);


        mLocationClient = new LocationClient(getActivity());//声明LocationClient类
        mLocationClient.registerLocationListener(myListener); //注册监听函数

        LocationClientOption option = new LocationClientOption();  //地图配置选项
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);  //节能
        option.setIsNeedAddress(true);  //需要详细地址信息
        option.setScanSpan(0);  //只获取一次信息
        mLocationClient.setLocOption(option); //加载配置
        if (new WebUtil().isConnected(getContext())) {
            Toast.makeText(getContext(), "网络似乎没有连接...", Toast.LENGTH_SHORT).show();
            chineseChart.loadUrl("file:///android_asset/chinese.html");
        } else {
            runPermission();
        }

        chineseSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                chineseSwipeRefreshLayout.setRefreshing(false);
                if (new WebUtil().isConnected(getContext())) {
                    Toast.makeText(getContext(), "网络似乎没有连接...", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    runPermission();
                    Toast.makeText(getContext(), "刷新完毕！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class MyLocationListener extends BDAbstractLocationListener {  //获取省份名称和详细地址
        @Override
        public void onReceiveLocation(BDLocation location) {
            locationNow.setText(location.getAddrStr());
            provinceName.setText(location.getProvince());
            initAndSetData(location.getProvince().substring(0,location.getProvince().length()-1));
        }
    }


    private void runPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //申请ACCESS_FINE_LOCATION权限
            //ActivityCompat.requestPermissions(getActivity(),
            //        new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            //以下是直接使用Fragment的requestPermissions方法
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }else {
            mLocationClient.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                mLocationClient.start();//此处改为你的逻辑函数
            } else {
                // Permission Denied
                Toast.makeText(getActivity(), "权限无效！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void initAndSetData(String province) {
        Call<BaseResponse> DataResult = new RetrofitUtil().getRetrofitService("https://c.m.163.com/").getNetEaseData();
        DataResult.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                List<AreaTree> areaTrees = response.body().getData().getAreaTree();
                List<Province> provinceDetails = new ArrayList<>();
                for (int i = 0; i < areaTrees.size(); i ++) {
                    if (areaTrees.get(i).getName().equals("中国")) {
                        List<Children> children = areaTrees.get(i).getChildren();
                        for (int j = 0; j < children.size(); j ++) {
                            if (children.get(j).getName().equals(province)) {
                                limitTimeProvince.setText("截止" + children.get(j).getLastUpdateTime());
                                province_ljqz.setText(children.get(j).getTotal().getConfirm() + "");
                                province_ljsw.setText(children.get(j).getTotal().getDead() + "");
                                province_ljzy.setText(children.get(j).getTotal().getHeal() + "");
                                if (children.get(j).getToday().getConfirm() == null) {
                                    province_ljqz_jzr.setText("较昨日待公布");
                                } else {
                                    if (children.get(j).getToday().getConfirm() >= 0) {
                                        province_ljqz_jzr.setText("较昨日+" + children.get(j).getToday().getConfirm());
                                    } else {
                                        province_ljqz_jzr.setText("较昨日" + children.get(j).getToday().getConfirm());
                                    }
                                }
                                if (children.get(j).getToday().getDead() == null) {
                                    province_ljsw_jzr.setText("较昨日待公布");
                                } else {
                                    if (children.get(j).getToday().getDead() >= 0) {
                                        province_ljsw_jzr.setText("较昨日+" + children.get(j).getToday().getDead());
                                    } else {
                                        province_ljsw_jzr.setText("较昨日" + children.get(j).getToday().getDead());
                                    }
                                }
                                if (children.get(j).getToday().getHeal() == null) {
                                    province_ljzy_jzr.setText("较昨日待公布");
                                } else {
                                    if (children.get(j).getToday().getHeal() >= 0) {
                                        province_ljzy_jzr.setText("较昨日+" + children.get(j).getToday().getHeal());
                                    } else {
                                        province_ljzy_jzr.setText("较昨日" + children.get(j).getToday().getHeal());
                                    }
                                }
                                mLocationClient.stop();
                                break;
                            }
                        }
                        List<Children> children2 = areaTrees.get(i).getChildren();
                        for (int k = 0; k < children2.size(); k ++) {
                            Province provinceDetail = new Province();
                            provinceDetail.setProvinceName(children2.get(k).getName());
                            provinceDetail.setProvinceXYQZ((children2.get(k).getTotal().getConfirm() - children2.get(k).getTotal().getDead() - children2.get(k).getTotal().getHeal()) + "");
                            provinceDetail.setProvinceLJQZ(children2.get(k).getTotal().getConfirm() + "");
                            provinceDetail.setProvinceLJSW(children2.get(k).getTotal().getDead() + "");
                            provinceDetail.setProvinceLJZY(children2.get(k).getTotal().getHeal() + "");
                            provinceDetails.add(provinceDetail);
                        }
                        break;
                    }
                }
                List<String> date = new ArrayList<>();
                List<String> xzqz = new ArrayList<>();
                List<String> xzjwsr = new ArrayList<>();
                List<ChinaDayList> chinaDayLists = response.body().getData().getChinaDayList();
                for (int m = (chinaDayLists.size() - 1); m >= (chinaDayLists.size() - 15); m --) {
                    date.add(chinaDayLists.get(m).getDate().substring(5, 10));
                    xzqz.add(chinaDayLists.get(m).getToday().getConfirm().toString());
                    xzjwsr.add(chinaDayLists.get(m).getToday().getInput().toString());
                }
                setChartData(date.toString().substring(1, date.toString().length() - 1).replaceAll(" ", "") + "," + xzqz.toString().substring(1, xzqz.toString().length() - 1).replaceAll(" ", "") + "," + xzjwsr.toString().substring(1, xzjwsr.toString().length() - 1).replaceAll(" ", ""));
                chineseChart.loadUrl("file:///android_asset/chinese.html");

                Collections.sort(provinceDetails);
                provinceAdapter.setProvinceDetails(provinceDetails);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                System.out.println("**********" + t.getMessage() + "**********");
                Toast.makeText(getContext(), "网络接口出错！", Toast.LENGTH_SHORT).show();
                chineseChart.loadUrl("file:///android_asset/chinese.html");
            }
        });
    }

    @JavascriptInterface
    public void setChartData(String string){
        chineseChart.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:setString(\'" + string + "\')");
            }
        });
    }
}
