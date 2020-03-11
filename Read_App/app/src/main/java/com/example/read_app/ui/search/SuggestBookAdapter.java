package com.example.read_app.ui.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.read_app.R;
import com.example.read_app.greendao.entity.SearchHistory;

import java.util.ArrayList;

/**
 * Created by zhao on 2017/7/26.
 */

public class SuggestBookAdapter extends ArrayAdapter<String> {

    private int mResourceId;

    public SuggestBookAdapter(Context context, int resourceId, ArrayList<String> datas) {
        super(context, resourceId, datas);
        mResourceId = resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(mResourceId, null);
            viewHolder.tvBookName = (TextView) convertView.findViewById(R.id.tv_suggestion_book);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        initView(position, viewHolder);
        return convertView;
    }

    private void initView(int postion, ViewHolder viewHolder) {
        String bookName = getItem(postion);
        viewHolder.tvBookName.setText(bookName);

    }

    class ViewHolder {

        TextView tvBookName;
    }

}
