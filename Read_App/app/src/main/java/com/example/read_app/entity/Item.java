package com.example.read_app.entity;

import java.io.Serializable;

/**
 * Created by 18179 on 2020/3/7.
 */

public class Item implements Serializable {
    String name;
    String url;
  int  isselect=0;

    public int getIsselect() {
        return isselect;
    }

    public void setIsselect(int isselect) {
        this.isselect = isselect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
