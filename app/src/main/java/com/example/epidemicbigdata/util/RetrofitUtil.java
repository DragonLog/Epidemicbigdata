package com.example.epidemicbigdata.util;

import com.example.epidemicbigdata.service.api.DataSourceApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    public DataSourceApi getRetrofitService(String baseUrl) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder() ;
        builder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .removeHeader("User-Agent")
                        .addHeader("User-Agent", "zcx666")
                        .build() ;
                return chain.proceed(newRequest);
            }
        }) ;
        OkHttpClient client = builder.build();  //解决服务器403问题

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        DataSourceApi dataSourceApi = retrofit.create(DataSourceApi.class);
        return dataSourceApi;
    }

}
