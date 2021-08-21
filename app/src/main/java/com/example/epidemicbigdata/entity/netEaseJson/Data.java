package com.example.epidemicbigdata.entity.netEaseJson;

import java.util.List;

public class Data {
    private ChinaTotal chinaTotal;
    private List<ChinaDayList> chinaDayList;
    private String lastUpdateTime;
    private List<AreaTree> areaTree;

    public ChinaTotal getChinaTotal() {
        return chinaTotal;
    }

    public void setChinaTotal(ChinaTotal chinaTotal) {
        this.chinaTotal = chinaTotal;
    }

    public List<ChinaDayList> getChinaDayList() {
        return chinaDayList;
    }

    public void setChinaDayList(List<ChinaDayList> chinaDayList) {
        this.chinaDayList = chinaDayList;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<AreaTree> getAreaTree() {
        return areaTree;
    }

    public void setAreaTree(List<AreaTree> areaTree) {
        this.areaTree = areaTree;
    }

    @Override
    public String toString() {
        return "Data{" +
                "chinaTotal=" + chinaTotal +
                ", chinaDayList=" + chinaDayList +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", areaTree=" + areaTree +
                '}';
    }
}
