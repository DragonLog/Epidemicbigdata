package com.example.epidemicbigdata.entity.sinaJson;

public class Otherhistorylist {
    private String certain;  //当天世界累计确诊
    private String uncertain;  //当天世界累计疑似
    private String die;  //当天世界累计死亡
    private String recure;  //当天世界累计治愈
    private String certain_inc;  //当天世界新增确诊
    private String uncertain_inc;  //当天世界新增疑似
    private String die_inc;  //当天世界新增死亡
    private String recure_inc;  //当天世界新增治愈
    private String date;  //当天日期

    public String getCertain() {
        return certain;
    }

    public void setCertain(String certain) {
        this.certain = certain;
    }

    public String getUncertain() {
        return uncertain;
    }

    public void setUncertain(String uncertain) {
        this.uncertain = uncertain;
    }

    public String getDie() {
        return die;
    }

    public void setDie(String die) {
        this.die = die;
    }

    public String getRecure() {
        return recure;
    }

    public void setRecure(String recure) {
        this.recure = recure;
    }

    public String getCertain_inc() {
        return certain_inc;
    }

    public void setCertain_inc(String certain_inc) {
        this.certain_inc = certain_inc;
    }

    public String getUncertain_inc() {
        return uncertain_inc;
    }

    public void setUncertain_inc(String uncertain_inc) {
        this.uncertain_inc = uncertain_inc;
    }

    public String getDie_inc() {
        return die_inc;
    }

    public void setDie_inc(String die_inc) {
        this.die_inc = die_inc;
    }

    public String getRecure_inc() {
        return recure_inc;
    }

    public void setRecure_inc(String recure_inc) {
        this.recure_inc = recure_inc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Otherhistorylist{" +
                "certain='" + certain + '\'' +
                ", uncertain='" + uncertain + '\'' +
                ", die='" + die + '\'' +
                ", recure='" + recure + '\'' +
                ", certain_inc='" + certain_inc + '\'' +
                ", uncertain_inc='" + uncertain_inc + '\'' +
                ", die_inc='" + die_inc + '\'' +
                ", recure_inc='" + recure_inc + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
