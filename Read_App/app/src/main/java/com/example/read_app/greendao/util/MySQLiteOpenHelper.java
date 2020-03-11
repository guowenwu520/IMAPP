package com.example.read_app.greendao.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.read_app.greendao.gen.BookDao;
import com.example.read_app.greendao.gen.ChapterDao;
import com.example.read_app.greendao.gen.DaoMaster;
import com.example.read_app.greendao.gen.SearchHistoryDao;

import org.greenrobot.greendao.database.Database;

/**
 * Created by zhao on 2017/3/15.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    private Context mContext;


    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        mContext = context;
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //加入你要新建的或者修改的表的信息
        GreenDaoUpgrade.getInstance().migrate(db, BookDao.class,ChapterDao.class,SearchHistoryDao.class);

    }



}
