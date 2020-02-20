package com.runapp.bean;

public class TestQuestion {
   String testquestionsid;
   String img;
   String yuyan;
   String miaoshi;
   
public TestQuestion() {
	super();
}

public TestQuestion(String testquestionsid, String img, String yuyan, String miaoshi) {
	super();
	this.testquestionsid = testquestionsid;
	this.img = img;
	this.yuyan = yuyan;
	this.miaoshi = miaoshi;
}

public String getTestquestionsid() {
	return testquestionsid;
}
public void setTestquestionsid(String testquestionsid) {
	this.testquestionsid = testquestionsid;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public String getYuyan() {
	return yuyan;
}
public void setYuyan(String yuyan) {
	this.yuyan = yuyan;
}
public String getMiaoshi() {
	return miaoshi;
}
public void setMiaoshi(String miaoshi) {
	this.miaoshi = miaoshi;
}
   
}
