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

import java.util.List;
import java.util.Map;

public class UserDao {
	public static final String TABLE_NAME = "uers";
	public static final String COLUMN_NAME_ID = "username";
	public static final String COLUMN_NAME_PASS = "pass";
	public static final String COLUMN_NAME_URL= "ul";


	public static final String DONGTAI_TABLE_NAME = "dongtai";
	public static final String DONGTAI_COLUMN_MY_ID = "id";
	public static final String DONGTAI_COLUMN_NAME_ID = "goudid";
	public static final String DONGTAI_COLUMN_NAME_HEAD = "userhead";
	public static final String DONGTAI_COLUMN_NAME_TIMES= "times";
	public static final String DONGTAI_COLUMN_NAME_NAME= "name";
	public static final String DONGTAI_COLUMN_NAME_MSG= "msg";

	public static final String ZAN_TABLE_NAME = "zan";
	public static final String ZAN_COLUMN_MY_ID = "id";
	public static final String ZAN_COLUMN_DONGTAI_ID = "dtaiid";
	public static final String ZAN_COLUMN_NAME_NAME= "name";

	public static final String PINGLUNREAD_TABLE_NAME = "pinglunsrade";
	public static final String PINGLUNREAD_COLUMN_MY_ID = "id";
	public static final String PINGLUNREAD_COLUMN_NAME_NAME= "name";
	public static final String PINGLUNREAD_COLUMN_NAME_IMG= "url";
	public static final String PINGLUNREAD_COLUMN_NAME_MSG= "msg";
	public static final String PINGLUNREAD_COLUMN_BOOK_ID="bookId";
	public static final String PINGLUNREAD_COLUMN_NUMBER_ID="number";

	public static final String PINGLUN_TABLE_NAME = "pinglun";
	public static final String PINGLUN_COLUMN_MY_ID = "id";
	public static final String PINGLUN_COLUMN_DONGTAI_ID = "dtaiid";
	public static final String PINGLUN_COLUMN_NAME_NAME= "name";
	public static final  String PINGLUN_COLUMN_NAME_NAMEED= "nameed";
	public static final String PINGLUN_COLUMN_NAME_MSG= "msg";

	public static final String IMGS_TABLE_NAME = "imgs";
	public static final String IMGS_COLUMN_NAME_OUTID = "outid";
	public static final String IMGS_COLUMN_NAME_IMGURL = "imgurl";
	public static final String BOOK_ID = "bookid";

    public UserDao(Context context) {
	}

}
