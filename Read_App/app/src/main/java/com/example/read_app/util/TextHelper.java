package com.example.read_app.util;

import android.widget.Toast;

import com.example.read_app.application.MyApplication;


/**
 * Created by zhao on 2016/10/26.
 */

public class TextHelper {

    public static void showText(final String text){

        MyApplication.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyApplication.getApplication(),text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void showLongText(final String text){

        MyApplication.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyApplication.getApplication(),text, Toast.LENGTH_LONG).show();
            }
        });
    }
}