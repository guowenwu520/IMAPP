package com.example.read_app.greendao;


import com.example.read_app.application.MyApplication;
import com.example.read_app.greendao.gen.DaoMaster;
import com.example.read_app.greendao.gen.DaoSession;
import com.example.read_app.greendao.util.MySQLiteOpenHelper;

/**
 * Created by zhao on 2017/3/15.
 */

public class GreenDaoManager {
    private static GreenDaoManager instance;
    private static DaoMaster daoMaster;
    private static MySQLiteOpenHelper mySQLiteOpenHelper;

    public static GreenDaoManager getInstance() {
        if (instance == null) {
            instance = new GreenDaoManager();
        }
        return instance;
    }

    public GreenDaoManager(){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(MyApplication.getmContext(), "read" , null);
        daoMaster = new DaoMaster(mySQLiteOpenHelper.getWritableDatabase());
    }



    public DaoSession getSession(){
       return daoMaster.newSession();
    }

}
