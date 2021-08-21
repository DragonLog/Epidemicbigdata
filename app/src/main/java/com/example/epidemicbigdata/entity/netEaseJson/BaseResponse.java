package com.example.epidemicbigdata.entity.netEaseJson;

public class BaseResponse {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                '}';
    }
}
