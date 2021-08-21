package com.example.epidemicbigdata.entity.soJsonJson;

public class EpidemicNews {
    private String realPublishTime;
    private String title;
    private String url;

    public String getRealPublishTime() {
        return realPublishTime;
    }

    public void setRealPublishTime(String realPublishTime) {
        this.realPublishTime = realPublishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "EpidemicNews{" +
                "realPublishTime='" + realPublishTime + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
