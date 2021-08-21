package com.example.epidemicbigdata.entity;

public class Province implements Comparable {

    private String provinceName;
    private String provinceXYQZ;
    private String provinceLJQZ;
    private String provinceLJSW;
    private String provinceLJZY;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceXYQZ() {
        return provinceXYQZ;
    }

    public void setProvinceXYQZ(String provinceXYQZ) {
        this.provinceXYQZ = provinceXYQZ;
    }

    public String getProvinceLJQZ() {
        return provinceLJQZ;
    }

    public void setProvinceLJQZ(String provinceLJQZ) {
        this.provinceLJQZ = provinceLJQZ;
    }

    public String getProvinceLJSW() {
        return provinceLJSW;
    }

    public void setProvinceLJSW(String provinceLJSW) {
        this.provinceLJSW = provinceLJSW;
    }

    public String getProvinceLJZY() {
        return provinceLJZY;
    }

    public void setProvinceLJZY(String provinceLJZY) {
        this.provinceLJZY = provinceLJZY;
    }

    @Override
    public String toString() {
        return "ProvinceDetail{" +
                "provinceName='" + provinceName + '\'' +
                ", provinceXYQZ='" + provinceXYQZ + '\'' +
                ", provinceLJQZ='" + provinceLJQZ + '\'' +
                ", provinceLJSW='" + provinceLJSW + '\'' +
                ", provinceLJZY='" + provinceLJZY + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return Integer.parseInt(((Province) o).provinceXYQZ) - Integer.parseInt(this.provinceXYQZ);
    }
}
