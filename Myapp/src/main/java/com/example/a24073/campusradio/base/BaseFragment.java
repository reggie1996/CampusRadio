package com.example.a24073.campusradio.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 24073 on 2017/6/3.
 * frament的基类
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment{
    protected Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    protected abstract View initView();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
/*
当子类需要初始化数据，或者联网请求绑定数据，展示数据
 */
    protected void initData(){

    }
}
