package com.example.a24073.campusradio.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cunoraz.gifview.library.GifView;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.controller.adapter.ClassRoomConversatioonAdapter;
import com.example.a24073.campusradio.model.bean.ClassConversation;
import com.example.a24073.campusradio.utils.MyListView;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;

import java.util.ArrayList;
import java.util.List;

public class ClassRoomActivity extends Activity {

    View slideView;
    SlideUp slideUp;

    MyListView lv_classroomconversation;
    List<ClassConversation> classConversations;
    ClassRoomConversatioonAdapter classRoomConversatioonAdapter;

    GifView gifView1;
    Button voicebottom;

    TextView whoisspeaking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_room);

        slideView = findViewById(R.id.slideView);
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

        lv_classroomconversation = (MyListView) findViewById(R.id.lv_classroomconversation);
        classConversations = new ArrayList<>();
        classRoomConversatioonAdapter = new ClassRoomConversatioonAdapter(classConversations,getApplication());
        lv_classroomconversation.setAdapter(classRoomConversatioonAdapter);


        gifView1 = (GifView)findViewById(R.id.gifvoicebottom);
        voicebottom = (Button) findViewById(R.id.voicebottom);
        voicebottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voicebottom.setVisibility(View.INVISIBLE);
                gifView1.setVisibility(View.VISIBLE);
                gifView1.play();
                new Thread(){
                    public void run(){
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    whoisspeaking.setText("我正在讲话...");
                                }
                            });
                            sleep(3000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    gifView1.setVisibility(View.INVISIBLE);
                                    voicebottom.setVisibility(View.VISIBLE);
                                    new Thread(){
                                        public void run(){
                                            try {
                                                sleep(500);
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        slideUp.hide();
                                                        whoisspeaking.setText("当前无人发言");
                                                        classConversations.add(new ClassConversation(R.drawable.ic_headportrait1,"12:23  3\"",true));
                                                        classRoomConversatioonAdapter.notifyDataSetChanged();
                                                    }
                                                });
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }.start();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });

        whoisspeaking = (TextView) findViewById(R.id.whoisspeaking);

    }




    public void sendvoice(View view){
        slideUp.show();
    }

    public void sendvoice2(View view){
        slideUp.hide();
    }

    public void bottomdown(View view){
        voicebottom.setVisibility(View.INVISIBLE);
        gifView1.setVisibility(View.VISIBLE);
        gifView1.play();
        new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        gifView1.setVisibility(View.INVISIBLE);
        voicebottom.setVisibility(View.VISIBLE);
        slideUp.hide();
    }

}
