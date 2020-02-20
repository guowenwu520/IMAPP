package com.runapp.bean;

/**
 * Created by 18179 on 2020/1/15.
 */

public class RunClass {
     private  String RunClassid;
     private  String classs;
     private  String classname;
     private  String classnumber;

    public RunClass() {
    }

    public RunClass(String id, String classs, String classname, String classnumber) {
        this.RunClassid = id;
        this.classs = classs;
        this.classname = classname;
        this.classnumber = classnumber;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

   
    public String getRunClassid() {
		return RunClassid;
	}

	public void setRunClassid(String runClassid) {
		RunClassid = runClassid;
	}

	public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getClassnumber() {
        return classnumber;
    }

    public void setClassnumber(String classnumber) {
        this.classnumber = classnumber;
    }
}
