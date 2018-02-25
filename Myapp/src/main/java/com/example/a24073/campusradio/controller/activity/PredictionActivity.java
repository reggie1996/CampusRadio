package com.example.a24073.campusradio.controller.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a24073.campusradio.MyResource.SystemBarTintManager;
import com.example.a24073.campusradio.R;
import com.sdsmdg.tastytoast.TastyToast;

import me.drakeet.materialdialog.MaterialDialog;

public class PredictionActivity extends Activity {
    ImageView order;

    TextView replay;
    TextView ordercourse;
    Context mContext;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);
        mContext = this;

        order = (ImageView) findViewById(R.id.iv_order);

        replay = (TextView) findViewById(R.id.replay);
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),LiveRoomActivity.class);
                intent.putExtra("str","4");
                startActivity(intent);
            }
        });
        ordercourse = (TextView) findViewById(R.id.ordercourse);
        ordercourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialDialog mMaterialDialog = new MaterialDialog(mContext);
                mMaterialDialog
                        .setTitle("预约成功！")
                        .setMessage("可进入我的课程进行预约管理")
                        .setPositiveButton("我的课程", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        })
                        .setNegativeButton("我知道了", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });
                mMaterialDialog.show();
            }
        });

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


    public void pressPredicte(View view){
        order.setImageResource(R.drawable.ic_predictionbutton2);
    }

}
