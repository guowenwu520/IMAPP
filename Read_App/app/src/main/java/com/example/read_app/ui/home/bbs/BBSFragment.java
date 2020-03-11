package com.example.read_app.ui.home.bbs;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.read_app.R;
import com.example.read_app.callback.OnClicktitem;
import com.example.read_app.common.APPCONST;
import com.example.read_app.entity.DongTai;
import com.example.read_app.entity.Imgs;
import com.example.read_app.entity.PingLun;
import com.example.read_app.entity.Zan_Data;
import com.example.read_app.greendao.service.BookService;
import com.example.read_app.source.DemoDBManager;
import com.example.read_app.ui.bookinfo.BookInfoActivity;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class BBSFragment extends Fragment {
    private int pageSize = 10;
    private int pageNum = 1;
    private boolean hasMoreData;
    private boolean isLoading;
    private RecyclerView recycler;
    private  Button fabudingtai;
    private String NOKJIAN="0";
    private BookService bookService;
    private  String name;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar loadmorePB;
    public BBSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_bbs, container, false);
        recycler =(RecyclerView)view.findViewById(R.id.recycle_DongTai);
        bookService=new BookService();
        swipeRefreshLayout = (SwipeRefreshLayout)view. findViewById(R.id.swipe_layout);
        loadmorePB = (ProgressBar) view.findViewById(R.id.pb_load_more);
        fabudingtai=view.findViewById(R.id.fabudingtai);
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("user",MODE_PRIVATE);
        name=sharedPreferences.getString("name","kiki");
        showLiuList(true);
       fabudingtai.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getContext(), FabuDongTai_Activity.class));

           }
       });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showLiuList(true);
            }
        });
        return  view;
    }
    private void showLiuList(boolean isRefresh) {
        isLoading = true;
        if(isRefresh){
            pageNum = 1;
            swipeRefreshLayout.setRefreshing(true);
            showList();
        }else{
            pageNum++;
            loadmorePB.setVisibility(View.VISIBLE);
        }
    }

    private void showList() {
        new AsyncTask<Void,Void,Void>(){
            ArrayList<DongTai> dongTais=new ArrayList<>();
            ArrayList<ArrayList<PingLun>> pingls=new ArrayList<>();
            ArrayList<ArrayList<Imgs>> imgs=new ArrayList<>();
            ArrayList<ArrayList<Zan_Data>> zandatas=new ArrayList<>();
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                swipeRefreshLayout.setRefreshing(false);
                MyRecycleViewClassAdapter myRecycleViewClassAdapter=new MyRecycleViewClassAdapter(dongTais,pingls,imgs,zandatas);
                recycler.setAdapter(myRecycleViewClassAdapter);
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                myRecycleViewClassAdapter.setOnClisterItem(new OnClicktitem() {
                    @Override
                    public void OnClick(View.OnClickListener onClickListener, int k) {

                    }

                    @Override
                    public void OnLongClick(View.OnLongClickListener onLongClickListener, final int k) {
                        //  Log.e("isE", EMClient.getInstance().getCurrentUser()+" "+dongTais.get(k).getLIUYAN_COLUMN_NAME_NAME());
                        String str="确定删除吗？";

                        if(name.equals(dongTais.get(k).DONGTAI_COLUMN_NAME_NAME)){
                            str="确定删除吗？";
                        }else{
                            str="你没有操作权限";
                        }
                        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                        builder.setTitle("提示").setMessage(str).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(name.equals(dongTais.get(k).getDONGTAI_COLUMN_NAME_NAME())) {
                                    new Thread(){
                                        @Override
                                        public void run() {
                                            super.run();
                                            DemoDBManager.getInstance().remontDongTai(dongTais.get(k).getDONGTAI_COLUMN_MY_ID());
                                            ArrayList<Imgs> imgss=imgs.get(k);
                                            ArrayList<PingLun> pingLun=pingls.get(k);
                                            ArrayList<Zan_Data> zan_data=zandatas.get(k);
                                            for (int i=0;i<imgss.size();i++){
                                                DemoDBManager.getInstance().remontImgs(dongTais.get(k).getDONGTAI_COLUMN_MY_ID());
                                            }
                                            for(int i=0;i<pingLun.size();i++){
                                                DemoDBManager.getInstance().remontPingLun(pingLun.get(i).getPINGLUN_COLUMN_MY_ID());
                                            }
                                            for(int i=0;i<zan_data.size();i++){
                                                DemoDBManager.getInstance().remontZanData(zan_data.get(i).getZAN_COLUMN_MY_ID());
                                            }
                                        }
                                    }.start();
                                }
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    }
                });
            }

            @Override
            protected Void doInBackground(Void... voids) {
                ArrayList<DongTai> dongTais2= DemoDBManager.getInstance().getDongTai();
                for (int i=dongTais2.size()-1;i>=0;i--){
                    ArrayList<Imgs> imgss=DemoDBManager.getInstance().getImgs(dongTais2.get(i).getDONGTAI_COLUMN_MY_ID());
                    ArrayList<PingLun> pingLun=DemoDBManager.getInstance().getPingLun(dongTais2.get(i).getDONGTAI_COLUMN_MY_ID());
                    ArrayList<Zan_Data> zan_data=DemoDBManager.getInstance().getZanData(dongTais2.get(i).getDONGTAI_COLUMN_MY_ID());
                    imgs.add(imgss);
                    zandatas.add(zan_data);
                    pingls.add(pingLun);
                    dongTais.add(dongTais2.get(i));
                }
                return null;
            }
        }.execute();
    }
    class  MyRecycleViewClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        OnClicktitem onClisterItem;
        boolean[] fing;
        boolean[] fign;
        ArrayList<DongTai> dongTais=new ArrayList<>();
        ArrayList<ArrayList<PingLun>> pingls=new ArrayList<>();
        ArrayList<ArrayList<Imgs>> imgs=new ArrayList<>();
        ArrayList<ArrayList<Zan_Data>> zandatas=new ArrayList<>();

        public MyRecycleViewClassAdapter( ArrayList<DongTai> dongTais,ArrayList<ArrayList<PingLun>> pingLuns,ArrayList<ArrayList<Imgs>> imgs,ArrayList<ArrayList<Zan_Data>> zandatas) {
            this.dongTais=dongTais;
            this.imgs=imgs;
            this.pingls=pingLuns;
            this.zandatas=zandatas;
            fign=new boolean[dongTais.size()+1];
            fing=new boolean[dongTais.size()+1];
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getContext()).inflate(R.layout.dongtai_view_item,parent,false);
            return new MyRecycleViewClassAdapter.myViewHolderClass(view,onClisterItem);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            fign[position]=false;
            DongTai devices=dongTais.get(position);
            final MyRecycleViewClassAdapter.myViewHolderClass myViewHolderClass= (MyRecycleViewClassAdapter.myViewHolderClass) holder;
            myViewHolderClass.times.setText(devices.getDONGTAI_COLUMN_NAME_TIMES());
            myViewHolderClass.name.setText(devices.getDONGTAI_COLUMN_NAME_NAME());
            myViewHolderClass.TextMsg.setText(devices.getDONGTAI_COLUMN_NAME_MSG());
            ArrayList<Imgs> imgs1=imgs.get(position);
            if(devices.getDONGTAI_COLUMN_NAME_HEAD().equals("")){
                Glide.with(getContext()).load(R.mipmap.avatar).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myViewHolderClass.imghead);
            }else
            Glide.with(getContext()).load(devices.getDONGTAI_COLUMN_NAME_HEAD()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myViewHolderClass.imghead);
            MyRecycleViewClassAdapterImg myRecycleViewClassAdapterImg = new MyRecycleViewClassAdapterImg(imgs1);
            myViewHolderClass.recyclerView_img.setAdapter(myRecycleViewClassAdapterImg);
            myViewHolderClass.recyclerView_img.setLayoutManager(new GridLayoutManager(getContext(), 3));
            if(imgs1.size()>0) {
                myViewHolderClass.recyclerView_img.setVisibility(View.VISIBLE);
            }else{
                myViewHolderClass.recyclerView_img.setVisibility(View.GONE);
            }
            final ArrayList<PingLun> pingLuns=pingls.get(position);
            final MyRecycleViewClassAdapterPingLun myRecycleViewClassAdapterPingLun = new MyRecycleViewClassAdapterPingLun(pingLuns);
            myViewHolderClass.recyclerView_pinglun.setAdapter(myRecycleViewClassAdapterPingLun);
            myViewHolderClass.recyclerView_pinglun.setLayoutManager(new LinearLayoutManager(getContext()));
            myRecycleViewClassAdapterPingLun.setOnClisterItem(new OnClicktitem() {
                @Override
                public void OnClick(View.OnClickListener onClickListener, final int k) {
                    View commentEditText=LayoutInflater.from(getContext()).inflate(R.layout.edit_button_input,null,false);
                    String str="说点什么。。";
                    final EditText editText=commentEditText.findViewById(R.id.textmsfg);
                    Button send=commentEditText.findViewById(R.id.send);
                    if(pingLuns.get(k).getPINGLUN_COLUMN_NAME_NAMEED().equals(NOKJIAN)){
                        fing[position]=false;
                        str="回复 "+pingLuns.get(k).getPINGLUN_COLUMN_NAME_NAME();
                        editText.setHint(str);
                    }else{
                        fing[position]=true;
                        str="回复 "+pingLuns.get(k).getPINGLUN_COLUMN_NAME_NAMEED();
                        editText.setHint(str);

                    }
                    commentEditText.setFocusable(true);
                    commentEditText.setFocusableInTouchMode(true);
                    commentEditText.requestFocus();
                    //打开软键盘

                    AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                    builder.setView(commentEditText);

                    final AlertDialog alertDialog= builder.show();
                    InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(commentEditText, 0);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String mesg=editText.getText().toString().trim();
                            if(!mesg.equals("")) {
                                final PingLun pingLun = new PingLun();
                                pingLun.setPINGLUN_COLUMN_MY_ID(System.currentTimeMillis() + "");
                                pingLun.setPINGLUN_COLUMN_DONGTAI_ID(dongTais.get(position).getDONGTAI_COLUMN_MY_ID());
                                pingLun.setPINGLUN_COLUMN_NAME_MSG(mesg);
                                if (fing[position]) {
                                    pingLun.setPINGLUN_COLUMN_NAME_NAME(pingLuns.get(k).getPINGLUN_COLUMN_NAME_NAMEED());
                                    pingLun.setPINGLUN_COLUMN_NAME_NAMEED(name);
                                }else{
                                    pingLun.setPINGLUN_COLUMN_NAME_NAME(pingLuns.get(k).getPINGLUN_COLUMN_NAME_NAME());
                                    pingLun.setPINGLUN_COLUMN_NAME_NAMEED(name);
                                }
                                new AsyncTask<Void,Void,Void>(){
                                    @Override
                                    protected void onPostExecute(Void aVoid) {
                                        super.onPostExecute(aVoid);
                                        alertDialog.dismiss();
                                        pingLuns.add(pingLun);
                                        myRecycleViewClassAdapterPingLun.notifyDataSetChanged();
                                    }

                                    @Override
                                    protected Void doInBackground(Void... voids) {
                                        DemoDBManager.getInstance().addPingLun(pingLun);

                                        return null;
                                    }
                                }.execute();
                            }
                        }
                    });

                }

                @Override
                public void OnLongClick(View.OnLongClickListener onLongClickListener, int k) {

                }
            });
            myViewHolderClass.inputed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View commentEditText = LayoutInflater.from(getContext()).inflate(R.layout.edit_button_input, null, false);
                    final EditText editText = commentEditText.findViewById(R.id.textmsfg);
                    Button send = commentEditText.findViewById(R.id.send);
                    commentEditText.setFocusable(true);
                    commentEditText.setFocusableInTouchMode(true);
                    commentEditText.requestFocus();
                    //打开软键盘
                    AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                    builder.setView(commentEditText);
                    final AlertDialog alertDialog=  builder.show();

                    InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(commentEditText, 0);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String mesg = editText.getText().toString().trim();
                            if (!mesg.equals("")) {
                                final PingLun pingLun = new PingLun();
                                pingLun.setPINGLUN_COLUMN_MY_ID(System.currentTimeMillis() + "");
                                pingLun.setPINGLUN_COLUMN_DONGTAI_ID(dongTais.get(position).getDONGTAI_COLUMN_MY_ID());
                                pingLun.setPINGLUN_COLUMN_NAME_MSG(mesg);
                                pingLun.setPINGLUN_COLUMN_NAME_NAME(name);
                                pingLun.setPINGLUN_COLUMN_NAME_NAMEED(NOKJIAN);

                                new AsyncTask<Void, Void, Void>() {
                                    @Override
                                    protected void onPostExecute(Void aVoid) {
                                        super.onPostExecute(aVoid);
                                        alertDialog.dismiss();
                                        pingLuns.add(pingLun);
                                        myRecycleViewClassAdapterPingLun.notifyDataSetChanged();
                                    }

                                    @Override
                                    protected Void doInBackground(Void... voids) {
                                     DemoDBManager.getInstance().addPingLun(pingLun);

                                        return null;
                                    }
                                }.execute();
                            }
                        }
                    });
                }
            });
            if(pingLuns.size()>0) {
                myViewHolderClass.recyclerView_pinglun.setVisibility(View.VISIBLE);

            }
            else {
                myViewHolderClass.recyclerView_pinglun.setVisibility(View.GONE);
            }
            String namers="";
            final ArrayList<Zan_Data> data=zandatas.get(position);
            if(data.size()>0){
                myViewHolderClass.dianzanshuow.setVisibility(View.VISIBLE);
            }else {
                myViewHolderClass.dianzanshuow.setVisibility(View.GONE);
            }
            for (int i=data.size()-1;i>=0;i--){
                namers+=data.get(i).getZAN_COLUMN_NAME_NAME()+",";
                if(data.get(i).getZAN_COLUMN_NAME_NAME().equals(name)){
                    fign[position]=true;
                }else{
                    fign[position]=false;
                }
            }

            myViewHolderClass.dianzanshuow.setText(namers);
            if( fign[position]){
                myViewHolderClass.dianzan.setImageDrawable(getResources().getDrawable(R.drawable.dianzaned));
            }else{
                myViewHolderClass.dianzan.setImageDrawable(getResources().getDrawable(R.drawable.dianzan));
            }
            myViewHolderClass.dianzan.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    final ArrayList<Zan_Data> datas=new ArrayList<>();
                    new AsyncTask<Void,Void,Void>(){

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            String str="";
                            for (int i=datas.size()-1;i>0;i--){
                                str+=datas.get(i).getZAN_COLUMN_NAME_NAME()+",";
                            }
                            myViewHolderClass.dianzanshuow.setText(str);
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {
                            if( fign[position]){
                                fign[position]=false;
                                for (int i=data.size()-1;i>0;i--){
                                    if(!data.get(i).getZAN_COLUMN_NAME_NAME().equals(name)){
                                        datas.add(data.get(i));
                                    }
                                }
                                myViewHolderClass.dianzan.setImageDrawable(getResources().getDrawable(R.drawable.dianzan));
                                DemoDBManager.getInstance().remontZan(name,dongTais.get(position).getDONGTAI_COLUMN_MY_ID());
                            }else{
                                for (int i=data.size()-1;i>0;i--){
                                    datas.add(data.get(i));
                                }
                                fign[position]=true;
                                Zan_Data zan_data=new Zan_Data();
                                zan_data.setZAN_COLUMN_MY_ID(System.currentTimeMillis()+"");
                                zan_data.setZAN_COLUMN_DONGTAI_ID(dongTais.get(position).getDONGTAI_COLUMN_MY_ID());
                                zan_data.setZAN_COLUMN_NAME_NAME(name);
                                datas.add(zan_data);
                                DemoDBManager.getInstance().addDianZan(zan_data);
                                myViewHolderClass.dianzan.setImageDrawable(getResources().getDrawable(R.drawable.dianzaned));
                            }
                            return null;
                        }
                    }.execute();

                }
            });


        }

        @Override
        public int getItemCount() {
            return dongTais.size();
        }

        public void setOnClisterItem(OnClicktitem onClisterItem) {
            this.onClisterItem = onClisterItem;
        }

        class  myViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
            OnClicktitem onClisterItem;
            TextView name,times;
            ImageView imghead;
            TextView TextMsg;
            RelativeLayout tp;
            ImageView inputed,dianzan;
            TextView dianzanshuow;
            RecyclerView recyclerView_img;
            RecyclerView recyclerView_pinglun;
            @Override
            public void onClick(View v) {
                onClisterItem.OnClick(this,getPosition());
            }

            @Override
            public boolean onLongClick(View v) {
                onClisterItem.OnLongClick(this,getPosition());
                return false;
            }

            public myViewHolderClass(View itemView, OnClicktitem onClisterItem) {
                super(itemView);
                this.onClisterItem=onClisterItem;
                tp=itemView.findViewById(R.id.tp);
                tp.setOnLongClickListener(this);
                dianzan=itemView.findViewById(R.id.dianzan);
                dianzanshuow=itemView.findViewById(R.id.dianzanshow);
                inputed=itemView.findViewById(R.id.inputedit);
                imghead=itemView.findViewById(R.id.head_user);
                name=itemView.findViewById(R.id.user_name);
                times=itemView.findViewById(R.id.times);
                TextMsg=itemView.findViewById(R.id.textmsg);
                recyclerView_img=itemView.findViewById(R.id.recyclke_img);
                recyclerView_pinglun=itemView.findViewById(R.id.recyclke_plung);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        showLiuList(true);
    }
    //适配器
    class  MyRecycleViewClassAdapterImg extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        ArrayList<Imgs> imgs=new ArrayList<>();

        public MyRecycleViewClassAdapterImg(ArrayList<Imgs> imgs) {
            this.imgs=imgs;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getContext()).inflate(R.layout.img_sing_view,parent,false);
            return new MyRecycleViewClassAdapterImg.myViewHolderClassImg(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Imgs devices=imgs.get(position);
            MyRecycleViewClassAdapterImg.myViewHolderClassImg myViewHolderClass= (MyRecycleViewClassAdapterImg.myViewHolderClassImg) holder;
            Glide.with(getContext()).load(devices.getIMGS_COLUMN_NAME_IMGURL()).into(myViewHolderClass.imgss);
            myViewHolderClass.imgss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), BookInfoActivity.class);
                    intent.putExtra(APPCONST.BOOK, bookService.getBookById(devices.BOOKS_ID));
                    getContext().startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return imgs.size();
        }



        class  myViewHolderClassImg extends RecyclerView.ViewHolder {
            ImageView imgss;


            public myViewHolderClassImg(View itemView) {
                super(itemView);
                imgss=itemView.findViewById(R.id.imgsing);
            }
        }
    }
    class  MyRecycleViewClassAdapterPingLun extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        OnClicktitem onClisterItem;
        ArrayList<PingLun> pingls=new ArrayList<>();

        public MyRecycleViewClassAdapterPingLun( ArrayList<PingLun> pingLuns) {

            this.pingls=pingLuns;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getContext()).inflate(R.layout.ping_lun_view_item,parent,false);
            return new myViewHolderClass(view,onClisterItem);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            PingLun pingLun=pingls.get(position);
            myViewHolderClass myViewHolderClass= (MyRecycleViewClassAdapterPingLun.myViewHolderClass) holder;
            if(pingLun.getPINGLUN_COLUMN_NAME_NAMEED().equals(NOKJIAN)){
                myViewHolderClass.name_name.setText(pingLun.getPINGLUN_COLUMN_NAME_NAME()+" : ");
                myViewHolderClass.isadleb.setVisibility(View.GONE);
            }else{
                myViewHolderClass.name_name.setText(pingLun.getPINGLUN_COLUMN_NAME_NAME()+" : ");
                myViewHolderClass.isadleb.setVisibility(View.VISIBLE);
                myViewHolderClass.nameed_name.setText(pingLun.getPINGLUN_COLUMN_NAME_NAMEED());
            }
            myViewHolderClass.textmsg.setText(pingLun.getPINGLUN_COLUMN_NAME_MSG());
        }

        @Override
        public int getItemCount() {
            return pingls.size();
        }

        public void setOnClisterItem(OnClicktitem onClisterItem) {
            this.onClisterItem = onClisterItem;
        }

        class  myViewHolderClass extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
            OnClicktitem onClisterItem;
            TextView nameed_name,name_name,textmsg;
            LinearLayout isadleb;
            @Override
            public void onClick(View v) {
                onClisterItem.OnClick(this,getPosition());
            }

            @Override
            public boolean onLongClick(View v) {
                onClisterItem.OnLongClick(this,getPosition());
                return false;
            }

            public myViewHolderClass(View itemView, OnClicktitem onClisterItem) {
                super(itemView);
                this.onClisterItem=onClisterItem;
                itemView.setOnClickListener(this);
                name_name=itemView.findViewById(R.id.name_name);
                nameed_name=itemView.findViewById(R.id.nameed_name);
                textmsg=itemView.findViewById(R.id.textmesg);
                isadleb=itemView.findViewById(R.id.isadlesee);
            }
        }
    }
}
