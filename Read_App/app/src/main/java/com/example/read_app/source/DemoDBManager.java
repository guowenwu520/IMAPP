package com.example.read_app.source;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.read_app.application.MyApplication;
import com.example.read_app.entity.DongTai;
import com.example.read_app.entity.Imgs;
import com.example.read_app.entity.PingLun;
import com.example.read_app.entity.PingLunRead;
import com.example.read_app.entity.User;
import com.example.read_app.entity.Zan_Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DemoDBManager {
    static private DemoDBManager dbMgr = new DemoDBManager();
    private DbOpenHelper dbHelper;
    
    private DemoDBManager(){
        dbHelper = DbOpenHelper.getInstance(MyApplication.getmContext());
    }
    
    public static synchronized DemoDBManager getInstance(){
        if(dbMgr == null){
            dbMgr = new DemoDBManager();
        }
        return dbMgr;
    }

    synchronized public void deleteContact(String username){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.delete(UserDao.TABLE_NAME, UserDao.COLUMN_NAME_ID + " = ?", new String[]{username});
        }
    }






    public ArrayList<Imgs> getImgs(String id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Imgs> msgs = new ArrayList<Imgs>();
        if(db.isOpen()){
            Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ? ", UserDao.IMGS_TABLE_NAME, UserDao.IMGS_COLUMN_NAME_OUTID),new String[]{id});
            while(cursor.moveToNext()){
                Imgs msg = new Imgs();
                String ids = cursor.getString(cursor.getColumnIndex(UserDao.IMGS_COLUMN_NAME_OUTID));
                String imgurl = cursor.getString(cursor.getColumnIndex(UserDao.IMGS_COLUMN_NAME_IMGURL));
                msg.setIMGS_COLUMN_NAME_IMGURL(imgurl);
                msg.setIMGS_COLUMN_NAME_OUTID(ids);
                msg.setBOOKS_ID(cursor.getString(cursor.getColumnIndex(UserDao.BOOK_ID)));
                msgs.add(msg);
            }
            cursor.close();
        }
        return msgs;
    }

    public void addImgs(Imgs imgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(UserDao.IMGS_COLUMN_NAME_IMGURL, imgs.getIMGS_COLUMN_NAME_IMGURL());
            values.put(UserDao.IMGS_COLUMN_NAME_OUTID, imgs.getIMGS_COLUMN_NAME_OUTID());
            values.put(UserDao.BOOK_ID, imgs.getBOOKS_ID());
            db.insert(UserDao.IMGS_TABLE_NAME, null, values);
        }
	}




    public void remontImgs(String imgs_column_name_outid) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.delete(UserDao.IMGS_TABLE_NAME, UserDao.IMGS_COLUMN_NAME_OUTID + " = ?", new String[]{imgs_column_name_outid});
        } }





    public void remontDongTai(String dongtai_column_my_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.delete(UserDao.DONGTAI_TABLE_NAME, UserDao.DONGTAI_COLUMN_MY_ID + " = ?", new String[]{dongtai_column_my_id});
        }
    }

    public void remontPingLun(String pinglun_column_my_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.delete(UserDao.PINGLUN_TABLE_NAME, UserDao.PINGLUN_COLUMN_MY_ID + " = ?", new String[]{pinglun_column_my_id});
        }
	}

    public ArrayList<DongTai> getDongTai() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<DongTai> msgs = new ArrayList<DongTai>();
        if(db.isOpen()){
            Cursor cursor = db.rawQuery(String.format("select * from %s", UserDao.DONGTAI_TABLE_NAME),null);
            while(cursor.moveToNext()){
                DongTai msg = new DongTai();
                String id = cursor.getString(cursor.getColumnIndex(UserDao.DONGTAI_COLUMN_NAME_ID));
                String userhead = cursor.getString(cursor.getColumnIndex(UserDao.DONGTAI_COLUMN_NAME_HEAD));
                String times = cursor.getString(cursor.getColumnIndex(UserDao.DONGTAI_COLUMN_NAME_TIMES));
                String myid = cursor.getString(cursor.getColumnIndex(UserDao.DONGTAI_COLUMN_MY_ID));
                String name=cursor.getString(cursor.getColumnIndex(UserDao.DONGTAI_COLUMN_NAME_NAME));
                String mssgs=cursor.getString(cursor.getColumnIndex(UserDao.DONGTAI_COLUMN_NAME_MSG));
                msg.setDONGTAI_COLUMN_NAME_HEAD(userhead);
                msg.setDONGTAI_COLUMN_NAME_ID(id);
                msg.setDONGTAI_COLUMN_NAME_TIMES(times);
                msg.setDONGTAI_COLUMN_MY_ID(myid);
                msg.setDONGTAI_COLUMN_NAME_NAME(name);
                msg.setDONGTAI_COLUMN_NAME_MSG(mssgs);
                msgs.add(msg);
            }
            cursor.close();
        }
        return msgs;
    }



    public void addDongTai(DongTai album) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(UserDao.DONGTAI_COLUMN_MY_ID, album.getDONGTAI_COLUMN_MY_ID());
            values.put(UserDao.DONGTAI_COLUMN_NAME_HEAD, album.getDONGTAI_COLUMN_NAME_HEAD());
            values.put(UserDao.DONGTAI_COLUMN_NAME_ID, album.getDONGTAI_COLUMN_NAME_ID());
            values.put(UserDao.DONGTAI_COLUMN_NAME_NAME, album.getDONGTAI_COLUMN_NAME_NAME());
            values.put(UserDao.DONGTAI_COLUMN_NAME_TIMES, album.getDONGTAI_COLUMN_NAME_TIMES());
            values.put(UserDao.DONGTAI_COLUMN_NAME_MSG,album.getDONGTAI_COLUMN_NAME_MSG());
            db.insert(UserDao.DONGTAI_TABLE_NAME, null, values);
        }
    }


    public void remontZan(String currentUser, String dongtai_column_my_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.delete(UserDao.ZAN_TABLE_NAME, UserDao.ZAN_COLUMN_DONGTAI_ID + " = ? and "+UserDao.ZAN_COLUMN_NAME_NAME+" = ?", new String[]{dongtai_column_my_id,currentUser});
        }
    }

    public void addDianZan(Zan_Data album) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(UserDao.ZAN_COLUMN_MY_ID, album.getZAN_COLUMN_MY_ID());
            values.put(UserDao.ZAN_COLUMN_NAME_NAME, album.getZAN_COLUMN_NAME_NAME());
            values.put(UserDao.ZAN_COLUMN_DONGTAI_ID, album.getZAN_COLUMN_DONGTAI_ID());
            db.insert(UserDao.ZAN_TABLE_NAME, null, values);
        }
    }
    public void updateUser(User album) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(UserDao.COLUMN_NAME_URL, album.getUrlimg());
            db.update(UserDao.TABLE_NAME,values,UserDao.COLUMN_NAME_ID+"=?",new String[]{album.getName()});
        }
    }public void updatePass(User album) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(UserDao.COLUMN_NAME_PASS, album.getPass());
            db.update(UserDao.TABLE_NAME,values,UserDao.COLUMN_NAME_ID+"=?",new String[]{album.getName()});
        }
    }
    public void addUser(User album) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(UserDao.COLUMN_NAME_ID, album.getName());
            values.put(UserDao.COLUMN_NAME_PASS, album.getPass());
            values.put(UserDao.COLUMN_NAME_URL, album.getUrlimg());
            db.insert(UserDao.TABLE_NAME, null, values);
        }
    }
    public ArrayList<Zan_Data> getZanData(String dongtai_column_my_id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Zan_Data> msgs = new ArrayList<Zan_Data>();
        if(db.isOpen()) {
            Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ? ", UserDao.ZAN_TABLE_NAME, UserDao.ZAN_COLUMN_DONGTAI_ID), new String[]{dongtai_column_my_id});
            while (cursor.moveToNext()) {
                Zan_Data msg = new Zan_Data();
                String id = cursor.getString(cursor.getColumnIndex(UserDao.ZAN_COLUMN_MY_ID));
                String pingdtaiid = cursor.getString(cursor.getColumnIndex(UserDao.ZAN_COLUMN_DONGTAI_ID));
                String name = cursor.getString(cursor.getColumnIndex(UserDao.ZAN_COLUMN_NAME_NAME));
                msg.setZAN_COLUMN_MY_ID(id);
                msg.setZAN_COLUMN_DONGTAI_ID(pingdtaiid);
                msg.setZAN_COLUMN_NAME_NAME(name);
                msgs.add(msg);
            }
            cursor.close();
        }
	    return msgs;
    }
    public void addPingLun(PingLun album) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(UserDao.PINGLUN_COLUMN_MY_ID, album.getPINGLUN_COLUMN_MY_ID());
            values.put(UserDao.PINGLUN_COLUMN_NAME_NAMEED, album.getPINGLUN_COLUMN_NAME_NAMEED());
            values.put(UserDao.PINGLUN_COLUMN_NAME_NAME, album.getPINGLUN_COLUMN_NAME_NAME());
            values.put(UserDao.PINGLUN_COLUMN_DONGTAI_ID, album.getPINGLUN_COLUMN_DONGTAI_ID());
            values.put(UserDao.PINGLUN_COLUMN_NAME_MSG,album.getPINGLUN_COLUMN_NAME_MSG());
            db.insert(UserDao.PINGLUN_TABLE_NAME, null, values);
        }
    }
    public void remontZanData(String zan_column_my_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()){
            db.delete(UserDao.ZAN_TABLE_NAME, UserDao.ZAN_COLUMN_MY_ID + " = ? ", new String[]{zan_column_my_id});
        }
    }

    public boolean isLogn(String currentPassword, String currentUsername) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(db.isOpen()){
            Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ? and %s=? ", UserDao.TABLE_NAME, UserDao.COLUMN_NAME_PASS,UserDao.COLUMN_NAME_ID),new String[]{currentPassword,currentUsername});
            if(cursor.moveToNext()){
                cursor.close();
              return true;
            }
            cursor.close();
        }
        return false;
    }

    public ArrayList<PingLun> getPingLun(String dongtai_column_my_id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<PingLun> msgs = new ArrayList<PingLun>();
        if(db.isOpen()){
            Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ? ", UserDao.PINGLUN_TABLE_NAME, UserDao.PINGLUN_COLUMN_DONGTAI_ID),new String[]{dongtai_column_my_id});
            while(cursor.moveToNext()){
                PingLun msg = new PingLun();
                String id = cursor.getString(cursor.getColumnIndex(UserDao.PINGLUN_COLUMN_MY_ID));
                String pingdtaiid = cursor.getString(cursor.getColumnIndex(UserDao.PINGLUN_COLUMN_DONGTAI_ID));
                String name = cursor.getString(cursor.getColumnIndex(UserDao.PINGLUN_COLUMN_NAME_NAME));
                String nameed=cursor.getString(cursor.getColumnIndex(UserDao.PINGLUN_COLUMN_NAME_NAMEED));
                String mssgs=cursor.getString(cursor.getColumnIndex(UserDao.PINGLUN_COLUMN_NAME_MSG));
                msg.setPINGLUN_COLUMN_MY_ID(id);
                msg.setPINGLUN_COLUMN_DONGTAI_ID(pingdtaiid);
                msg.setPINGLUN_COLUMN_NAME_NAMEED(nameed);
                msg.setPINGLUN_COLUMN_NAME_NAME(name);
                msg.setPINGLUN_COLUMN_NAME_MSG(mssgs);
                msgs.add(msg);
            }
            cursor.close();
        }
        return msgs;
    }

    public ArrayList<PingLunRead> getPingLun(String bookId, int number) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<PingLunRead> msgs = new ArrayList<PingLunRead>();
        if(db.isOpen()){
            Cursor cursor = db.rawQuery(String.format("select * from %s where %s = ? and %s=?", UserDao.PINGLUNREAD_TABLE_NAME, UserDao.PINGLUNREAD_COLUMN_BOOK_ID,UserDao.PINGLUNREAD_COLUMN_NUMBER_ID),new String[]{bookId,number+""});
            while(cursor.moveToNext()){
                PingLunRead msg = new PingLunRead();
                String id = cursor.getString(cursor.getColumnIndex(UserDao.PINGLUNREAD_COLUMN_MY_ID));
                String bookid = cursor.getString(cursor.getColumnIndex(UserDao.PINGLUNREAD_COLUMN_BOOK_ID));
                String name = cursor.getString(cursor.getColumnIndex(UserDao.PINGLUNREAD_COLUMN_NAME_NAME));
                String Number=cursor.getString(cursor.getColumnIndex(UserDao.PINGLUNREAD_COLUMN_NUMBER_ID));
                String mssgs=cursor.getString(cursor.getColumnIndex(UserDao.PINGLUNREAD_COLUMN_NAME_MSG));
                String url=cursor.getString(cursor.getColumnIndex(UserDao.PINGLUNREAD_COLUMN_NAME_IMG));
                msg.setPINGLUN_COLUMN_MY_ID(id);
                msg.setPINGLUN_COLUMN_BOOK_ID(bookid);
                msg.setPINGLUN_COLUMN_NUMBER_ID(Number);
                msg.setPINGLUN_COLUMN_NAME_IMG(url);
                msg.setPINGLUN_COLUMN_NAME_NAME(name);
                msg.setPINGLUN_COLUMN_NAME_MSG(mssgs);
                msgs.add(msg);
            }
            cursor.close();
        }
        return msgs;
    }

    public void addPingLunRead(PingLunRead album) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(UserDao.PINGLUNREAD_COLUMN_MY_ID, album.getPINGLUN_COLUMN_MY_ID());
            values.put(UserDao.PINGLUNREAD_COLUMN_NAME_NAME, album.getPINGLUN_COLUMN_NAME_NAME());
            values.put(UserDao.PINGLUNREAD_COLUMN_BOOK_ID, album.getPINGLUN_COLUMN_BOOK_ID());
            values.put(UserDao.PINGLUNREAD_COLUMN_NUMBER_ID, album.getPINGLUN_COLUMN_NUMBER_ID());
            values.put(UserDao.PINGLUNREAD_COLUMN_NAME_MSG,album.getPINGLUN_COLUMN_NAME_MSG());
            values.put(UserDao.PINGLUNREAD_COLUMN_NAME_IMG,album.getPINGLUN_COLUMN_NAME_IMG());
            db.insert(UserDao.PINGLUNREAD_TABLE_NAME, null, values);
        }
    }
}
