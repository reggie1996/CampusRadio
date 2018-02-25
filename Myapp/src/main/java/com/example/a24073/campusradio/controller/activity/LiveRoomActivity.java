package com.example.a24073.campusradio.controller.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.opengl.GLSurfaceView;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.brucetoo.gradienttabstrip.PagerSlidingTabStrip;
import com.cunoraz.gifview.library.GifView;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.adapter.LiveRoomPagerAdapter;
import com.example.a24073.campusradio.controller.adapter.ViewPageAdapter;
import com.example.a24073.campusradio.utils.LiveRoomConversationEvent;
import com.freegeek.android.materialbanner.MaterialBanner;
import com.freegeek.android.materialbanner.demo.BannerData;
import com.freegeek.android.materialbanner.demo.DepthPageTransformer;
import com.freegeek.android.materialbanner.demo.ImageHolderView;
import com.freegeek.android.materialbanner.holder.ViewHolderCreator;
import com.freegeek.android.materialbanner.view.indicator.CirclePageIndicator;
import com.freegeek.android.materialbanner.view.indicator.LinePageIndicator;

import android.support.v4.app.FragmentManager;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import me.drakeet.materialdialog.MaterialDialog;

public class LiveRoomActivity extends FragmentActivity {

    private MaterialBanner materialBanner;
    private PagerSlidingTabStrip strip;
    private ViewPager pager;
    private LiveRoomPagerAdapter adapter;
    TextView isliving;
    TextView listenernum;
    private String[] strs = new String[]{"互动聊天","直播介绍","连线"};
    private String[] bannerUrl;

    final int TYPE_SCHOOL = 0;
    final int TYPE_INDIVIDUAL = 1;
    final int TYPE_STUDY = 2;
    final int TYPE_NOTHOME = 3;
    final int TYPE_REPLAY = 4;

    private String[] bannerSchool = {"http://imgsrc.baidu.com/forum/pic/item/70fe972bd40735faddceb83f94510fb30e240864.jpg",
                                        "http://imgsrc.baidu.com/forum/pic/item/5ce7a18b87d6277f4d3658bd22381f30e824fc72.jpg"};
    private String[] bannerIndividual = {"http://imgsrc.baidu.com/forum/pic/item/d2c2ab18972bd4070ff9257b71899e510eb30964.jpg",
                                            "http://imglf.nosdn.127.net/img/RXFpMGtmVEN0UkRYc1hEQ1NBOXIwRnZwT2dWK0h2aTVYVkg1WjhXVmtmST0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg",
                                            "http://imglf.nosdn.127.net/img/S3lvckhpUS9rZ29JV2VQNy9LeldlcTBvN2tRdExmaWNBdFdWTnhKeTc4MnRvTEkxajRQUHdRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg"};
    private String[] bannerStudy = {"http://a3.qpic.cn/psb?/V121cQJl1HcbCT/DMgtz.zGCMGuD8p9Pj7sB1p.4VVXLiNGTReKHtioH1I!/b/dAEBAAAAAAAA",
                                        "http://a1.qpic.cn/psb?/V121cQJl1HcbCT/77KhbzXOG7utRe4bklCUnLvSQcoP9z0bXeUGGvvDAIM!/b/dPkAAAAAAAAA",
                                        "http://a1.qpic.cn/psb?/V121cQJl1HcbCT/F8WGC14iuJpn.4riJWJwg0CqYH6OXIbQX5SZSe1rEC0!/b/dPkAAAAAAAAA",
                                        "http://a2.qpic.cn/psb?/V121cQJl1HcbCT/2hQ4MKxz5vv4Cio3x3ikYJkIX5Wwhsr7mcQl*tdeAyc!/b/dBgBAAAAAAAA"};
    private String[] bannerNothome = {"http://a2.qpic.cn/psb?/V121cQJl1HcbCT/0MQZI2AouwEgNcAEC84lqqn6rMURSzd6.O7GNj*xy*U!/b/dDwBAAAAAAAA"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_room);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String str=bundle.getString("str");
        final int type = Integer.parseInt(str);


        switch (type){
            case TYPE_SCHOOL:
                bannerUrl = bannerSchool;
                break;
            case TYPE_INDIVIDUAL:
                bannerUrl = bannerIndividual;
                break;
            case TYPE_STUDY:
                bannerUrl = bannerStudy;
                break;
            case TYPE_NOTHOME:
                bannerUrl = bannerNothome;
                break;
            case TYPE_REPLAY:
                bannerUrl = bannerNothome;
                break;
            default:
                bannerUrl = bannerNothome;
                break;
        }

        materialBanner = (MaterialBanner) findViewById(R.id.material_banner_liveroom);
        strip = (PagerSlidingTabStrip)findViewById(R.id.strip_liveroom);
        pager = (ViewPager)findViewById(R.id.pager_liveroom);
        isliving = (TextView) findViewById(R.id.tv_isliving);
        listenernum = (TextView) findViewById(R.id.tv_listenernum);

        adapter = new LiveRoomPagerAdapter(getSupportFragmentManager(),strs);


        setBanner();
        setPager();

        GifView gifView1 = (GifView)findViewById(R.id.gif1);
        gifView1.setVisibility(View.VISIBLE);
        gifView1.play();



        if(type == TYPE_NOTHOME){
            gifView1.pause();
            isliving.setText("未开播");
            listenernum.setText("1人");
            final MaterialDialog mMaterialDialog = new MaterialDialog(this);
            mMaterialDialog
                    .setTitle("Oooooops...")
                    .setMessage("主播不在诶...\n待会再来吧~\n你可以设置主播开播提醒\n主播上线了我们会第一时间通知你哒！！")
                    .setPositiveButton("设置开播提醒", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    })
                    .setNegativeButton("算了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });
            mMaterialDialog.show();
        }

        if(type == TYPE_REPLAY){
            gifView1.play();
            isliving.setText("正在重播...");
        }

        new Handler().postDelayed(new Runnable(){
            public void run() {
                //execute the task 
                EventBus.getDefault().post(new LiveRoomConversationEvent(type));
            }
        },500);

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    //——————————————————设置banner———————————————————————
    private void setBanner(){

        List bannerList = new ArrayList();

        BannerData[] bannerDatas = new BannerData[bannerUrl.length];

       for(int i = 0;i<bannerUrl.length;i++){
           bannerDatas[i] = new BannerData();
           bannerDatas[i].setUrl(bannerUrl[i]);
           bannerList.add(bannerDatas[i]);
       }

/*
        BannerData bannerData1 = new BannerData();
        bannerData1.setUrl("http://imglf.nosdn.127.net/img/R0t1SFdFVkJnYzhDR3o4MGZQUGlROHV5RlNjdURRQWsyREp5Y1VKZGNMVHlTOFlmYzY2N2FnPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        bannerList.add(bannerData1);
        BannerData bannerData2 = new BannerData();
        bannerData2.setUrl("http://imglf.nosdn.127.net/img/RXFpMGtmVEN0UkRYc1hEQ1NBOXIwRnZwT2dWK0h2aTVYVkg1WjhXVmtmST0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        bannerList.add(bannerData2);
        BannerData bannerData3 = new BannerData();
        bannerData3.setUrl("http://imglf.nosdn.127.net/img/S3lvckhpUS9rZ29JV2VQNy9LeldlcTBvN2tRdExmaWNBdFdWTnhKeTc4MnRvTEkxajRQUHdRPT0.jpg?imageView&thumbnail=500x0&quality=96&stripmeta=0&type=jpg");
        bannerList.add(bannerData3);
*/

        materialBanner.setPages(new ViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolderView();
            }
        },bannerList);
        //set circle indicator
        materialBanner.setIndicator(new LinePageIndicator(this));
        materialBanner.setIndicatorInside(true);
        //indicators:
        //CirclePageIndicator,IconPageIndicator,LinePageIndicator
        //Custom indicator view needs to implement com.freegeek.android.materialbanner.view.indicator.PageIndicator
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

    public void liveroom_back(View view){
        finish();
    }
    public void fullScreen(View view){
        Intent intent=new Intent();
        intent.setClass(this, LiveRoom2Activity.class);//从一个activity跳转到另一个activity  
        startActivity(intent);
    }

    public void liveshare(View view){
        startActivity(new Intent(this,QRCodeActivity.class));
    }


}
