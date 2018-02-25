package com.example.a24073.campusradio.controller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.cunoraz.gifview.library.GifView;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.base.BaseFragment;
import com.example.a24073.campusradio.controller.activity.HotTopicActivity;
import com.example.a24073.campusradio.controller.activity.MomentUtilsActivity;
import com.example.a24073.campusradio.controller.activity.MoreLocalMomentActivity;
import com.example.a24073.campusradio.controller.activity.WriteMomentActivity;
import com.example.a24073.campusradio.controller.adapter.MomentAdapter;
import com.example.a24073.campusradio.model.bean.Message;
import com.example.a24073.campusradio.utils.MomentEvent;
import com.freegeek.android.materialbanner.MaterialBanner;
import com.freegeek.android.materialbanner.demo.BannerData;
import com.freegeek.android.materialbanner.demo.ImageHolderView;
import com.freegeek.android.materialbanner.holder.ViewHolderCreator;
import com.freegeek.android.materialbanner.view.indicator.CirclePageIndicator;
import com.melnykov.fab.ObservableScrollView;
import com.ramotion.foldingcell.FoldingCell;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by 24073 on 2017/6/3.
 * 动态界面
 */

public class MomentFragment extends BaseFragment{
    private View view;
    private MaterialBanner materialBanner;
    private ObservableScrollView scrollView;
    private View more_local_moment;

    private ListView lv_moment_local;
    private ListView lv_moment_global;
    List<Message> messages_local;
    List<Message> messages_global;
    MomentAdapter momentAdapter_local;

    private View view_moment_train;
    private View view_moment_resource;
    private View view_moment_comingsoon;

    private View view_moretopic;
    private View view_topic1;

    private View item_voicemoment;


    private View playvoice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected View initView() {
        view = View.inflate(getActivity(), R.layout.fragment_moment,null);

        materialBanner = (MaterialBanner) view.findViewById(R.id.material_banner);
        scrollView = (ObservableScrollView) view.findViewById(R.id.sv_moment);
        lv_moment_local = (ListView) view.findViewById(R.id.moment_local);
        lv_moment_global = (ListView) view.findViewById(R.id.moment_global);
        more_local_moment = view.findViewById(R.id.more_local_moment);

        view_moment_resource = view.findViewById(R.id.moment_resource);
        view_moment_train = view.findViewById(R.id.moment_train);
        view_moment_comingsoon = view.findViewById(R.id.moment_comingsoon);

        view_moretopic = view.findViewById(R.id.more_topic);
        view_topic1 = view.findViewById(R.id.topic1);

        item_voicemoment  = view.findViewById(R.id.item_voicemoment);



        // get our folding cell
        final FoldingCell fc = (FoldingCell) view.findViewById(R.id.folding_cell1);
        // attach click listener to folding cell
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });

        //注册EventBus  
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus  
    }

    @Override
    protected void initData() {
        super.initData();
        setBanner();
        setFab();
        setLocal();
        setGlobal();
        setMore_local_moment();
        setUtils();
        setTopic();




    }

    //eventbus回调
    @Subscribe
    public void onEventMainThread(MomentEvent momentEvent){
        /*
        Message message = new Message();
        message.setSenderName("吃香也橙子呢");
        message.setSendTime(momentEvent.getDate());
        message.setSenderSchool("王者峡谷学院");
        message.setWords(momentEvent.getText());
        messages_local.add(0,message);
        momentAdapter_local.notifyDataSetChanged();
        */
        if(momentEvent.getText().equals("voice")){
            item_voicemoment.setVisibility(View.VISIBLE);
        }else {
            Message message = new Message("http://a3.qpic.cn/psb?/V121cQJl2s5so0/WEudqBJwcATn8Xx.EhbzMqfUpuEX*wFulp9SA6WpyZQ!/b/dD0BAAAAAAAA",
                    "吃香也橙子呢","耶鲁大学",new Date(System.currentTimeMillis()),momentEvent.getText(),null);
            messages_local.add(0,message);

            momentAdapter_local.notifyDataSetChanged();
        }
    }


    //——————————————————设置自动滚动广告———————————————————————
    private void setBanner(){
        BannerData bannerData1 = new BannerData();
        bannerData1.setTitle("");
        bannerData1.setUrl("http://imgsrc.baidu.com/forum/pic/item/301e1a4c510fd9f9b01d63eb2f2dd42a2934a414.jpg");
        List bannerData = new ArrayList();
        bannerData.add(bannerData1);
        BannerData bannerData2 = new BannerData();
        bannerData2.setUrl("http://a4.qpic.cn/psb?/V121cQJl1HcbCT/.ETg8fppwR9f3qWenH2gQKGwlvlhAOBO7Lc3B3S5PFc!/b/dNsAAAAAAAAA");
        bannerData.add(bannerData2);
        BannerData bannerData3 = new BannerData();
        bannerData3.setUrl("http://a2.qpic.cn/psb?/V121cQJl1HcbCT/yjBC2Re0X2QNCBq3CQvy*sho*0V6UyM1hotkulypoJ0!/b/dPcAAAAAAAAA");
        bannerData.add(bannerData3);
        materialBanner.setPages(new ViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new ImageHolderView();
            }
        },bannerData);
        //set circle indicator
        materialBanner.setIndicator(new CirclePageIndicator(getActivity()));
        materialBanner.startTurning(1000);
        //indicators:
        //CirclePageIndicator,IconPageIndicator,LinePageIndicator
        //Custom indicator view needs to implement com.freegeek.android.materialbanner.view.indicator.PageIndicator
    }

    //------------------------------------设置FloatingActionButton------------------------------------------
    private void setFab(){
        com.melnykov.fab.FloatingActionButton fab = (com.melnykov.fab.FloatingActionButton) view.findViewById(R.id.fab);
        fab.attachToScrollView(scrollView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),WriteMomentActivity.class);
                startActivity(intent);
            }
        });
    }


    //------------------------------------设置lv_moment_local----------------------------
    private void setLocal(){
        /*网络请求
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "http://119.23.59.97:8080/ssh-2/getMessageBySchool?school=YaleUniversity";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            //正确接收数据回调
            @Override
            public void onResponse(String s) {
                //s即为所返回的jason串
                messages_local = JSON.parseArray(s, Message.class);//转化为jason数组
                momentAdapter_local = new MomentAdapter(messages_local,getActivity());
                lv_moment_local.setAdapter(momentAdapter_local);
                setListViewHeightBasedOnChildren(lv_moment_local);
            }
        }, new Response.ErrorListener() {
            //出现错误
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //所做处理
            }
        });
        requestQueue.add(stringRequest);
        */
        //String senderIcon, String senderName, String senderSchool, Date sendTime, String words, String picture
        messages_local = new ArrayList<>();
        messages_local.add(new Message("http://a3.qpic.cn/psb?/V121cQJl2s5so0/.ebMNtI36W2m*iRXomZJPYE8SjuATW1BsJe**CFj.fQ!/b/dG0BAAAAAAAA",
                "喵喵喵","耶鲁大学",new Date(System.currentTimeMillis()),"7-8月，羊卓雍错，应该是这个世界最美的湖。","http://wx1.sinaimg.cn/mw690/7fa92467gy1fhw000adtyj20j60cs77v.jpg"));
        messages_local.add(new Message("http://a3.qpic.cn/psb?/V121cQJl2s5so0/ndaLGJRiEF8DHgjFRHmc9PuwURajwGXt53JwaRQkanQ!/b/dD0BAAAAAAAA",
                "荒废的年糕","耶鲁大学",new Date(System.currentTimeMillis()),"好久不下雨，如何读书？",null));
        messages_local.add(new Message("http://a3.qpic.cn/psb?/V121cQJl2s5so0/M*YdiXEDZ2CDnLFRzFkLvyMtfV0Km9x4Qx93XsIJPbs!/b/dD0BAAAAAAAA",
                "扑街Star","耶鲁大学",new Date(System.currentTimeMillis()),"今天来阿里巴巴实习，外面太热了","http://wx3.sinaimg.cn/mw690/8018bea1gy1fhxc2gdj5xj23402c04qs.jpg"));
        momentAdapter_local = new MomentAdapter(messages_local,getActivity());
        lv_moment_local.setAdapter(momentAdapter_local);
        setListViewHeightBasedOnChildren(lv_moment_local);

    }



    //------------------------------------设置lv_moment_global----------------------------
    private void setGlobal(){
        /*
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "http://119.23.59.97:8080/ssh-2/getMessageBySchool?school=YaleUniversity";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            //正确接收数据回调
            @Override
            public void onResponse(String s) {
                //s即为所返回的jason串
                messages_global = JSON.parseArray(s, Message.class);//转化为jason数组
                MomentAdapter momentAdapter = new MomentAdapter(messages_global,getActivity());
                lv_moment_global.setAdapter(momentAdapter);
                setListViewHeightBasedOnChildren(lv_moment_global);
            }
        }, new Response.ErrorListener() {
            //出现错误
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //所做处理
            }
        });
        requestQueue.add(stringRequest);
        */

        messages_global = new ArrayList<>();
        messages_global.add(new Message("http://img.qq1234.org/uploads/allimg/140707/3_140707142055_14.jpg",
                "白夜HHH","哈佛大学",new Date(System.currentTimeMillis()),"暴怒的人挑起争端，忍耐的人止息纷争",null));
        messages_global.add(new Message("http://v1.qzone.cc/avatar/201508/17/09/21/55d1372b820a3621.jpg%21200x200.jpg",
                "蓝胖子嘤嘤嘤","霍格沃兹大学",new Date(System.currentTimeMillis()),"喵星人为了抓鱼不小心掉进鱼缸 悲剧了","http://wx2.sinaimg.cn/mw690/dd783238gy1fhv0eeciukj20qo0k0gpd.jpg"));
        messages_global.add(new Message("http://a3.qpic.cn/psb?/V121cQJl2s5so0/6kaB9FkBX.TbfKxZoR2rILUWzi6ss3jkhWHtMIhU6sQ!/b/dG0BAAAAAAAA",
                "马克达蒙","剑桥大学",new Date(System.currentTimeMillis()),"青芒校园FM这款APP很有趣诶",null));
        MomentAdapter momentAdapter = new MomentAdapter(messages_global,getActivity());
        lv_moment_global.setAdapter(momentAdapter);
        setListViewHeightBasedOnChildren(lv_moment_global);

    }

    //-----------------------------解决滚动和listview的问题------------------------------
    public void setListViewHeightBasedOnChildren(ListView listView) { // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) { return; }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            //  计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        /// / listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

//------------------------------------设置更多本校动态-------------------------------------------
    public void setMore_local_moment(){
        more_local_moment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MoreLocalMomentActivity.class);
                startActivity(intent);
            }
        });
    }

//设置校园业务拓展模块
    public void setUtils(){
        view_moment_resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MomentUtilsActivity.class);
                intent.putExtra("str","resource");
                startActivity(intent);
            }
        });

        view_moment_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MomentUtilsActivity.class);
                intent.putExtra("str","train");
                startActivity(intent);
            }
        });

        view_moment_comingsoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MomentUtilsActivity.class));
            }
        });
    }

    //设置话题模块
    private  void setTopic(){
        view_moretopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MomentUtilsActivity.class);
                intent.putExtra("str","topic");
                startActivity(intent);
            }
        });

        view_topic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HotTopicActivity.class);
                intent.putExtra("str","topic1");
                startActivity(intent);
            }
        });
    }

}
