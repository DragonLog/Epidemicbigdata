package com.example.epidemicbigdata.entity.netEaseJson;

public class Children {

    private Total total;
    private String name;
    private String lastUpdateTime;
    private Today today;

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    @Override
    public String toString() {
        return "Children{" +
                "total=" + total +
                ", name='" + name + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", today=" + today +
                '}';
    }
}
