package com.example.read_app.ui.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.read_app.R;
import com.example.read_app.callback.Item_OnClickListener;
import com.example.read_app.greendao.entity.Book;
import com.example.read_app.greendao.service.BookService;
import com.example.read_app.ui.home.bookstore.BookStoreFragment;
import com.example.read_app.util.StringHelper;

import java.util.ArrayList;

import static com.example.read_app.common.URLCONST.nameSpace_tianlai;
import static com.example.read_app.ui.home.bbs.FabuDongTai_Activity.REQUEST_CODE_SELECT_FILE;

public class Select_BookS_Activity extends AppCompatActivity {
BookService mBookService;
ArrayList<Book> mBooks=new ArrayList<>();
ArrayList<Integer> biaos=new ArrayList<Integer>();
RecyclerView recycle_Lingrtr;
    BooksAdapter booksAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__book_s_);
        recycle_Lingrtr=findViewById(R.id.recycle_Lingrtr);
        mBookService = new BookService();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBooks.addAll(mBookService.getAllBooks());
        for (int i=0;i<mBooks.size();i++){
            biaos.add(0);
        }
        booksAdapter =new BooksAdapter();
        recycle_Lingrtr.setAdapter(booksAdapter);
        recycle_Lingrtr.setLayoutManager(new LinearLayoutManager(Select_BookS_Activity.this));
        booksAdapter.setItem_onClickListener(new Item_OnClickListener() {
            @Override
            public void OnLitemLister(View.OnClickListener onClickListener, int postion) {

            }

            @Override
            public void OnLongLitemLister(View.OnLongClickListener onLongClickListener, int postion) {

            }
        });
    }

    public void back(View view) {
    }

    public void save(View view) {
        Book book=null;
        for (int i=0;i<biaos.size();i++){
           if(biaos.get(i)==1){
               book=mBooks.get(i);
           }
        }
        Intent i = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("books",book);
        setResult(REQUEST_CODE_SELECT_FILE, i.putExtras(bundle));

//            Intent i = new Intent();
//            i.putExtra("result", text1.getText().toString());
//            setResult(3, i);
        finish();
    }
    class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{//RecyclerView控件的适配器
        Item_OnClickListener item_onClickListener;

        @Override
        public int getItemViewType(int position) {
            if(mBooks.get(position).getImgUrl()==null||mBooks.get(position).getImgUrl().equals(""))
                return 0;
            else return 1;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//创建
            if(viewType==0) {
                View view = LayoutInflater.from(Select_BookS_Activity.this).inflate(R.layout.book_view, parent, false);//视图搜索框
                return new BooksAdapter.myhoder(view, item_onClickListener);
            }else {
                View view = LayoutInflater.from(Select_BookS_Activity.this).inflate(R.layout.listview_search_book_select_item, parent, false);//视图搜索框
                return new BooksAdapter.myhoder2(view, item_onClickListener);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {//对每个子项绑定数据，建立视图与数据的联系，给ViewHolder里面的控件赋值
            if(holder instanceof BooksAdapter.myhoder) {
                BooksAdapter.myhoder myhoder = (BooksAdapter.myhoder) holder;
                Book book = mBooks.get(position);
                if(book.getType()==null||book.getType().equals("")){

                }else
                    myhoder.classs.setText(book.getType());
                myhoder.name.setText(book.getName());
            }else {
                BooksAdapter.myhoder2 myhoder2 = (BooksAdapter.myhoder2) holder;
                Book book = mBooks.get(position);
                if (StringHelper.isEmpty(book.getImgUrl())){
                    book.setImgUrl("");
                }
                if(biaos.get(position)==0){
                    myhoder2.radioButton.setOnCheckedChangeListener(null);
                    myhoder2.radioButton.setChecked(false);
                }else {
                    myhoder2.radioButton.setOnCheckedChangeListener(null);
                    myhoder2.radioButton.setChecked(true);
                }
                myhoder2.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        for (int i=0;i<biaos.size();i++){
                            biaos.set(i,0);
                        }
                        if(isChecked){
                            biaos.set(position,1);
                        }
                        booksAdapter.notifyDataSetChanged();
                    }
                });
                Glide.with(Select_BookS_Activity.this)
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
            return mBooks.size();
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
            RadioButton radioButton;
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
                radioButton=itemView.findViewById(R.id.selectradur);
                ivBookImg = (ImageView) itemView.findViewById(R.id.iv_book_img);
                tvBookName = (TextView) itemView.findViewById(R.id.tv_book_name);
                tvAuthor = (TextView) itemView.findViewById(R.id.tv_book_author);
                tvDesc = (TextView) itemView.findViewById(R.id.tv_book_desc);
                tvType = (TextView) itemView.findViewById(R.id.tv_book_type);
            }
        }
    }
}
