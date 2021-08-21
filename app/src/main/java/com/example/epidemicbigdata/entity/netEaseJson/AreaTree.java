package com.example.epidemicbigdata.entity.netEaseJson;

import java.util.List;

public class AreaTree {

    private Today today;
    private Total total;
    private String name;
    private List<Children> children;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "AreaTree{" +
                "today=" + today +
                ", total=" + total +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
