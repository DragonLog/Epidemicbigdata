package com.example.epidemicbigdata.entity.netEaseJson;

public class ChinaTotal {
    private Today today;
    private Total total;
    private ExtData extData;

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

    public ExtData getExtData() {
        return extData;
    }

    public void setExtData(ExtData extData) {
        this.extData = extData;
    }

    @Override
    public String toString() {
        return "ChinaTotal{" +
                "today=" + today +
                ", total=" + total +
                ", extData=" + extData +
                '}';
    }
}
