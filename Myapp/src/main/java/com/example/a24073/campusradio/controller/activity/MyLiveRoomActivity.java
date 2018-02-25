package com.example.a24073.campusradio.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.brucetoo.gradienttabstrip.PagerSlidingTabStrip;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.adapter.LiveRoomPagerAdapter;
import com.example.a24073.campusradio.controller.adapter.MyLiveRoomPagerAdapter;
import com.freegeek.android.materialbanner.MaterialBanner;

public class MyLiveRoomActivity extends FragmentActivity {

    private MaterialBanner materialBanner;
    private PagerSlidingTabStrip strip;
    private ViewPager pager;
    private MyLiveRoomPagerAdapter adapter;
    private String[] strs = new String[]{"互动聊天","直播介绍","连线"};

    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    Bitmap bm;

    ImageView mycover;
    TextView tv_isliving;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_live_room);

        materialBanner = (MaterialBanner) findViewById(R.id.material_banner_liveroom);
        strip = (PagerSlidingTabStrip)findViewById(R.id.strip_myliveroom);
        pager = (ViewPager)findViewById(R.id.pager_myliveroom);
        adapter = new MyLiveRoomPagerAdapter(getSupportFragmentManager(),strs);
        mycover = (ImageView) findViewById(R.id.mycover);
        tv_isliving = (TextView) findViewById(R.id.tv_isliving);

        tv_isliving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_isliving.getText().equals("开启直播")){
                    tv_isliving.setText("正在启动直播...");
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                sleep(2000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_isliving.setText("正在直播中...");
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                }else{
                    tv_isliving.setText("开启直播");
                }
            }
        });



        setPager();

    }


    //-------------------------------------------设置下方的viewpager------------------------
    private void setPager(){
        //tab text color
        strip.setTextColor(Color.parseColor("#8a8a8a"));
        //tab chose text color
        strip.setTabChoseTextColor(Color.parseColor("#00645c"));
        //tab text size
        strip.setTextSize(13);
        //tab chose text size
        strip.setTabChoseTextSize(17);
        //indicator color
        strip.setIndicatorColor(Color.parseColor("#00645c"));
        //indicator height
        strip.setIndicatorHeight(4);
        //underline height
        strip.setUnderlineHeight(1);
        //expand?
        strip.setShouldExpand(true);
        //divider between tab
        strip.setDividerColor(android.R.color.transparent);

        pager.setAdapter(adapter);
        strip.setViewPager(pager);
    }

    public void myliveroom_back(View view){
        finish();
    }

    public void addcover(View view){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imaePath){
        bm = BitmapFactory.decodeFile(imaePath);
        mycover.setVisibility(View.VISIBLE);
    }

}
