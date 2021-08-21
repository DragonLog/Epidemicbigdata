package com.example.epidemicbigdata.entity.sinaJson;

import java.util.List;

public class Data {
    private String times;
    private Othertotal othertotal;
    private List<Otherhistorylist> otherhistorylist;
    private List<Otherlist> otherlist;

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Othertotal getOthertotal() {
        return othertotal;
    }

    public void setOthertotal(Othertotal othertotal) {
        this.othertotal = othertotal;
    }

    public List<Otherhistorylist> getOtherhistorylist() {
        return otherhistorylist;
    }

    public void setOtherhistorylist(List<Otherhistorylist> otherhistorylist) {
        this.otherhistorylist = otherhistorylist;
    }

    public List<Otherlist> getOtherlist() {
        return otherlist;
    }

    public void setOtherlist(List<Otherlist> otherlist) {
        this.otherlist = otherlist;
    }

    @Override
    public String toString() {
        return "Data{" +
                "times='" + times + '\'' +
                ", othertotal=" + othertotal +
                ", otherhistorylist=" + otherhistorylist +
                ", otherlist=" + otherlist +
                '}';
    }
}
