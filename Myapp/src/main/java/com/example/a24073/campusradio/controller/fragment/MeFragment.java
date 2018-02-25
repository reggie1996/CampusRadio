package com.example.a24073.campusradio.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a24073.campusradio.MyResource.CircleImageView;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.base.BaseFragment;
import com.example.a24073.campusradio.controller.activity.MeOrder2Activity;
import com.example.a24073.campusradio.controller.activity.MeOrderActivity;
import com.example.a24073.campusradio.controller.activity.MomentUtilsActivity;
import com.example.a24073.campusradio.controller.activity.MyLiveRoomActivity;
import com.example.a24073.campusradio.controller.activity.QRCodeActivity;
import com.example.a24073.campusradio.controller.activity.RadioTypeActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by 24073 on 2017/6/3.
 * 我界面
 */

public class MeFragment extends BaseFragment{
    private View view;

    private View myLiveRoom;
    private View myorder;
    private View mycourse;
    private View mymoment;
    private View messages;
    private View setting;
    private View history;
    private View scan;
    private View myqingmang;


    String qrCode;

    @Override
    protected View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_me,null);
        myLiveRoom = view.findViewById(R.id.myliveroom);
        myorder = view.findViewById(R.id.myorder);
        mycourse = view.findViewById(R.id.mycourse);
        mymoment = view.findViewById(R.id.mymoment);
        messages = view.findViewById(R.id.messages);
        setting = view.findViewById(R.id.setting);
        history = view.findViewById(R.id.history);
        scan = view.findViewById(R.id.scan);
        myqingmang = view.findViewById(R.id.myqingmang);

        myLiveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的直播间
                startActivity(new Intent(getContext(), MyLiveRoomActivity.class));
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫描二维码
                startActivityForResult(new Intent(getContext(), CaptureActivity.class),0);
            }
        });

        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直播预约
                startActivity(new Intent(getContext(), MeOrder2Activity.class));
            }
        });

        mycourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的课程
                startActivity(new Intent(getContext(), MeOrderActivity.class));
            }
        });

        mymoment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的动态
                startActivity(new Intent(getContext(), MomentUtilsActivity.class));
            }
        });

        myqingmang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的青芒
                startActivity(new Intent(getContext(), MomentUtilsActivity.class));
            }
        });

        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //消息管理
                startActivity(new Intent(getContext(), MomentUtilsActivity.class));
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //历史浏览
                Intent intent = new Intent();
                intent.setClass(getContext(), RadioTypeActivity.class);
                intent.putExtra("str","历史浏览");
                startActivity(intent);
            }
        });




        return view;
    }


    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            //bundle.getString("result")为扫描结果，String类型
            qrCode = bundle.getString("result");
        }
    }

}
