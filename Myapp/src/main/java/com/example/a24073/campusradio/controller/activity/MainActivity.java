package com.example.a24073.campusradio.controller.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.algebra.sdk.API;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.base.BaseFragment;
import com.example.a24073.campusradio.controller.fragment.MainFragment;
import com.example.a24073.campusradio.controller.fragment.MeFragment;
import com.example.a24073.campusradio.controller.fragment.MomentFragment;
import com.example.a24073.campusradio.controller.fragment.RadioFragment;
import com.example.a24073.campusradio.MyResource.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;


public class MainActivity extends FragmentActivity {

    private RadioGroup radioGroup;
    private List<BaseFragment> mBaseFragments;
    private int position;
    private android.support.v4.app.Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //------------------------设置底部的按钮------------------
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
        //-----------------------设置状态栏颜色-------------------
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
           // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        // 自定义状态栏颜色
        tintManager.setTintColor(Color.parseColor("#00645c"));

    }


    //------------------------设置底部的按钮------------------
    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        radioGroup.check(R.id.rb_main);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_main:
                    position = 0;
                    break;
                case R.id.rb_radio:
                    position = 1;
                    break;
                case R.id.rb_moment:
                    position = 2;
                    break;
                case R.id.rb_me:
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }

            //根据位置对应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFragment(mContent,to);
        }
    }
    //from 原先是，to后来的fragment
    private void switchFragment(android.support.v4.app.Fragment from, android.support.v4.app.Fragment to) {
        if(from != to){
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //判断有没有被添加
            if(!to.isAdded()){
                //to没有被添加 from隐藏 添加to
                if (from != null){
                    ft.hide(from);
                }
                if(to != null){
                    ft.add(R.id.fl_content,to).commit();
                }
            }else {
                //to已经被添加 from隐藏 显示to
                if (from != null){
                    ft.hide(from);
                }
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }
    }

    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragments.get(position);
        return fragment;
    }

    private void initView(){
        radioGroup = (RadioGroup)findViewById(R.id.rg_bottom_tag);
    }
    private void initFragment(){
        mBaseFragments = new ArrayList<>();
        mBaseFragments.add(new MainFragment());
        mBaseFragments.add(new RadioFragment());
        mBaseFragments.add(new MomentFragment());
        mBaseFragments.add(new MeFragment());
    }
    //-------------------------------------设置底部按钮-------------------------------

    //-------------------------------------状态栏颜色修改------------------------------
    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    //-------------------------------------状态栏颜色修改------------------------------


}
