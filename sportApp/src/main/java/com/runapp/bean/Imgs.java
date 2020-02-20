package com.runapp.bean;

public class Imgs {
    String imgid;
    String imgname;
    String sprotsdetail_id;

    public Imgs(String imgid, String imgname, String sprotsdetail_id) {
        this.imgid = imgid;
        this.imgname = imgname;
        this.sprotsdetail_id = sprotsdetail_id;
    }

   

    public String getImgid() {
		return imgid;
	}



	public void setImgid(String imgid) {
		this.imgid = imgid;
	}



	public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    public String getSprotsdetail_id() {
        return sprotsdetail_id;
    }

    public void setSprotsdetail_id(String sprotsdetail_id) {
        this.sprotsdetail_id = sprotsdetail_id;
    }
}
