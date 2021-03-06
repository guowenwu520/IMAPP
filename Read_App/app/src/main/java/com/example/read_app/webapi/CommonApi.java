package com.example.read_app.webapi;

import android.util.Log;

import com.example.read_app.callback.ResultCallback;
import com.example.read_app.common.APPCONST;
import com.example.read_app.common.URLCONST;
import com.example.read_app.util.crawler.TianLaiReadUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhao on 2017/7/24.
 */

public class CommonApi extends BaseApi{




    /**
     * 获取章节列表
     * @param url
     * @param callback
     */
    public static void getBookChapters(String url, final ResultCallback callback){

        getCommonReturnHtmlStringApi(url, null, "GBK", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(TianLaiReadUtil.getChaptersFromHtml((String) o),0);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);

            }
        });
    }

    /**
     * 获取章节正文
     * @param url
     * @param callback
     */
    public static void getChapterContent(String url, final ResultCallback callback){
        int tem = url.indexOf("\"");
        if (tem != -1){
            url = url.substring(0,tem);
        }
        getCommonReturnHtmlStringApi(URLCONST.nameSpace_tianlai + url, null, "GBK", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(TianLaiReadUtil.getContentFormHtml((String)o),0);

            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
    }

    /**
     * 搜索小说
     * @param key
     * @param callback
     */
    public static void search(String key, final ResultCallback callback){
        Map<String,Object> params = new HashMap<>();
        params.put("keyword", key);
        getCommonReturnHtmlStringApi(URLCONST.method_buxiu_search, params, "utf-8", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(TianLaiReadUtil.getBooksFromSearchHtml((String)o),code);
                Log.e("erer","sdsd");
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);

            }
        });
    }
    //获取分类
    public static void searchList(final ResultCallback callback){
          getCommonReturnHtmlStringApi(URLCONST.nameSpace_tianlai, null, "GBK", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(TianLaiReadUtil.getBooksClassHtml((String)o),code);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);

            }
        });
    }
    public static void getNewestAppVersion(final ResultCallback callback){
        getCommonReturnStringApi(URLCONST.method_getCurAppVersion,null,callback);
    }


    public static void booksList(final String key, final ResultCallback resultCallback) {
         getCommonReturnHtmlStringApi(URLCONST.nameSpace_tianlai+key, null, "GBK", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                resultCallback.onFinish(TianLaiReadUtil.getBooksFromStoreHtml((String)o,key),code);
             }

            @Override
            public void onError(Exception e) {
                resultCallback.onError(e);

            }
        });
    }
}
