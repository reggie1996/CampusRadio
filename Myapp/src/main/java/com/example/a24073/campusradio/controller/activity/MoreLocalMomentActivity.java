package com.example.a24073.campusradio.controller.activity;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a24073.campusradio.MyResource.SystemBarTintManager;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.adapter.MomentAdapter;
import com.example.a24073.campusradio.model.bean.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoreLocalMomentActivity extends AppCompatActivity {

    ListView lv_more_local_moment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_local_moment);

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

        lv_more_local_moment = (ListView) findViewById(R.id.lv_more_local_moment);
        setLv_more_local_moment();

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
    //-------------------------------------设置listview-----------------------------
    public void setLv_more_local_moment(){
        /*
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://119.23.59.97:8080/ssh-2/getMessageBySchool?school=YaleUniversity";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            //正确接收数据回调
            @Override
            public void onResponse(String s) {
                //s即为所返回的jason串
                List<Message> messages_local = JSON.parseArray(s, Message.class);//转化为jason数组
                MomentAdapter momentAdapter = new MomentAdapter(messages_local,getApplication());
                lv_more_local_moment.setAdapter(momentAdapter);
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
        List<Message> messages_local = new ArrayList<>();
        //messages_local = new ArrayList<>();
        messages_local.add(new Message("http://a3.qpic.cn/psb?/V121cQJl2s5so0/.ebMNtI36W2m*iRXomZJPYE8SjuATW1BsJe**CFj.fQ!/b/dG0BAAAAAAAA",
                "喵喵喵","耶鲁大学",new Date(System.currentTimeMillis()),"7-8月，羊卓雍错，应该是这个世界最美的湖。","http://wx1.sinaimg.cn/mw690/7fa92467gy1fhw000adtyj20j60cs77v.jpg"));
        messages_local.add(new Message("http://a3.qpic.cn/psb?/V121cQJl2s5so0/ndaLGJRiEF8DHgjFRHmc9PuwURajwGXt53JwaRQkanQ!/b/dD0BAAAAAAAA",
                "荒废的年糕","耶鲁大学",new Date(System.currentTimeMillis()),"好久不下雨，如何读书？",null));
        messages_local.add(new Message("http://a3.qpic.cn/psb?/V121cQJl2s5so0/M*YdiXEDZ2CDnLFRzFkLvyMtfV0Km9x4Qx93XsIJPbs!/b/dD0BAAAAAAAA",
                "扑街Star","耶鲁大学",new Date(System.currentTimeMillis()),"今天来阿里巴巴实习，外面太热了","http://wx3.sinaimg.cn/mw690/8018bea1gy1fhxc2gdj5xj23402c04qs.jpg"));
        MomentAdapter momentAdapter = new MomentAdapter(messages_local,getApplication());
        lv_more_local_moment.setAdapter(momentAdapter);
    }

    public void moment_back(View view){
        finish();
    }

}
