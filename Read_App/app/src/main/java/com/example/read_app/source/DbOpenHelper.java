/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.read_app.source;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbOpenHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 7;
	private static DbOpenHelper instance;

	private static final String USERNAME_TABLE_CREATE = "CREATE TABLE "
			+ UserDao.TABLE_NAME + " ("
			+ UserDao.COLUMN_NAME_PASS + " TEXT, "
			+ UserDao.COLUMN_NAME_URL+ " TEXT, "
			+ UserDao.COLUMN_NAME_ID + " TEXT PRIMARY KEY);";


	private static final String PINGLUN_TABLE_CREATE = "CREATE TABLE "
			+ UserDao.PINGLUN_TABLE_NAME + " ("
			+ UserDao.PINGLUN_COLUMN_MY_ID + " TEXT, "
			+ UserDao.PINGLUN_COLUMN_DONGTAI_ID + " TEXT, "
			+ UserDao.PINGLUN_COLUMN_NAME_NAME+ " TEXT, "
			+ UserDao.PINGLUN_COLUMN_NAME_NAMEED + " TEXT, "
			+ UserDao.PINGLUN_COLUMN_NAME_MSG + " TEXT);";

	private static final String PINGLUNREAD_TABLE_CREATE = "CREATE TABLE "
			+ UserDao.PINGLUNREAD_TABLE_NAME + " ("
			+ UserDao.PINGLUNREAD_COLUMN_MY_ID + " TEXT, "
			+ UserDao.PINGLUNREAD_COLUMN_NAME_IMG + " TEXT, "
			+ UserDao.PINGLUNREAD_COLUMN_NAME_NAME+ " TEXT, "
			+ UserDao.PINGLUNREAD_COLUMN_NAME_MSG+ " TEXT, "
			+ UserDao.PINGLUNREAD_COLUMN_BOOK_ID+ " TEXT, "
			+ UserDao.PINGLUNREAD_COLUMN_NUMBER_ID + " TEXT);";


	private static final String ZAN_TABLE_CREATE = "CREATE TABLE "
			+ UserDao.ZAN_TABLE_NAME + " ("
			+UserDao.ZAN_COLUMN_MY_ID+" TEXT,"
			+ UserDao.ZAN_COLUMN_DONGTAI_ID + " TEXT, "
			+ UserDao.ZAN_COLUMN_NAME_NAME + " TEXT);";

	private static final String DONGTAI_TABLE_CREATE = "CREATE TABLE "
			+ UserDao.DONGTAI_TABLE_NAME + " ("
			+UserDao.DONGTAI_COLUMN_MY_ID+" TEXT,"
			+ UserDao.DONGTAI_COLUMN_NAME_ID + " TEXT, "
			+ UserDao.DONGTAI_COLUMN_NAME_HEAD + " TEXT, "
			+ UserDao.DONGTAI_COLUMN_NAME_NAME+ " TEXT, "
			+ UserDao.DONGTAI_COLUMN_NAME_MSG+ " TEXT, "
			+ UserDao.DONGTAI_COLUMN_NAME_TIMES + " TEXT);";

	private static final String IMGS_TABLE_CREATE = "CREATE TABLE "
			+ UserDao.IMGS_TABLE_NAME + " ("
			+ UserDao.IMGS_COLUMN_NAME_OUTID + " TEXT, "
			+ UserDao.BOOK_ID + " TEXT, "
			+ UserDao.IMGS_COLUMN_NAME_IMGURL+ " TEXT);";


	private DbOpenHelper(Context context) {
		super(context, getUserDatabaseName(), null, DATABASE_VERSION);
	}

	public static DbOpenHelper getInstance(Context context) {
		if (instance == null) {
			instance = new DbOpenHelper(context.getApplicationContext());
		}
		return instance;
	}

	private static String getUserDatabaseName() {
        return  "_demo.db";
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(USERNAME_TABLE_CREATE);
		db.execSQL(IMGS_TABLE_CREATE);
		db.execSQL(PINGLUN_TABLE_CREATE);
		db.execSQL(DONGTAI_TABLE_CREATE);
		db.execSQL(PINGLUNREAD_TABLE_CREATE);
		db.execSQL(ZAN_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	public void closeDB() {
	    if (instance != null) {
	        try {
	            SQLiteDatabase db = instance.getWritableDatabase();
	            db.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        instance = null;
	    }
	}
	
}
