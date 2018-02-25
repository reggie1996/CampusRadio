package com.example.a24073.campusradio.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.activity.ComprtitionActivity;
import com.example.a24073.campusradio.controller.activity.LiveRoomActivity;
import com.example.a24073.campusradio.controller.activity.PredictionActivity;
import com.example.a24073.campusradio.controller.adapter.LiveCourseAdapter;
import com.example.a24073.campusradio.controller.adapter.VerticalBannerAdapter;
import com.example.a24073.campusradio.model.bean.LiveCourse;
import com.example.a24073.campusradio.model.bean.VerticalBannerModel;
import com.example.a24073.campusradio.utils.MyListView;
import com.freegeek.android.materialbanner.MaterialBanner;
import com.freegeek.android.materialbanner.demo.BannerData;
import com.freegeek.android.materialbanner.demo.ImageHolderView;
import com.freegeek.android.materialbanner.holder.ViewHolderCreator;
import com.freegeek.android.materialbanner.view.indicator.CirclePageIndicator;
import com.taobao.library.VerticalBannerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 24073 on 2017/7/9.
 */

public class MainFragmentThree extends Fragment {

    private MaterialBanner materialBanner;
    private MyListView lv_liveCourse;
    private List<LiveCourse> liveCourses = new ArrayList<>();
    private MyListView lv_preCourse;
    private List<LiveCourse> preCourses = new ArrayList<>();
    private MyListView lv_onlinecompetition;
    private List<LiveCourse> onlineCompetition = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mainthree, null);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        materialBanner = (MaterialBanner) view.findViewById(R.id.material_banner_mainthree);
        lv_liveCourse = (MyListView) view.findViewById(R.id.lv_livecourse);
        lv_preCourse = (MyListView) view.findViewById(R.id.lv_precourse);
        lv_onlinecompetition = (MyListView) view.findViewById(R.id.lv_onlinecompetition);

        setVerticalBanner(view);
        setBanner();
        setLv_liveCourse();
        setLv_preCourse();
        setLv_onlinecompetition();

    }

    //----------------------------------设置垂直的bannner-------------------------------------------
    private void setVerticalBanner(View view){
        List<VerticalBannerModel> datas = new ArrayList<>();
        datas.add(new VerticalBannerModel("期末高数补课班，赶快预约吧",R.drawable.ic_star1));
        datas.add(new VerticalBannerModel("第一届青芒杯英语演讲大赛，点击报名",R.drawable.ic_star1));
        datas.add(new VerticalBannerModel("易中地老师的文学课堂，快来围观",R.drawable.ic_star1));
        final VerticalBannerAdapter adapter = new VerticalBannerAdapter(datas);
        final VerticalBannerView banner = (VerticalBannerView)view.findViewById(R.id.banner_01);
        banner.setAdapter(adapter);
        banner.start();
    }

    //——————————————————设置自动滚动广告———————————————————————
    private void setBanner(){
        List bannerData = new ArrayList();

        BannerData bannerData1 = new BannerData();
        bannerData1.setTitle("");
        bannerData1.setUrl("http://a3.qpic.cn/psb?/V121cQJl1HcbCT/GY0302Th265tgSLhQtiJ9vE*gySpjRNU02UjvHQFL14!/b/dD0BAAAAAAAA&bo=XAN8AQAAAAADAAc!");
        bannerData.add(bannerData1);
        BannerData bannerData2 = new BannerData();
        bannerData2.setUrl("http://imgsrc.baidu.com/forum/pic/item/65ef6b63f6246b60c6581dffe1f81a4c530fa2ca.jpg");
        bannerData.add(bannerData2);
        BannerData bannerData3 = new BannerData();
        bannerData3.setUrl("http://a3.qpic.cn/psb?/V121cQJl1HcbCT/rSSZN9HijsJ4BDnRIA7I54ewQ.rb7VAPI12XhlOW9.A!/b/dG0BAAAAAAAA");
        bannerData.add(bannerData3);
        BannerData bannerData4 = new BannerData();
        bannerData4.setUrl("http://imgsrc.baidu.com/forum/pic/item/29f9be096b63f624a20b96888d44ebf8184ca3ca.jpg");
        bannerData.add(bannerData4);
        materialBanner.setPages(new ViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolderView();
            }
        },bannerData);
        //set circle indicator
        materialBanner.setIndicator(new CirclePageIndicator(getActivity()));
        materialBanner.startTurning(2000);
        //indicators:
        //CirclePageIndicator,IconPageIndicator,LinePageIndicator
        //Custom indicator view needs to implement com.freegeek.android.materialbanner.view.indicator.PageIndicator
    }

    private void setLv_liveCourse(){

        if(liveCourses.size() == 0) {
            liveCourses.add(new LiveCourse("英语自救指南", " 邓布利多", "考研复习系列公共课", "http://imgsrc.baidu.com/forum/pic/item/d4550f2442a7d933bca940f8a74bd11372f0011a.jpg"));
            liveCourses.add(new LiveCourse("Vue2.0高级实战", " D8级工程师", "搞定Vue成为前端大神", "http://imgsrc.baidu.com/forum/pic/item/a06f9e510fb30f24309b4888c295d143ac4b031a.jpg"));
            liveCourses.add(new LiveCourse("考研逢考必过", " 斯内普", "公共课270分不是梦", "http://imgsrc.baidu.com/forum/pic/item/45b70fb30f2442a7aa0f592edb43ad4bd013021a.jpg"));
            liveCourses.add(new LiveCourse("线代启示录", " 靳老师", "线代复习及解题策略", "http://imgsrc.baidu.com/forum/pic/item/d4c242a7d933c895a47f3cf0db1373f08302001a.jpg"));
        }
        lv_liveCourse.setAdapter(new LiveCourseAdapter(liveCourses,getActivity(),true));
        lv_liveCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(getContext(), LiveRoomActivity.class);//从一个activity跳转到另一个activity  
                intent.putExtra("str", "2");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                startActivity(intent);
            }
        });
    }

    private void setLv_preCourse(){
        if(preCourses.size() == 0) {
            preCourses.add(new LiveCourse("时间管理速成班", " 老吴", "迈出你的高效生活", "http://imgsrc.baidu.com/forum/pic/item/d1a0f21fbe096b633e4ff1db06338744e9f8acca.jpg"));
            preCourses.add(new LiveCourse("乡村老师的慕课", " 赫敏", "资深用户，173张证书", "http://imgsrc.baidu.com/forum/pic/item/0c2c7bcb0a46f21f7862f1d8fc246b600e33aeca.jpg"));
            preCourses.add(new LiveCourse("每日一读", " 易中地", "给你讲讲书中的道理", "http://imgsrc.baidu.com/forum/pic/item/65ef6b63f6246b60c6581dffe1f81a4c530fa2ca.jpg"));
            preCourses.add(new LiveCourse("挺进大山的天文", " 小天狼星", "走，咱们一起去看星星", "http://imgsrc.baidu.com/forum/pic/item/a02d0a46f21fbe09ac086c9f61600c338544adca.jpg"));
            preCourses.add(new LiveCourse("单词速记课", " 昭雪昭雪", "无痛背单词，快来试试？", "http://imgsrc.baidu.com/forum/pic/item/29f9be096b63f624a20b96888d44ebf8184ca3ca.jpg"));
        }
        lv_preCourse.setAdapter(new LiveCourseAdapter(preCourses,getActivity(),false));
        lv_preCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(getContext(), PredictionActivity.class);//从一个activity跳转到另一个activity  
                startActivity(intent);
            }
        });
    }


    private void setLv_onlinecompetition(){
        if(onlineCompetition.size() == 0) {
            onlineCompetition.add(new LiveCourse("线上校园歌手大赛", "青芒校园", "2017-8-25", "http://a3.qpic.cn/psb?/V121cQJl1Kj5Ik/jbH8MfDzUxFaTswlIJZBJ1H*JgZ6x.NtpfE41YUlxXA!/b/dD0BAAAAAAAA"));
            onlineCompetition.add(new LiveCourse("青芒英语演讲大赛", "霍格沃兹", "2017-8-23", "http://a3.qpic.cn/psb?/V121cQJl1HcbCT/rSSZN9HijsJ4BDnRIA7I54ewQ.rb7VAPI12XhlOW9.A!/b/dG0BAAAAAAAA"));
        }
        lv_onlinecompetition.setAdapter(new LiveCourseAdapter(onlineCompetition,getActivity(),false));
        lv_onlinecompetition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(getContext(), ComprtitionActivity.class);//从一个activity跳转到另一个activity  
                startActivity(intent);
            }
        });
    }


}
