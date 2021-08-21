package com.example.epidemicbigdata.entity;

public class Country implements Comparable {

    private String name;
    private String xzqz;
    private String ljqz;
    private String ljsw;
    private String ljzy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXzqz() {
        return xzqz;
    }

    public void setXzqz(String xzqz) {
        this.xzqz = xzqz;
    }

    public String getLjqz() {
        return ljqz;
    }

    public void setLjqz(String ljqz) {
        this.ljqz = ljqz;
    }

    public String getLjsw() {
        return ljsw;
    }

    public void setLjsw(String ljsw) {
        this.ljsw = ljsw;
    }

    public String getLjzy() {
        return ljzy;
    }

    public void setLjzy(String ljzy) {
        this.ljzy = ljzy;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", xzqz='" + xzqz + '\'' +
                ", ljqz='" + ljqz + '\'' +
                ", ljsw='" + ljsw + '\'' +
                ", ljzy='" + ljzy + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return Integer.parseInt(((Country) o).xzqz) - Integer.parseInt(this.xzqz);
    }
}
