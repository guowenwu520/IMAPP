package com.runapp.bean;

public class FenYe {
    private  int status;
    private  int limit;

    public FenYe() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public FenYe(int status, int limit) {
        this.status = status;
        this.limit = limit;
    }
}
