package com.example.a24073.campusradio.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.activity.LiveRoomActivity;
import com.example.a24073.campusradio.model.bean.LiveCourse;
import com.example.a24073.campusradio.model.bean.LiveRoom;

import java.util.List;

/**
 * Created by 24073 on 2017/7/22.
 */

public class LiveCourseAdapter extends BaseAdapter {

    private List<LiveCourse> liveCourses;
    private Context mContext;
    private boolean iflive;

    public LiveCourseAdapter(List<LiveCourse> liveCourses, Context mContext,boolean iflive) {
        this.liveCourses = liveCourses;
        this.mContext = mContext;
        this.iflive = iflive;
    }

    @Override
    public int getCount() {
        return liveCourses.size();
    }

    @Override
    public Object getItem(int position) {
        return liveCourses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(mContext, R.layout.item_studyroom,null);
        LiveCourse liveCourse = liveCourses.get(position);

        TextView courseName = (TextView) view.findViewById(R.id.tv_coursename);
        TextView hostName = (TextView) view.findViewById(R.id.tv_hostName);
        TextView describe = (TextView) view.findViewById(R.id.tv_describe);
        ImageView cover = (ImageView) view.findViewById(R.id.iv_cover);
        ImageView live = (ImageView) view.findViewById(R.id.iv_livestatus);

        if(liveCourse.getCourseName().contains("赛")){

            courseName.setText("名称：" + liveCourse.getCourseName());
            hostName.setText("主办：" + liveCourse.getHostName());
            describe.setText("时间：" + liveCourse.getDescribe());

            Glide.with(mContext)
                    .load(liveCourse.getCover())
                    .dontAnimate()
                    .placeholder(R.drawable.coursecover)
                    .into(cover);

            Glide.with(mContext)
                    .load(R.drawable.ic_compi)
                    .dontAnimate()
                    .placeholder(R.drawable.ic_live)
                    .into(live);

        }else{
            courseName.setText("课程名：" + liveCourse.getCourseName());
            hostName.setText("主讲人：" + liveCourse.getHostName());
            describe.setText("简介：" + liveCourse.getDescribe());

            Glide.with(mContext)
                    .load(liveCourse.getCover())
                    .dontAnimate()
                    .placeholder(R.drawable.coursecover)
                    .into(cover);
            if(iflive) {
                Glide.with(mContext)
                        .load(R.drawable.ic_live)
                        .dontAnimate()
                        .placeholder(R.drawable.ic_live)
                        .into(live);
            }else {
                Glide.with(mContext)
                        .load(R.drawable.ic_pre)
                        .dontAnimate()
                        .placeholder(R.drawable.ic_pre)
                        .into(live);
            }
        }
        return view;
    }
}
