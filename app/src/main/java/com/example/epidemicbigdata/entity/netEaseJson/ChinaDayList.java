package com.example.epidemicbigdata.entity.netEaseJson;

public class ChinaDayList {
    private String date;
    private Today today;
    private Total total;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ChinaDayList{" +
                "date='" + date + '\'' +
                ", today=" + today +
                ", total=" + total +
                '}';
    }
}
