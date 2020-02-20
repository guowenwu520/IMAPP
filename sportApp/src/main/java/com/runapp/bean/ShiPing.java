package com.runapp.bean;

public class ShiPing {
   public String shipingid;
   public String shiurl;
   public String times;
   public String nicheng;
   public String useraccount;
   public String title;
   public String miaoshu;
   
public ShiPing() {
	super();
}
public ShiPing(String shipingid, String shiurl, String times, String nicheng, String useraccount) {
	super();
	this.shipingid = shipingid;
	this.shiurl = shiurl;
	this.times = times;
	this.nicheng = nicheng;
	this.useraccount = useraccount;
}

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getMiaoshu() {
	return miaoshu;
}
public void setMiaoshu(String miaoshu) {
	this.miaoshu = miaoshu;
}
public String getShipingid() {
	return shipingid;
}
public void setShipingid(String shipingid) {
	this.shipingid = shipingid;
}
public String getShiurl() {
	return shiurl;
}
public void setShiurl(String shiurl) {
	this.shiurl = shiurl;
}
public String getTimes() {
	return times;
}
public void setTimes(String times) {
	this.times = times;
}
public String getNicheng() {
	return nicheng;
}
public void setNicheng(String nicheng) {
	this.nicheng = nicheng;
}
public String getUseraccount() {
	return useraccount;
}
public void setUseraccount(String useraccount) {
	this.useraccount = useraccount;
}
   
}
