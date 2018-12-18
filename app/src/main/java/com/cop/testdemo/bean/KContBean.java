package com.cop.testdemo.bean;

import com.cop.testdemo.net.BaseEntery;

/**
 * Create by wangchao on 2018/12/12 19:52
 */
public class KContBean extends BaseEntery {
    private boolean ko;

    private String startTime;

    private String endTime;

    private int kTime;

    private int dTime;

    public int getdTime() {
        return dTime;
    }

    public void setdTime(int dTime) {
        this.dTime = dTime;
    }

    public int getkTime() {
        return kTime;
    }

    public void setkTime(int kTime) {
        this.kTime = kTime;
    }

    public boolean isKo() {
        return ko;
    }

    public void setKo(boolean ko) {
        this.ko = ko;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "KContBean{" +
                "isKo=" + ko +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
