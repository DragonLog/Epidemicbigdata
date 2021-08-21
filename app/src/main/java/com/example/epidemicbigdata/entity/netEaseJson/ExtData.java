package com.example.epidemicbigdata.entity.netEaseJson;

public class ExtData {
    private Integer noSymptom;
    private Integer incrNoSymptom;

    public Integer getNoSymptom() {
        return noSymptom;
    }

    public void setNoSymptom(Integer noSymptom) {
        this.noSymptom = noSymptom;
    }

    public Integer getIncrNoSymptom() {
        return incrNoSymptom;
    }

    public void setIncrNoSymptom(Integer incrNoSymptom) {
        this.incrNoSymptom = incrNoSymptom;
    }

    @Override
    public String toString() {
        return "ExtData{" +
                "noSymptom=" + noSymptom +
                ", incrNoSymptom=" + incrNoSymptom +
                '}';
    }
}
