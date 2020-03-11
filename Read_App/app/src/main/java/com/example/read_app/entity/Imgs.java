package com.example.read_app.entity;

/**
 * Created by 18179 on 2020/2/2.
 */

public class Imgs {
    public String IMGS_COLUMN_NAME_OUTID = "outid";
    public String IMGS_COLUMN_NAME_IMGURL = "imgurl";
    public String BOOKS_ID="booksid";

    public Imgs() {
    }

    public String getBOOKS_ID() {
        return BOOKS_ID;
    }

    public void setBOOKS_ID(String BOOKS_ID) {
        this.BOOKS_ID = BOOKS_ID;
    }

    public String getIMGS_COLUMN_NAME_OUTID() {
        return IMGS_COLUMN_NAME_OUTID;
    }

    public void setIMGS_COLUMN_NAME_OUTID(String IMGS_COLUMN_NAME_OUTID) {
        this.IMGS_COLUMN_NAME_OUTID = IMGS_COLUMN_NAME_OUTID;
    }

    public String getIMGS_COLUMN_NAME_IMGURL() {
        return IMGS_COLUMN_NAME_IMGURL;
    }

    public void setIMGS_COLUMN_NAME_IMGURL(String IMGS_COLUMN_NAME_IMGURL) {
        this.IMGS_COLUMN_NAME_IMGURL = IMGS_COLUMN_NAME_IMGURL;
    }
}
