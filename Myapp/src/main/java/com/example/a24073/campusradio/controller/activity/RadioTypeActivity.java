package com.example.a24073.campusradio.controller.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a24073.campusradio.MyResource.SystemBarTintManager;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.adapter.LiveRoomAdapter;
import com.example.a24073.campusradio.model.bean.LiveRoom;
import com.example.a24073.campusradio.utils.MyGridView;

import java.util.ArrayList;
import java.util.List;

public class RadioTypeActivity extends AppCompatActivity {

    private TextView tv_title_radio_type;
    private MyGridView gv_liveroomtype;
    private List<LiveRoom> liveRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_type);
        tv_title_radio_type = (TextView) findViewById(R.id.title_radio_type);
        gv_liveroomtype = (MyGridView) findViewById(R.id.gv_liveroomtype);

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
        //-----------------------设置状态栏颜色-------------------


        //设置标题
        Intent intent=getIntent();//getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent  
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据  
        String str=bundle.getString("str");//getString()返回指定key的值
        tv_title_radio_type.setText(str);

        //设置girdview
        if(str.equals("私人电台")){
            liveRooms = new ArrayList<>();
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/36NtMMRofAn1C4iGMsvysfpBeMRxg578HreyrgZtvKk!/b/dD0BAAAAAAAA","YASUSU的烘培间","YASUSU",432,true));
            liveRooms.add(new LiveRoom("http://a2.qpic.cn/psb?/V121cQJl2s5so0/bJN9l6qwz2oRk9VjWnfxfw.tc3PkysumLAsjpj7Mrwg!/b/dGwBAAAAAAAA","千的日常播报","长岛没有秋刀鱼",387,true));
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/jUj6QUhdMEMu4J22vi9EPbl0WVVBgnE.PzSFph2hRiE!/b/dD0BAAAAAAAA","炸鸡块电台","北斋家的大虾",112,true));
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/KQjJpJVezS3WsGQF*7XUCmz2W7qSsOZPGneXiroSTMU!/b/dD0BAAAAAAAA","天啦噜的广播电台","拉拉酱",122,false));
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/M*YdiXEDZ2CDnLFRzFkLvyMtfV0Km9x4Qx93XsIJPbs!/b/dD0BAAAAAAAA","Reco电台","杆上的布朗尼",102,true));
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/JaDN66lIEp6l3NqrhS*rd2YJUlNwE8doyp9GK2MqKmc!/b/dD0BAAAAAAAA","深夜食堂电台","Yummy酱",98,false));
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/ndaLGJRiEF8DHgjFRHmc9PuwURajwGXt53JwaRQkanQ!/b/dD0BAAAAAAAA","榛美去呐","榛美",98,false));
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/8B00Hwo1AlnQYt0qGPuLBYnx6AnKMdLLyjQj4HRHP8w!/b/dD0BAAAAAAAA","知心老妹","鸢尾",92,false));
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/ZfaiiNHzXZRHXDN4fzoC4dE2*b4TejwDM4sDatpm5Pk!/b/dD0BAAAAAAAA","我唉健身跑","老黑巧克力",50,false));
        }else if(str.equals("校园电台")){
            liveRooms = new ArrayList<>();
            liveRooms.add(new LiveRoom("http://baike.haixiangjiaoyu.com/uploads/201410/1414387520MrkRAwUm_s.jpg","耶鲁校园电台","耶鲁",231,true));
            liveRooms.add(new LiveRoom("http://e-images.juwaistatic.com/2016/08/Harvard_University.jpg","哈佛校园电台","哈佛",219,true));
            liveRooms.add(new LiveRoom("http://www.logo11.cn/uploads/allimg/141107/1_141107100510_1.jpg","芝加哥大学电台","Chicago",177,false));
            liveRooms.add(new LiveRoom("http://d.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=f285556c8c13632715b8ca37a4bf8cda/34fae6cd7b899e51ece9f3a444a7d933c8950d1f.jpg","霍格沃茨电台","邓布利多",119,true));
            liveRooms.add(new LiveRoom("http://img.mp.itc.cn/upload/20160728/a56662039ace4cccb93fd0716235a1be.jpg","巴斯大学电台","老巴斯",122,true));
            liveRooms.add(new LiveRoom("http://img.bimg.126.net/photo/943NRO7deiCc14AfFnzT1g==/643451796777396826.jpg","西北大学电台","小西西",115,false));
            liveRooms.add(new LiveRoom("http://epaper.gmw.cn/gmrb/images/2016-01/24/07/res06_attpic_brief.jpg","清迈大学电台","歪摇摇铃",123,false));
            liveRooms.add(new LiveRoom("http://school.eduwo.com/images/eduwoweb/img/1462440529964.jpg","奥克兰商学院电台","奥克兰",111,false));
        }else {
            liveRooms = new ArrayList<>();
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/8B00Hwo1AlnQYt0qGPuLBYnx6AnKMdLLyjQj4HRHP8w!/b/dD0BAAAAAAAA","知心老妹","鸢尾",92,true));
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/ZfaiiNHzXZRHXDN4fzoC4dE2*b4TejwDM4sDatpm5Pk!/b/dD0BAAAAAAAA","我唉健身跑","老黑巧克力",50,true));
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/M*YdiXEDZ2CDnLFRzFkLvyMtfV0Km9x4Qx93XsIJPbs!/b/dD0BAAAAAAAA","我有酒还有故事","杆上的布朗尼",102,true));
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/JaDN66lIEp6l3NqrhS*rd2YJUlNwE8doyp9GK2MqKmc!/b/dD0BAAAAAAAA","深夜食堂电台","Yummy酱",98,false));
            liveRooms.add(new LiveRoom("http://d.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=f285556c8c13632715b8ca37a4bf8cda/34fae6cd7b899e51ece9f3a444a7d933c8950d1f.jpg","霍格沃茨电台","邓布利多",119,true));
            liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/KQjJpJVezS3WsGQF*7XUCmz2W7qSsOZPGneXiroSTMU!/b/dD0BAAAAAAAA","天啦噜的广播电台","拉拉酱",122,false));
            liveRooms.add(new LiveRoom("http://img.bimg.126.net/photo/943NRO7deiCc14AfFnzT1g==/643451796777396826.jpg","西北大学电台","小西西",115,false));
        }
        //liveRooms = new ArrayList<>();
        //liveRooms.add(new LiveRoom("http://a3.qpic.cn/psb?/V121cQJl2s5so0/M*YdiXEDZ2CDnLFRzFkLvyMtfV0Km9x4Qx93XsIJPbs!/b/dD0BAAAAAAAA","我有酒还有故事","杆上的布朗尼",102,true));

        gv_liveroomtype.setAdapter(new LiveRoomAdapter(liveRooms,this));
        gv_liveroomtype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(), LiveRoomActivity.class);//从一个activity跳转到另一个activity  
                if(liveRooms.get(position).isIflive()){
                    intent.putExtra("str", "1");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                }else {
                    intent.putExtra("str", "3");//给intent添加额外数据，key为“str”,key值为"Intent Demo"  
                }
                startActivity(intent);
            }
        });
    }



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

    public void type_back(View view){
        finish();
    }
}
