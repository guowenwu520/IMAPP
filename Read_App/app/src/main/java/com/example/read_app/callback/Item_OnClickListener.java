package com.example.read_app.callback;

import android.view.View;

public interface Item_OnClickListener {//自定义接口实现点击事件
    public void OnLitemLister(View.OnClickListener onClickListener, int postion);
    public void OnLongLitemLister(View.OnLongClickListener onLongClickListener, int postion);

}
