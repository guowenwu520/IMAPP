package com.example.read_app.ui.home;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.example.read_app.R;
import com.example.read_app.application.MyApplication;
import com.example.read_app.base.BaseActivity;
import com.example.read_app.common.APPCONST;
import com.example.read_app.custom.CircleImageView;
import com.example.read_app.entity.User;
import com.example.read_app.source.DemoDBManager;
import com.example.read_app.util.TextHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;


public class MainActivity extends BaseActivity {


    @BindView(R.id.civ_avatar2)
    CircleImageView civAvatar2;
    @BindView(R.id.civ_avatar)
    CircleImageView civAvatar;
    @BindView(R.id.tl_tab_menu)
    TabLayout tlTabMenu;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rl_common_title)
    RelativeLayout rlCommonTitle;
    @BindView(R.id.tv_edit_finish)
    TextView tvEditFinish;
    @BindView(R.id.name)
    TextView username;
    @BindView(R.id.rl_edit_titile)
    RelativeLayout rlEditTitile;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    private MainPrensenter mMainPrensenter;

    private  String name,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setStatusBar(R.color.sys_line);
        SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        name=sharedPreferences.getString("name","kiki");
        url=sharedPreferences.getString("url","kiki");
        if(!url.equals("kiki")&&!url.equals("")){
            Glide.with(this).load(url).into(civAvatar);
            Glide.with(this).load(url).into(civAvatar2);
        }
        if(!name.equals("kiki")){
            username.setText(name);
        }else {
            username.setText("无心");
        }
        civAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        mMainPrensenter = new MainPrensenter(this);
        mMainPrensenter.start();

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - APPCONST.exitTime > APPCONST.exitConfirmTime) {
            TextHelper.showText("再按一次退出");
            APPCONST.exitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MyApplication.checkVersionByServer(this);
    }


    public CircleImageView getCivAvatar() {
        return civAvatar;
    }

    public TabLayout getTlTabMenu() {
        return tlTabMenu;
    }

    public ImageView getIvSearch() {
        return ivSearch;
    }

    public ViewPager getVpContent() {
        return vpContent;
    }

    public RelativeLayout getRlCommonTitle() {
        return rlCommonTitle;
    }

    public TextView getTvEditFinish() {
        return tvEditFinish;
    }

    public RelativeLayout getRlEditTitile() {
        return rlEditTitile;
    }

    public void TuiChu(View view) {
        Log.e("sdsd","sdsd");
         Intent intent=new Intent(MainActivity.this,LoginActivity.class);
         intent.putExtra("fign",1);
        startActivity(intent);
        finish();
    }

    public void Update_Pass(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LinearLayout linearLayout=new LinearLayout(this);
        EditText editText=new EditText(this);
        EditText editText1=new EditText(this);
        editText.setHint("输入新密码");
        editText1.setHint("确认新密码");
        editText.setInputType(TYPE_TEXT_VARIATION_PASSWORD);
        editText1.setInputType(TYPE_TEXT_VARIATION_PASSWORD);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(editText);
        linearLayout.addView(editText1);
        builder.setTitle("修改密码").setView(linearLayout).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String pass=editText.getText().toString().trim();
                String pass2=editText1.getText().toString().trim();
                if(pass.equals(pass2)&&!pass.equals("")){
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            User user=new User();
                            user.setName(name);
                            user.setPass(pass);
                            DemoDBManager.getInstance().updatePass(user);
                        }
                    }.start();
                    Toast.makeText(MainActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else if(pass.equals("")||pass2.equals("")){
                    Toast.makeText(MainActivity.this,"请输入",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                }

            }
        }).show();
    }

    public void Update_Head(View view) {
//单选并剪裁
        ImageSelector.builder()
                .useCamera(true) // 设置是否使用拍照
                .setCrop(true)  // 设置是否使用图片剪切功能。
                .setCropRatio(1.0f) // 图片剪切的宽高比,默认1.0f。宽固定为手机屏幕的宽。
                .setSingle(true)  //设置是否单选
                .canPreview(true) //是否可以预览图片，默认为true
                .start(this, REQUEST_CODE); // 打开相册
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelector.SELECT_RESULT);
            Glide.with(this).load(images.get(0)).into(civAvatar);
            Glide.with(this).load(images.get(0)).into(civAvatar2);
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    User user=new User();
                    user.setName(name);
                    user.setUrlimg(images.get(0));
                    DemoDBManager.getInstance().updateUser(user);
                    SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("url",images.get(0));
                    editor.commit();
                }
            }.start();
            /**
             * 是否是来自于相机拍照的图片，
             * 只有本次调用相机拍出来的照片，返回时才为true。
             * 当为true时，图片返回的结果有且只有一张图片。
             */
            boolean isCameraImage = data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false);
        }
    }
}
