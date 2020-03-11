package com.example.read_app.entity;

/**
 * Created by 18179 on 2020/2/4.
 */

public class Zan_Data {
    public String ZAN_COLUMN_MY_ID = "id";
    public  String ZAN_COLUMN_DONGTAI_ID = "dtaiid";
    public  String ZAN_COLUMN_NAME_NAME= "name";
    public  boolean ZAN_COUNT_ISDIAN=false;

    public boolean isZAN_COUNT_ISDIAN() {
        return ZAN_COUNT_ISDIAN;
    }

    public void setZAN_COUNT_ISDIAN(boolean ZAN_COUNT_ISDIAN) {
        this.ZAN_COUNT_ISDIAN = ZAN_COUNT_ISDIAN;
    }

    public String getZAN_COLUMN_MY_ID() {
        return ZAN_COLUMN_MY_ID;
    }

    public void setZAN_COLUMN_MY_ID(String ZAN_COLUMN_MY_ID) {
        this.ZAN_COLUMN_MY_ID = ZAN_COLUMN_MY_ID;
    }

    public String getZAN_COLUMN_DONGTAI_ID() {
        return ZAN_COLUMN_DONGTAI_ID;
    }

    public void setZAN_COLUMN_DONGTAI_ID(String ZAN_COLUMN_DONGTAI_ID) {
        this.ZAN_COLUMN_DONGTAI_ID = ZAN_COLUMN_DONGTAI_ID;
    }

    public String getZAN_COLUMN_NAME_NAME() {
        return ZAN_COLUMN_NAME_NAME;
    }

    public void setZAN_COLUMN_NAME_NAME(String ZAN_COLUMN_NAME_NAME) {
        this.ZAN_COLUMN_NAME_NAME = ZAN_COLUMN_NAME_NAME;
    }
}
