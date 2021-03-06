package com.example.read_app.ui.read;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.read_app.R;
import com.example.read_app.application.SysManager;
import com.example.read_app.entity.Setting;
import com.example.read_app.greendao.entity.Chapter;

import java.util.ArrayList;

/**
 * Created by zhao on 2017/7/26.
 */

public class ChapterTitleAdapter extends ArrayAdapter<Chapter> {

    private int mResourceId;
    private Setting setting;
    private int curChapterPosition = -1;

    public ChapterTitleAdapter(Context context, int resourceId, ArrayList<Chapter> datas){
        super(context,resourceId,datas);
        mResourceId = resourceId;
        setting = SysManager.getSetting();
    }

    @Override
    public void notifyDataSetChanged() {
        setting = SysManager.getSetting();
        super.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(mResourceId,null);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_chapter_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        initView(position,viewHolder);
        return convertView;
    }

    private void initView(int postion,final ViewHolder viewHolder){
        final  Chapter chapter = getItem(postion);
        viewHolder.tvTitle.setText("【" + chapter.getTitle() + "】");
        if (setting.isDayStyle()) {
            viewHolder.tvTitle.setTextColor(getContext().getResources().getColor(setting.getReadWordColor()));
        }else {
            viewHolder.tvTitle.setTextColor(getContext().getResources().getColor(R.color.sys_night_word));
        }

        if (postion == curChapterPosition){
            viewHolder.tvTitle.setTextColor(getContext().getResources().getColor(R.color.sys_dialog_setting_word_red));
        }

    }

    public void setCurChapterPosition(int curChapterPosition) {
        this.curChapterPosition = curChapterPosition;
    }

    class ViewHolder{

        TextView tvTitle;

    }

}
