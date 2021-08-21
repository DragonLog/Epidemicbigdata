package com.example.epidemicbigdata.entity.netEaseJson;

public class Total {
    private Integer confirm;
    private Integer suspect;
    private Integer heal;
    private Integer dead;
    private Integer input;
    private Integer storeConfirm;

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getSuspect() {
        return suspect;
    }

    public void setSuspect(Integer suspect) {
        this.suspect = suspect;
    }

    public Integer getHeal() {
        return heal;
    }

    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    public Integer getDead() {
        return dead;
    }

    public void setDead(Integer dead) {
        this.dead = dead;
    }

    public Integer getInput() {
        return input;
    }

    public void setInput(Integer input) {
        this.input = input;
    }

    public Integer getStoreConfirm() {
        return storeConfirm;
    }

    public void setStoreConfirm(Integer storeConfirm) {
        this.storeConfirm = storeConfirm;
    }

    @Override
    public String toString() {
        return "Total{" +
                "confirm=" + confirm +
                ", suspect=" + suspect +
                ", heal=" + heal +
                ", dead=" + dead +
                ", input=" + input +
                ", storeConfirm=" + storeConfirm +
                '}';
    }
}
