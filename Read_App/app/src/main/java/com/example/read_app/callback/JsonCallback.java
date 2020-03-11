package com.example.read_app.callback;


import com.example.read_app.entity.JsonModel;

/**
 * Created by zhao on 2016/10/25.
 */

public interface JsonCallback {

    void onFinish(JsonModel jsonModel);

    void onError(Exception e);

}
