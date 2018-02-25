package com.example.a24073.campusradio.controller;

import android.app.Application;

import com.github.asifmujteba.easyvolley.EasyVolley;

import io.rong.imkit.RongIM;


/**
 * Created by 24073 on 2017/6/9.
 */

public class APP extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
    }
}
