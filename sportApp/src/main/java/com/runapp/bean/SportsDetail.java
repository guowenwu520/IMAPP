package com.runapp.bean;

import java.util.ArrayList;

/**
 * Created by 18179 on 2020/1/15.
 */

public class SportsDetail {
    private  String SportsDetailid;
    private  String classid;
    private String userid;
    ArrayList<Imgs> strings;
    private String address;
    private String tiem;
    //绠�浠�
    private String briefIntroduction;
   private  String title;
   //璇︾粏淇℃伅
   private  String detailmesg;
   private  String phone;
  private  String lat,lon;
    public SportsDetail() {
    }

    public ArrayList<Imgs> getStrings() {
        return strings;
    }

    public void setStrings(ArrayList<Imgs> strings) {
        this.strings = strings;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public SportsDetail(String id, String classid, String userid, String address, String tiem,  String briefIntroduction, String title, String detailmesg, String phone) {
        this.SportsDetailid = id;
        this.classid = classid;
        this.userid = userid;
        this.address = address;
        this.tiem = tiem;
        this.briefIntroduction = briefIntroduction;
        this.title = title;
        this.detailmesg = detailmesg;
        this.phone = phone;
    }

  

    public String getSportsDetailid() {
		return SportsDetailid;
	}

	public void setSportsDetailid(String sportsDetailid) {
		SportsDetailid = sportsDetailid;
	}

	public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTiem() {
        return tiem;
    }

    public void setTiem(String tiem) {
        this.tiem = tiem;
    }


    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailmesg() {
        return detailmesg;
    }

    public void setDetailmesg(String detailmesg) {
        this.detailmesg = detailmesg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "SportsDetail{" +
                ", classid='" + classid + '\'' +
                ", userid='" + userid + '\'' +
                ", strings=" + strings +
                ", address='" + address + '\'' +
                ", tiem='" + tiem + '\'' +
                ", briefIntroduction='" + briefIntroduction + '\'' +
                ", title='" + title + '\'' +
                ", detailmesg='" + detailmesg + '\'' +
                ", phone='" + phone + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
