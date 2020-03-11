package com.example.read_app.ui.home.bbs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.example.read_app.R;
import com.example.read_app.base.BaseActivity;
import com.example.read_app.callback.OnClicktitem;
import com.example.read_app.entity.DongTai;
import com.example.read_app.entity.Imgs;
import com.example.read_app.greendao.entity.Book;
import com.example.read_app.source.DemoDBManager;
import com.example.read_app.ui.home.Select_BookS_Activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FabuDongTai_Activity extends BaseActivity {
    ArrayList<String> imgs=new ArrayList<>();
    ArrayList<String> booksid=new ArrayList<>();
    private String groupId;
    RecyclerView recyclerView;
    EditText editText;
    ArrayList<Book> books=new ArrayList<>();
    MyRecycleViewClassAdapterImg myRecycleViewClassAdapterImg;
   public static final int REQUEST_CODE_SELECT_FILE=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabu_dong_tai_);
        imgs.add("0");
        recyclerView=findViewById(R.id.recycle_img);
        editText=findViewById(R.id.edittext);
        myRecycleViewClassAdapterImg =new MyRecycleViewClassAdapterImg();
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(myRecycleViewClassAdapterImg);
        myRecycleViewClassAdapterImg.setOnClicktitem(new OnClicktitem() {
            @Override
            public void OnClick(View.OnClickListener onClickListener, int k) {
                if(imgs.get(k).equals("0")&&imgs.size()<7){
                       Intent i=new Intent(FabuDongTai_Activity.this, Select_BookS_Activity.class);
                       startActivityForResult(i,REQUEST_CODE_SELECT_FILE);
                }
            }

            @Override
            public void OnLongClick(View.OnLongClickListener onLongClickListener, int k) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == REQUEST_CODE_SELECT_FILE){
                imgs.remove(imgs.size()-1);
                //获取选择器返回的数据\
                Book  books= (Book) data.getSerializableExtra("books");
                imgs.add(books.getImgUrl());
                booksid.add(books.getId());
                imgs.add("0");
                Log.e("sdsd",books.getName());
                myRecycleViewClassAdapterImg.notifyDataSetChanged();
                    }
    }
    private void addLiuYan(final String msg) {
        SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        String names=sharedPreferences.getString("name","阅读");
        String headurl= sharedPreferences.getString("url","");
        SimpleDateFormat format=new SimpleDateFormat("MM-dd HH:mm");
        final String datas=format.format(new Date());
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                String id=System.currentTimeMillis()+"";
                for(int i=0;i<imgs.size()-1;i++){
                    Imgs imgss=new Imgs();
                    imgss.setIMGS_COLUMN_NAME_OUTID(id);
                    imgss.setBOOKS_ID(booksid.get(i));
                    imgss.setIMGS_COLUMN_NAME_IMGURL(imgs.get(i));
                    DemoDBManager.getInstance().addImgs(imgss);
                }
                DongTai album=new DongTai();
                album.setDONGTAI_COLUMN_MY_ID(id);
                album.setDONGTAI_COLUMN_NAME_ID(groupId);
                album.setDONGTAI_COLUMN_NAME_TIMES(datas);
                album.setDONGTAI_COLUMN_NAME_HEAD(headurl);
                album.setDONGTAI_COLUMN_NAME_NAME(names);
                album.setDONGTAI_COLUMN_NAME_MSG(msg);
                DemoDBManager.getInstance().addDongTai(album);
                return null;
            }
        }.execute();
    }

    public void back(View view) {
        finish();
    }

    public void save(View view) {
        String msg=editText.getText().toString().trim();
        addLiuYan(msg);
    }
    //适配器
    class  MyRecycleViewClassAdapterImg extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        OnClicktitem onClicktitem;
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(FabuDongTai_Activity.this).inflate(R.layout.img_sing_view,parent,false);
            return new FabuDongTai_Activity.MyRecycleViewClassAdapterImg.myViewHolderClassImg(view,onClicktitem);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            String devices=imgs.get(position);
            FabuDongTai_Activity.MyRecycleViewClassAdapterImg.myViewHolderClassImg myViewHolderClass= (FabuDongTai_Activity.MyRecycleViewClassAdapterImg.myViewHolderClassImg) holder;
          if(devices.equals("0")){
              Glide.with(getBaseContext()).load(R.drawable.addimg).into(myViewHolderClass.imgss);
          }else
            Glide.with(getBaseContext()).load(devices).into(myViewHolderClass.imgss);

        }

        @Override
        public int getItemCount() {
            return imgs.size();
        }

        public void setOnClicktitem(OnClicktitem onClicktitem) {
            this.onClicktitem = onClicktitem;
        }

        class  myViewHolderClassImg extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView imgss;
            OnClicktitem onClicktitem;
            @Override
            public void onClick(View v) {
                onClicktitem.OnClick(this,getPosition());
            }

            public myViewHolderClassImg(View itemView, OnClicktitem onClicktitem) {
                super(itemView);
                imgss=itemView.findViewById(R.id.imgsing);
                this.onClicktitem=onClicktitem;
                itemView.setOnClickListener(this);
            }
        }
    }
}
