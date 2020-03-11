package com.example.read_app.ui.read;

import android.app.AlertDialog;
import android.app.AutomaticZenRule;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.read_app.callback.Item_OnClickListener;
import com.example.read_app.custom.CircleImageView;
import com.example.read_app.entity.Item;
import com.example.read_app.entity.PingLun;
import com.example.read_app.entity.PingLunRead;
import com.example.read_app.source.DemoDBManager;
import com.example.read_app.ui.home.bookstore.BookStoreFragment;
import com.spreada.utils.chinese.ZHConverter;
import com.example.read_app.R;
import com.example.read_app.application.SysManager;
import com.example.read_app.callback.ResultCallback;
import com.example.read_app.custom.ContainsEmojiEditText;
import com.example.read_app.custom.MyTextView;
import com.example.read_app.entity.Setting;
import com.example.read_app.enums.Font;
import com.example.read_app.enums.Language;
import com.example.read_app.greendao.entity.Book;
import com.example.read_app.greendao.entity.Chapter;
import com.example.read_app.greendao.service.BookService;
import com.example.read_app.greendao.service.ChapterService;
import com.example.read_app.util.StringHelper;
import com.example.read_app.webapi.CommonApi;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zhao on 2017/8/17.
 */

public class ReadContentAdapter extends RecyclerView.Adapter<ReadContentAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<Chapter> mDatas;
    private OnClickItemListener mOnClickItemListener;
    private View.OnTouchListener mOnTouchListener;
    private OnLongClickItemListener onLongClickItemListener;

    private ChapterService mChapterService;
    private BookService mBookService;
    private Setting mSetting;
    private Book mBook;
    private Typeface mTypeFace;
    private TextView curTextView;
    private int mResourceId;
    private Context mContext;
    private RecyclerView rvContent;
    private ArrayList<PingLunRead> pingLuns=new ArrayList<>();
    private  String url,name;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ViewHolder viewHolder = (ViewHolder) msg.obj;
                    viewHolder.tvErrorTips.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    ViewHolder viewHolder2 = (ViewHolder) msg.obj;
                    viewHolder2.tv_chat.setText(pingLuns.size()+"");
                    break;
            }
        }
    };

    public ReadContentAdapter(Context context, int resourceId, ArrayList<Chapter> datas, Book book) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        mResourceId = resourceId;
        mChapterService = new ChapterService();
        mBookService = new BookService();
        mSetting = SysManager.getSetting();
        mBook = book;
        mContext = context;
        SharedPreferences sharedPreferences=mContext.getSharedPreferences("user",MODE_PRIVATE);
        name=sharedPreferences.getString("name","kiki");
        url=sharedPreferences.getString("url","kiki");
        initFont();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View arg0) {
            super(arg0);
        }
        TextView tv_chat;
        MyTextView tvTitle;
        MyTextView tvContent;
        TextView tvErrorTips;
    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public Chapter getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (rvContent == null) rvContent = (RecyclerView) viewGroup;
        View view = mInflater.inflate(mResourceId, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tvTitle = (MyTextView) view.findViewById(R.id.tv_title);
        viewHolder.tv_chat=view.findViewById(R.id.tv_chat);
        viewHolder.tvContent = (MyTextView) view.findViewById(R.id.tv_content);
        viewHolder.tvErrorTips = (TextView) view.findViewById(R.id.tv_loading_error_tips);
        return viewHolder;
    }



    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        initView(i, viewHolder);

       /* if (mOnTouchListener != null){
            viewHolder.tvContent.setmOnTouchListener(mOnTouchListener);
        }

        viewHolder.tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickItemListener != null) {
                    mOnClickItemListener.onClick(viewHolder.itemView, i);
                }
            }
        });*/
        if (mOnTouchListener != null){
            viewHolder.itemView.setOnTouchListener(mOnTouchListener);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickItemListener != null) {
                    mOnClickItemListener.onClick(viewHolder.itemView, i);
                }
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onLongClickItemListener != null) {
                    onLongClickItemListener.onClick(viewHolder.itemView, i);
                }
                return false;
            }
        });
    }

    private void initView(final int postion, final ViewHolder viewHolder) {
        final Chapter chapter = getItem(postion);
        mHandler.post(new Runnable() {
            @Override
            public void run() {

                pingLuns= DemoDBManager.getInstance().getPingLun(chapter.getBookId(),chapter.getNumber());
                if(viewHolder!=null)
                mHandler.sendMessage(mHandler.obtainMessage(2,viewHolder));
            }
        });

//        hiddenSoftInput(viewHolder.tvContent);
//        hiddenSoftInput(viewHolder.tvTitle);
        viewHolder.tvContent.setTypeface(mTypeFace);
        viewHolder.tvTitle.setTypeface(mTypeFace);
        viewHolder.tvErrorTips.setVisibility(View.GONE);
        viewHolder.tvTitle.setText("【" + getLanguageContext(chapter.getTitle()) + "】");
        viewHolder.tv_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(mContext);
                View view=LayoutInflater.from(mContext).inflate(R.layout.pinglun_dialong_view,null,false);
                RecyclerView recyclerView=view.findViewById(R.id.recycle_item_view);
                RelativeLayout cunt_lasy=view.findViewById(R.id.cunt_lasy);
                cunt_lasy.setBackgroundResource(mSetting.getReadBgColor());
                EditText editText=view.findViewById(R.id.editmsg);
                TextView send=view.findViewById(R.id.send);
                ItemAdapter itemAdapter=new ItemAdapter();
                recyclerView.setAdapter(itemAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                alertDialog.setView(view);
                Dialog dialog=alertDialog.show();
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str=editText.getText().toString().trim();
                        if(!str.equals("")){
                            PingLunRead pingLun=new PingLunRead();
                            pingLun.setPINGLUN_COLUMN_NAME_IMG(url);
                            pingLun.setPINGLUN_COLUMN_MY_ID(System.currentTimeMillis()+"");
                            pingLun.setPINGLUN_COLUMN_BOOK_ID(chapter.getBookId());
                            pingLun.setPINGLUN_COLUMN_NAME_MSG(str);
                            pingLun.setPINGLUN_COLUMN_NAME_NAME(name);
                            pingLun.setPINGLUN_COLUMN_NUMBER_ID(chapter.getNumber()+"");
                            new Thread(){
                                @Override
                                public void run() {
                                    super.run();
                                    DemoDBManager.getInstance().addPingLunRead(pingLun);
                                    pingLuns= DemoDBManager.getInstance().getPingLun(chapter.getBookId(),chapter.getNumber());
                                    mHandler.sendMessage(mHandler.obtainMessage(2,viewHolder));
                                }
                            }.start();
                        }
                        dialog.dismiss();
                    }
                });

            }
        });
        if (mSetting.isDayStyle()) {
            viewHolder.tvTitle.setTextColor(mContext.getResources().getColor(mSetting.getReadWordColor()));
            viewHolder.tvContent.setTextColor(mContext.getResources().getColor(mSetting.getReadWordColor()));
        } else {
            viewHolder.tvTitle.setTextColor(mContext.getResources().getColor(R.color.sys_night_word));
            viewHolder.tvContent.setTextColor(mContext.getResources().getColor(R.color.sys_night_word));
        }

        viewHolder.tvTitle.setTextSize(mSetting.getReadWordSize() + 2);
        viewHolder.tvContent.setTextSize(mSetting.getReadWordSize());
        viewHolder.tvErrorTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChapterContent(chapter, viewHolder);
            }
        });
        if (StringHelper.isEmpty(chapter.getContent())) {
            getChapterContent(chapter, viewHolder);
        } else {
            viewHolder.tvContent.setText(getLanguageContext(chapter.getContent()));
        }


        curTextView = viewHolder.tvContent;

        preLoading(postion);

        lastLoading(postion);



//        saveHistory(postion);

    }




    public void notifyDataSetChangedBySetting() {
        mSetting = SysManager.getSetting();
        initFont();
        super.notifyDataSetChanged();
    }

    public TextView getCurTextView() {
        return curTextView;
    }

    private String getLanguageContext(String content) {
        if (mSetting.getLanguage() == Language.traditional && mSetting.getFont() == Font.默认字体) {
            return ZHConverter.convert(content, ZHConverter.TRADITIONAL);
        }
        return content;

    }

    /**
     * 加载章节内容
     *
     * @param chapter
     * @param viewHolder
     */
    private void getChapterContent(final Chapter chapter, final ViewHolder viewHolder) {
        if (viewHolder != null) {
            viewHolder.tvErrorTips.setVisibility(View.GONE);
        }
        Chapter cacheChapter = mChapterService.findChapterByBookIdAndTitle(chapter.getBookId(), chapter.getTitle());

        if (cacheChapter != null && !StringHelper.isEmpty(cacheChapter.getContent())) {
            chapter.setContent(cacheChapter.getContent());
            chapter.setId(cacheChapter.getId());
            if (viewHolder != null) {
                if (mSetting.getLanguage() == Language.traditional) {
                    viewHolder.tvContent.setText(ZHConverter.convert(chapter.getTitle(), ZHConverter.TRADITIONAL));
                } else {
                    viewHolder.tvContent.setText(chapter.getContent());
                }

                viewHolder.tvErrorTips.setVisibility(View.GONE);
            }
        } else {
            CommonApi.getChapterContent(chapter.getUrl(), new ResultCallback() {
                @Override
                public void onFinish(final Object o, int code) {
                    chapter.setContent((String) o);
                    mChapterService.saveOrUpdateChapter(chapter);
                    if (viewHolder != null) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                viewHolder.tvContent.setText(getLanguageContext((String) o));
                                viewHolder.tvErrorTips.setVisibility(View.GONE);
                            }
                        });
                    }
                }

                @Override
                public void onError(Exception e) {
                    if (viewHolder != null) {
                        mHandler.sendMessage(mHandler.obtainMessage(1, viewHolder));
                    }
                }

            });
        }

    }


    /**
     * 预加载下一章
     */
    private void preLoading(int position) {
        if (position + 1 < getItemCount()) {
            Chapter chapter = getItem(position + 1);
            if (StringHelper.isEmpty(chapter.getContent())) {
                getChapterContent(chapter, null);
            }
        }
    }

    /**
     * 预加载上一张
     *
     * @param position
     */
    private void lastLoading(int position) {
        if (position > 0) {
            Chapter chapter = getItem(position - 1);
            if (StringHelper.isEmpty(chapter.getContent())) {
                getChapterContent(chapter, null);
            }
        }
    }

    public void saveHistory(int position) {
        if (!StringHelper.isEmpty(mBook.getId())) {
            mBook.setHisttoryChapterNum(position);
            mBookService.updateEntity(mBook);
        }
    }

    public void initFont() {
        if (mSetting.getFont() == Font.默认字体) {
            mTypeFace = null;
        } else {
            mTypeFace = Typeface.createFromAsset(mContext.getAssets(), mSetting.getFont().path);
        }
    }

    private void hiddenSoftInput(EditText editText){
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        try {
            Class<EditText> cls = EditText.class;
            Method setSoftInputShownOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
            setSoftInputShownOnFocus.setAccessible(true);
            setSoftInputShownOnFocus.invoke(editText, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setmOnClickItemListener(OnClickItemListener mOnClickItemListener) {
        this.mOnClickItemListener = mOnClickItemListener;
    }
    public void setmOnLongClickItemListener(OnLongClickItemListener onLongClickItemListener) {
        this.onLongClickItemListener = onLongClickItemListener;
    }
    public void setmOnTouchListener(View.OnTouchListener mOnTouchListener) {
        this.mOnTouchListener = mOnTouchListener;
    }

    public interface OnClickItemListener {
        void onClick(View view, int positon);
    }
    public interface OnLongClickItemListener {
        void onClick(View view, int positon);
    }

    class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{//RecyclerView控件的适配器
         @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//创建
            View view= LayoutInflater.from(mContext).inflate(R.layout.pinglun_item_view,parent,false);//视图搜索框
            return new ItemAdapter.myhoder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {//对每个子项绑定数据，建立视图与数据的联系，给ViewHolder里面的控件赋值
           ItemAdapter.myhoder myhoder= (ItemAdapter.myhoder) holder;
            PingLunRead item=pingLuns.get(position);
           myhoder.name.setText(item.getPINGLUN_COLUMN_NAME_NAME());myhoder.name.setTextColor(mContext.getResources().getColor(mSetting.getReadWordColor()));
           myhoder.msg.setText(item.getPINGLUN_COLUMN_NAME_MSG());myhoder.msg.setTextColor(mContext.getResources().getColor(mSetting.getReadWordColor()));
           if(item.getPINGLUN_COLUMN_NAME_IMG().equals("")){
               Glide.with(mContext).load(R.mipmap.avatar).into(myhoder.circleImageView);
           }else
            Glide.with(mContext).load(item.getPINGLUN_COLUMN_NAME_IMG()).into(myhoder.circleImageView);
        }

        @Override
        public int getItemCount() {//多少行
            return pingLuns.size();
        }
        class  myhoder extends RecyclerView.ViewHolder{//继承重写构造函数
            TextView msg,name;
            CircleImageView circleImageView;

            public myhoder(View itemView) {
                super(itemView);
                circleImageView=itemView.findViewById(R.id.headimg);
                msg=itemView.findViewById(R.id.mag);
                name=itemView.findViewById(R.id.name);
            }
        }
    }
}
