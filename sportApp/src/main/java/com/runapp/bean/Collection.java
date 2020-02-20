package com.runapp.bean;

/**
 * Created by 18179 on 2020/1/27.
 */

public class Collection {
    private  String Collectionid;
    private  String userid;
    private String sprrtsid;

    public Collection() {
    }

    public Collection(String id, String userid, String sportsid) {
        this.Collectionid = id;
        this.userid = userid;
        this.sprrtsid = sportsid;
    }


    public String getCollectionid() {
		return Collectionid;
	}

	public void setCollectionid(String collectionid) {
		Collectionid = collectionid;
	}

	public void setSprrtsid(String sprrtsid) {
		this.sprrtsid = sprrtsid;
	}

	public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSprrtsid() {
        return sprrtsid;
    }

    public void setSprrsid(String sprrtsid) {
        this.sprrtsid = sprrtsid;
    }
}
