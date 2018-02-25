package com.example.a24073.campusradio.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a24073.campusradio.controller.activity.RadioTypeActivity;
import com.example.a24073.campusradio.controller.fragment.MainFragmentTwo;
import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by moxun on 16/1/19.
 */
public class TextTagsAdapter extends TagsAdapter {
    private Context mContext;
    private List<String> dataSet = new ArrayList<>();
    private String[] type = {"情感","健康","娱乐","科技","校园","脱口秀","文化","生活","二次元","语言","广播剧","资讯","音乐","公开课","历史","情感","健康","娱乐","科技","校园"};

    public TextTagsAdapter(Context context,@NonNull String... data) {
        mContext = context;
        dataSet.clear();
        Collections.addAll(dataSet, data);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        TextView tv = new TextView(context);
        tv.setText(type[position]);
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Click", "Tag " + position + " clicked.");
                Intent intent=new Intent();
                intent.setClass(mContext, RadioTypeActivity.class);//从一个activity跳转到另一个activity  
                intent.putExtra("str", type[position]);//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                mContext.startActivity(intent);
            }
        });
        tv.setTextColor(Color.WHITE);
        return tv;
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position % 7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        view.setBackgroundColor(themeColor);
    }
}