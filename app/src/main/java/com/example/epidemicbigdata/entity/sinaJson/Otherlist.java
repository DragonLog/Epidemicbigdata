package com.example.epidemicbigdata.entity.sinaJson;

public class Otherlist {

    private String conNum;
    private String susNum;
    private String cureNum;
    private String deathNum;
    private String conadd;
    private String susadd;
    private String cureadd;
    private String deathadd;
    private String econNum;
    private String name;

    public String getConNum() {
        return conNum;
    }

    public void setConNum(String conNum) {
        this.conNum = conNum;
    }

    public String getSusNum() {
        return susNum;
    }

    public void setSusNum(String susNum) {
        this.susNum = susNum;
    }

    public String getCureNum() {
        return cureNum;
    }

    public void setCureNum(String cureNum) {
        this.cureNum = cureNum;
    }

    public String getDeathNum() {
        return deathNum;
    }

    public void setDeathNum(String deathNum) {
        this.deathNum = deathNum;
    }

    public String getConadd() {
        return conadd;
    }

    public void setConadd(String conadd) {
        this.conadd = conadd;
    }

    public String getSusadd() {
        return susadd;
    }

    public void setSusadd(String susadd) {
        this.susadd = susadd;
    }

    public String getCureadd() {
        return cureadd;
    }

    public void setCureadd(String cureadd) {
        this.cureadd = cureadd;
    }

    public String getDeathadd() {
        return deathadd;
    }

    public void setDeathadd(String deathadd) {
        this.deathadd = deathadd;
    }

    public String getEconNum() {
        return econNum;
    }

    public void setEconNum(String econNum) {
        this.econNum = econNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Otherlist{" +
                "conNum='" + conNum + '\'' +
                ", susNum='" + susNum + '\'' +
                ", cureNum='" + cureNum + '\'' +
                ", deathNum='" + deathNum + '\'' +
                ", conadd='" + conadd + '\'' +
                ", susadd='" + susadd + '\'' +
                ", cureadd='" + cureadd + '\'' +
                ", deathadd='" + deathadd + '\'' +
                ", econNum='" + econNum + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
