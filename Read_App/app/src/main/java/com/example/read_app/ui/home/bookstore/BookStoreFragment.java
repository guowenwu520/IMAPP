package com.example.read_app.ui.home.bookstore;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.read_app.R;
import com.example.read_app.callback.Item_OnClickListener;
import com.example.read_app.callback.ResultCallback;
import com.example.read_app.common.APPCONST;
import com.example.read_app.entity.Item;
import com.example.read_app.greendao.entity.Book;
import com.example.read_app.ui.bookinfo.BookInfoActivity;
import com.example.read_app.util.StringHelper;
import com.example.read_app.webapi.CommonApi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import butterknife.BindView;

import static com.example.read_app.common.URLCONST.nameSpace_tianlai;
import static com.example.read_app.webapi.BaseApi.getCommonReturnHtmlStringApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookStoreFragment extends Fragment {
     RecyclerView navitor_main_recycle;
     RecyclerView nrong_recycle;
     String key="/",MyType="首页";
    ArrayList<Item> items=new ArrayList<Item>();
    ArrayList<Book> books=new ArrayList<Book>();
    public BookStoreFragment() {
        // Required empty public constructor
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    final ItemAdapter itemAdapter=new ItemAdapter();
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    navitor_main_recycle.setLayoutManager(linearLayoutManager);
                    navitor_main_recycle.setAdapter(itemAdapter);
                    itemAdapter.setItem_onClickListener(new Item_OnClickListener() {
                        @Override
                        public void OnLitemLister(View.OnClickListener onClickListener, int postion) {
                               Item item=items.get(postion);
                               books.clear();
                               for (int i=0;i<items.size();i++){
                                   items.get(i).setIsselect(0);
                               }
                               item.setIsselect(1);
                            MyType=item.getName();
                            CommonApi.booksList(item.getUrl(),new ResultCallback() {

                                @Override
                                public void onFinish(Object o, int code) {
                                    books= (ArrayList<Book>) o;
                                    mHandler.sendMessage(mHandler.obtainMessage(2));
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });
                            itemAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void OnLongLitemLister(View.OnLongClickListener onLongClickListener, int postion) {

                        }
                    });
                    break;
                case 2:
                    BooksAdapter booksAdapter=new BooksAdapter();
                    LinearLayoutManager linearLayoutManager2=new LinearLayoutManager(getContext());
                    linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
                    nrong_recycle.setLayoutManager(linearLayoutManager2);
                    nrong_recycle.setAdapter(booksAdapter);
                    booksAdapter.setItem_onClickListener(new Item_OnClickListener() {
                        @Override
                        public void OnLitemLister(View.OnClickListener onClickListener, final int postion) {
                            getCommonReturnHtmlStringApi(books.get(postion).getChapterUrl(), null, "GBK", new ResultCallback() {
                                @Override
                                public void onFinish(Object o, int code) {
                                    Document doc2 = Jsoup.parse((String) o);
                                    Element divs = doc2.getElementById("info");
                                    Elements tds = divs.select("p");
                                    String[] strw = tds.get(0).text().split("：");
                                    books.get(postion).setAuthor(strw[1]);
                                    Element divs2 = doc2.getElementById("intro");
                                    books.get(postion).setDesc(divs2.text());
                                    Element divs3 = doc2.getElementById("sidebar");
                                    Elements tds2 = divs3.select("img");
                                    books.get(postion).setImgUrl(tds2.get(0).attr("src"));
                                    mHandler.sendMessage(mHandler.obtainMessage(3,postion));
                                }

                                @Override
                                public void onError(Exception e) {

                                }
                            });

                        }

                        @Override
                        public void OnLongLitemLister(View.OnLongClickListener onLongClickListener, int postion) {

                        }
                    });
                    break;
                case 3:
                    Intent intent = new Intent(getContext(), BookInfoActivity.class);
                    intent.putExtra(APPCONST.BOOK, books.get((Integer) msg.obj));
                    getContext().startActivity(intent);
                     break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view=  inflater.inflate(R.layout.fragment_book_store, container, false);
        navitor_main_recycle=view.findViewById(R.id.navitor_main_recycle);
        nrong_recycle=view.findViewById(R.id.nrong_recycle);   getData();
        return view ;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void getData() {
       items.clear();
        CommonApi.searchList(new ResultCallback() {

            @Override
            public void onFinish(Object o, int code) {
                items= (ArrayList<Item>) o;
                mHandler.sendMessage(mHandler.obtainMessage(1));
                CommonApi.booksList(key,new ResultCallback() {

                    @Override
                    public void onFinish(Object o, int code) {
                        books= (ArrayList<Book>) o; mHandler.sendMessage(mHandler.obtainMessage(2));

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });    }

            @Override
            public void onError(Exception e) {

            }
        });

    }
    class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{//RecyclerView控件的适配器
        Item_OnClickListener item_onClickListener;
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//创建
            View view= LayoutInflater.from(getContext()).inflate(R.layout.item_view,parent,false);//视图搜索框
            return new myhoder(view, item_onClickListener);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {//对每个子项绑定数据，建立视图与数据的联系，给ViewHolder里面的控件赋值
            myhoder myhoder= (myhoder) holder;
           Item item=items.get(position);
           myhoder.count.setText(item.getName());
           if(item.getIsselect()==0){
               myhoder.count.setTextColor(getResources().getColor(R.color.black));
           }else {
               myhoder.count.setTextColor(getResources().getColor(R.color.origin));
           }
        }



        public void setItem_onClickListener(Item_OnClickListener item_onClickListener) {
            this.item_onClickListener = item_onClickListener;
        }//回调把接口传递进来

        @Override
        public int getItemCount() {//多少行
            return items.size();
        }
        class  myhoder extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnLongClickListener{//继承重写构造函数
            Item_OnClickListener item_onClickListener;
            TextView count;
            @Override
            public boolean onLongClick(View v) {
                item_onClickListener.OnLongLitemLister(this,getPosition());
                return false;
            }

            @Override
            public void onClick(View v) {//点击事件
                item_onClickListener.OnLitemLister(this,getPosition());
            }

            public myhoder(View itemView, Item_OnClickListener item_onClickListener) {
                super(itemView);
                this.item_onClickListener = item_onClickListener;
                itemView.setOnClickListener(this);
                count=itemView.findViewById(R.id.name);
            }
        }
    }
    class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{//RecyclerView控件的适配器
        Item_OnClickListener item_onClickListener;

        @Override
        public int getItemViewType(int position) {
            if(books.get(position).getImgUrl()==null||books.get(position).getImgUrl().equals(""))
            return 0;
            else return 1;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//创建
            if(viewType==0) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.book_view, parent, false);//视图搜索框
                return new myhoder(view, item_onClickListener);
            }else {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.listview_search_book_item, parent, false);//视图搜索框
                return new myhoder2(view, item_onClickListener);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {//对每个子项绑定数据，建立视图与数据的联系，给ViewHolder里面的控件赋值
          if(holder instanceof myhoder ) {
              myhoder myhoder = (myhoder) holder;
              Book book = books.get(position);
              if(book.getType()==null||book.getType().equals("")){
                  myhoder.classs.setText(MyType);book.setType(MyType);
              }else
              myhoder.classs.setText(book.getType());
              myhoder.name.setText(book.getName());
          }else {
              myhoder2 myhoder2 = (myhoder2) holder;
              Book book = books.get(position);
              if (StringHelper.isEmpty(book.getImgUrl())){
                  book.setImgUrl("");
              }  if (!MyType.equals("首页")&&!book.getImgUrl().contains("http")){
                  book.setImgUrl(nameSpace_tianlai+"/"+book.getImgUrl());
              }
              Glide.with(getContext())
                      .load(book.getImgUrl())
//                .override(DipPxUtil.dip2px(getContext(), 80), DipPxUtil.dip2px(getContext(), 150))
                      .error(R.mipmap.no_image)
                      .placeholder(R.mipmap.no_image)
                      .into(myhoder2.ivBookImg);
              myhoder2.tvBookName.setText(book.getName());
              myhoder2.tvDesc.setText(book.getDesc());
              myhoder2.tvAuthor.setText(book.getAuthor());
              myhoder2.tvType.setText(book.getType());
          }
        }



        public void setItem_onClickListener(Item_OnClickListener item_onClickListener) {
            this.item_onClickListener = item_onClickListener;
        }//回调把接口传递进来

        @Override
        public int getItemCount() {//多少行
            return books.size();
        }
        class  myhoder extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnLongClickListener{//继承重写构造函数
            Item_OnClickListener item_onClickListener;
            TextView classs,name;
            @Override
            public boolean onLongClick(View v) {
                item_onClickListener.OnLongLitemLister(this,getPosition());
                return false;
            }

            @Override
            public void onClick(View v) {//点击事件
                item_onClickListener.OnLitemLister(this,getPosition());
            }

            public myhoder(View itemView, Item_OnClickListener item_onClickListener) {
                super(itemView);
                this.item_onClickListener = item_onClickListener;
                itemView.setOnClickListener(this);
                classs=itemView.findViewById(R.id.classs);
                name=itemView.findViewById(R.id.name);
            }
        }
        class  myhoder2 extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnLongClickListener{//继承重写构造函数
            Item_OnClickListener item_onClickListener;
            ImageView ivBookImg;
            TextView tvBookName;
            TextView tvDesc;
            TextView tvAuthor;
            TextView tvType;
            @Override
            public boolean onLongClick(View v) {
                item_onClickListener.OnLongLitemLister(this,getPosition());
                return false;
            }

            @Override
            public void onClick(View v) {//点击事件
                item_onClickListener.OnLitemLister(this,getPosition());
            }

            public myhoder2(View itemView, Item_OnClickListener item_onClickListener) {
                super(itemView);
                this.item_onClickListener = item_onClickListener;
                itemView.setOnClickListener(this);
                ivBookImg = (ImageView) itemView.findViewById(R.id.iv_book_img);
                tvBookName = (TextView) itemView.findViewById(R.id.tv_book_name);
                tvAuthor = (TextView) itemView.findViewById(R.id.tv_book_author);
               tvDesc = (TextView) itemView.findViewById(R.id.tv_book_desc);
                tvType = (TextView) itemView.findViewById(R.id.tv_book_type);
            }
        }
    }
}
