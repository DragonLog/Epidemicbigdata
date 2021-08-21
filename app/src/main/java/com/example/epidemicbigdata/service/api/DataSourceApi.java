package com.example.epidemicbigdata.service.api;

import com.example.epidemicbigdata.entity.sinaJson.BaseResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataSourceApi {
    @GET("/news/wap/fymap2020_data.d.json")
    public Call<BaseResponse> getSinaData();

    @GET("/ug/api/wuhan/app/data/list-total")
    public Call<com.example.epidemicbigdata.entity.netEaseJson.BaseResponse> getNetEaseData();

    @GET("/contentdtos.js")
    public Call<ResponseBody> getSoJsonData();
}
