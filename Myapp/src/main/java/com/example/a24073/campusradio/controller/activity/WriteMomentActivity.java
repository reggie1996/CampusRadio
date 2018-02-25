package com.example.a24073.campusradio.controller.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cunoraz.gifview.library.GifView;
import com.example.a24073.campusradio.MyResource.SystemBarTintManager;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.utils.MomentEvent;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import com.sdsmdg.tastytoast.TastyToast;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Date;

public class WriteMomentActivity extends AppCompatActivity {

    //调用系统相册-选择图片
    private static final int IMAGE = 1;

    TextView textView;
    Date date;
    String text;
    View iv_voicemoment;

    Bitmap bm;

    private View slideView;
    private SlideUp slideUp;

    private GifView gifView1;
    private Button voicebottom;

    TextView press;
    Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_moment);

        mContext = this;

        textView = (TextView) findViewById(R.id.editText);
        iv_voicemoment = findViewById(R.id.iv_voicemoment);
        slideView = findViewById(R.id.momentSlideView);
        gifView1 = (GifView) findViewById(R.id.gifvoicebottom);
        voicebottom = (Button) findViewById(R.id.voicebottom);
        press = (TextView) findViewById(R.id.press);

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

        //设置上划
        slideUp = new SlideUpBuilder(slideView)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {

                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        if (visibility == View.GONE){

                        }
                    }
                })
                .withStartGravity(Gravity.BOTTOM)
                .withLoggingEnabled(true)
                .withStartState(SlideUp.State.HIDDEN)
                .build();

        voicebottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voicebottom.setVisibility(View.INVISIBLE);
                gifView1.setVisibility(View.VISIBLE);
                gifView1.play();
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                voicebottom.setVisibility(View.VISIBLE);
                                gifView1.setVisibility(View.INVISIBLE);
                                new Thread(){
                                    @Override
                                    public void run() {
                                        try {
                                            sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                slideUp.hide();
                                                iv_voicemoment.setVisibility(View.VISIBLE);
                                            }
                                        });
                                    }
                                }.start();
                            }
                        });
                    }
                }.start();
            }
        });

        press.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                text = "voice";
                date = new Date(System.currentTimeMillis());//获取当前时间 
                EventBus.getDefault().post(new MomentEvent(text,null,date));

                TastyToast.makeText(mContext, "发表中...", TastyToast.LENGTH_LONG, TastyToast.INFO);

                //new Thread().sleep(800);
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }.start();

                TastyToast.makeText(mContext, "发表成功", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

                return true;
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
    //-------------------------------------状态栏颜色修改------------------------------


    public void back(View view){
        finish();
    }

    public void press(View view){

        text = textView.getText().toString();
        date = new Date(System.currentTimeMillis());//获取当前时间 
        EventBus.getDefault().post(new MomentEvent(text,null,date));

        TastyToast.makeText(this, "发表中...", TastyToast.LENGTH_LONG, TastyToast.INFO);


            //new Thread().sleep(800);
            new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }.start();

        TastyToast.makeText(this, "发表成功", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
    }

    public void selectImage(View view){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imaePath){
        bm = BitmapFactory.decodeFile(imaePath);
        ((ImageView)findViewById(R.id.image_moment)).setImageBitmap(bm);
    }


    public void startrecode(View view){
        slideUp.show();
    }

}
