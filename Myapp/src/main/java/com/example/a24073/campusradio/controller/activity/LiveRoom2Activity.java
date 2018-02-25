package com.example.a24073.campusradio.controller.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.anbetter.danmuku.DanMuView;
import com.anbetter.danmuku.model.DanMuModel;
import com.anbetter.danmuku.model.utils.DimensionUtil;
import com.example.a24073.campusradio.R;
import com.freegeek.android.materialbanner.MaterialBanner;
import com.freegeek.android.materialbanner.demo.BannerData;
import com.freegeek.android.materialbanner.demo.ImageHolderView;
import com.freegeek.android.materialbanner.holder.ViewHolderCreator;
import com.freegeek.android.materialbanner.view.indicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LiveRoom2Activity extends Activity {

    private MaterialBanner materialBanner;
    DanMuView mDanMuContainerBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_room2);
        materialBanner = (MaterialBanner) findViewById(R.id.material_banner_liveroom2);
        setBanner();

        mDanMuContainerBroadcast = (DanMuView) findViewById(R.id.danmaku_container_broadcast);
        mDanMuContainerBroadcast.prepare();

        setDanmuku(this,"啦啦啦啦啦啦啦");
    }

    //——————————————————设置bsnner———————————————————————
    private void setBanner(){

        List bannerData = new ArrayList();
        BannerData bannerData1 = new BannerData();
        bannerData1.setUrl("http://a1.qpic.cn/psb?/V121cQJl1HcbCT/2VRBcsw4VGWtljqu.xttC9RR3wFVUj.6a34H5tXb*n4!/b/dBoBAAAAAAAA");
        bannerData.add(bannerData1);
        BannerData bannerData2 = new BannerData();
        bannerData2.setUrl("http://a2.qpic.cn/psb?/V121cQJl1HcbCT/LiwEQVXz8WJYMn9zxSXQrJElaF3f5r27sHekURt3GEA!/b/dB4BAAAAAAAA");
        bannerData.add(bannerData2);
        BannerData bannerData3 = new BannerData();
        bannerData3.setUrl("http://a1.qpic.cn/psb?/V121cQJl1HcbCT/EmaYItyEc2JEPmsQICUplrzS92l3jXu1ftoPC1PGObc!/b/dLEAAAAAAAAA");
        bannerData.add(bannerData3);
        BannerData bannerData4 = new BannerData();
        bannerData4.setUrl("http://a1.qpic.cn/psb?/V121cQJl1HcbCT/Y0WIgTex6W*BmSBndkLHVcM2touMjOzn8fS5jS5GKMY!/b/dBoBAAAAAAAA");
        bannerData.add(bannerData4);
        materialBanner.setPages(new ViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolderView();
            }
        },bannerData);
        //set circle indicator
        materialBanner.setIndicator(new LinePageIndicator(this));
        materialBanner.setIndicatorInside(true);
        //indicators:
        //CirclePageIndicator,IconPageIndicator,LinePageIndicator
        //Custom indicator view needs to implement com.freegeek.android.materialbanner.view.indicator.PageIndicator
    }


    private void setDanmuku(Context mContext,String text){
        DanMuModel danMuView = new DanMuModel();
        danMuView.setDisplayType(DanMuModel.RIGHT_TO_LEFT);
        danMuView.setPriority(DanMuModel.NORMAL);
        danMuView.marginLeft = DimensionUtil.dpToPx(mContext, 30);

        // 显示的文本内容
        danMuView.textSize = DimensionUtil.spToPx(mContext, 14);
        danMuView.textColor = ContextCompat.getColor(mContext, R.color.colorWhite);
        danMuView.textMarginLeft = DimensionUtil.dpToPx(mContext, 5);

        danMuView.text = text;

        // 弹幕文本背景
        danMuView.textBackground = ContextCompat.getDrawable(mContext, R.color.danmuke_bkg);
        danMuView.textBackgroundMarginLeft = DimensionUtil.dpToPx(mContext, 15);
        danMuView.textBackgroundPaddingTop = DimensionUtil.dpToPx(mContext, 3);
        danMuView.textBackgroundPaddingBottom = DimensionUtil.dpToPx(mContext, 3);
        danMuView.textBackgroundPaddingRight = DimensionUtil.dpToPx(mContext, 15);

        mDanMuContainerBroadcast.add(danMuView);
    }


    public void adddanmu(View view){

        String[] text = {"今天又长知识了","谢谢老师","666666666","很强","可以可以","昭雪昭雪","老师下次直播什么时候啊","学到了很多谢谢老师","青芒校园FM很棒哦"};
        Random rand = new Random();
        int num = rand.nextInt(9);
        setDanmuku(this,text[num]);
    }
}
