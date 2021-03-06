package com.example.read_app.ui.search;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;

import com.example.read_app.R;
import com.example.read_app.application.MyApplication;
import com.example.read_app.base.BasePresenter;
import com.example.read_app.callback.ResultCallback;
import com.example.read_app.common.APPCONST;
import com.example.read_app.greendao.entity.Book;
import com.example.read_app.greendao.entity.SearchHistory;
import com.example.read_app.greendao.service.SearchHistoryService;
import com.example.read_app.ui.bookinfo.BookInfoActivity;
import com.example.read_app.util.ListViewHeight;
import com.example.read_app.util.StringHelper;
import com.example.read_app.webapi.CommonApi;

import java.util.ArrayList;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by zhao on 2017/7/26.
 */

public class SearchBookPrensenter implements BasePresenter {

    private SearchBookActivity mSearchBookActivity;
    private SearchBookAdapter mSearchBookAdapter;
    private String searchKey;//搜索关键字
    private ArrayList<Book> mBooks = new ArrayList<>();
    private ArrayList<SearchHistory> mSearchHistories = new ArrayList<>();
    private ArrayList<String> mSuggestions = new ArrayList<>();

    private SearchHistoryService mSearchHistoryService;

    private SearchHistoryAdapter mSearchHistoryAdapter;



    private int inputConfirm = 0;//搜索输入确认
    private int confirmTime = 1000;//搜索输入确认时间（毫秒）

    private static String[] suggestion = {"不朽凡人", "圣墟", "我是至尊" ,"龙王传说", "太古神王", "一念永恒", "雪鹰领主", "大主宰"};


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    search();
                    break;
                case 2:
                    initSearchList();
                    break;
                case 3:
                    mSearchBookActivity.getLvSearchBooksList().setAdapter(null);
                    mSearchBookActivity.getPbLoading().setVisibility(View.GONE);
                    break;
            }
        }
    };

    public SearchBookPrensenter(SearchBookActivity searchBookActivity) {
        mSearchBookActivity = searchBookActivity;
        mSearchHistoryService = new SearchHistoryService();
        for (int i = 0; i < suggestion.length; i++) {
            mSuggestions.add(suggestion[i]);
        }
    }

    @Override
    public void start() {

        mSearchBookActivity.getTvTitleText().setText("搜索");
        mSearchBookActivity.getLlTitleBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchBookActivity.finish();
            }
        });

        mSearchBookActivity.getEtSearchKey().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                searchKey = editable.toString();
                if (StringHelper.isEmpty(searchKey)) {
                    search();
                }

            }

        });

        mSearchBookActivity.getLvSearchBooksList().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mSearchBookActivity, BookInfoActivity.class);
                intent.putExtra(APPCONST.BOOK, mBooks.get(i));
                mSearchBookActivity.startActivity(intent);
            }
        });
        mSearchBookActivity.getTvSearchConform().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
        mSearchBookActivity.getTgSuggestBook().setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                mSearchBookActivity.getEtSearchKey().setText(tag);
                search();
            }
        });
        mSearchBookActivity.getLvHistoryList().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSearchBookActivity.getEtSearchKey().setText(mSearchHistories.get(position).getContent());
                search();
            }
        });
        mSearchBookActivity.getLlClearHistory().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchHistoryService.clearHistory();
                initHistoryList();
            }
        });
        initSuggestionBook();
        initHistoryList();
    }


    /**
     * 初始化建议书目
     */
    private void initSuggestionBook() {

        mSearchBookActivity.getTgSuggestBook().setTags(suggestion);
    }

    /**
     * 初始化历史列表
     */
    private void initHistoryList() {
        mSearchHistories = mSearchHistoryService.findAllSearchHistory();
        if (mSearchHistories == null || mSearchHistories.size() == 0) {
            mSearchBookActivity.getLlHistoryView().setVisibility(View.GONE);
        } else {
            mSearchHistoryAdapter = new SearchHistoryAdapter(mSearchBookActivity, R.layout.listview_search_history_item, mSearchHistories);
            mSearchBookActivity.getLvHistoryList().setAdapter(mSearchHistoryAdapter);
            mSearchBookActivity.getLlHistoryView().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化搜索列表
     */
    private void initSearchList() {
        mSearchBookAdapter = new SearchBookAdapter(mSearchBookActivity, R.layout.listview_search_book_item, mBooks);
        mSearchBookActivity.getLvSearchBooksList().setAdapter(mSearchBookAdapter);
        mSearchBookActivity.getLvSearchBooksList().setVisibility(View.VISIBLE);
        mSearchBookActivity.getLlSuggestBooksView().setVisibility(View.GONE);
        mSearchBookActivity.getLlHistoryView().setVisibility(View.GONE);
        mSearchBookActivity.getPbLoading().setVisibility(View.GONE);

    }

    /**
     * 获取搜索数据
     */
    private void getData() {
        mBooks.clear();
        CommonApi.search(searchKey, new ResultCallback() {

            @Override
            public void onFinish(Object o, int code) {
                mBooks = (ArrayList<Book>) o;
                mHandler.sendMessage(mHandler.obtainMessage(2));
            }

            @Override
            public void onError(Exception e) {
                mHandler.sendMessage(mHandler.obtainMessage(3));
            }
        });

    }

    /**
     * 搜索
     */
    private void search() {
        mSearchBookActivity.getPbLoading().setVisibility(View.VISIBLE);
        if (StringHelper.isEmpty(searchKey)) {
            mSearchBookActivity.getPbLoading().setVisibility(View.GONE);
            mSearchBookActivity.getLvSearchBooksList().setVisibility(View.GONE);
            mSearchBookActivity.getLlSuggestBooksView().setVisibility(View.VISIBLE);
            initHistoryList();
            mSearchBookActivity.getLvSearchBooksList().setAdapter(null);
        } else {
            mSearchBookActivity.getLvSearchBooksList().setVisibility(View.VISIBLE);
            mSearchBookActivity.getLlSuggestBooksView().setVisibility(View.GONE);
            mSearchBookActivity.getLlHistoryView().setVisibility(View.GONE);
            getData();
            mSearchHistoryService.addOrUpadteHistory(searchKey);
        }
    }

    public boolean onBackPressed() {
        if (StringHelper.isEmpty(searchKey)) {
            return false;
        } else {
            mSearchBookActivity.getEtSearchKey().setText("");
            return true;
        }
    }


}

