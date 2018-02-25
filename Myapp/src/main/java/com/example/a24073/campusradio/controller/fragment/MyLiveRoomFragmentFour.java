package com.example.a24073.campusradio.controller.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cunoraz.gifview.library.GifView;
import com.example.a24073.campusradio.R;
import com.fynn.switcher.Switch;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyLiveRoomFragmentFour extends Fragment {

    com.fynn.switcher.Switch aSwitch;
    TextView tv_connectstatus;

    Button button;
    GifView gifView1;
    ImageView nowave;
    TextView tv_someonespk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_live_room_fragment_four, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aSwitch = (Switch) view.findViewById(R.id.openconnet);
        tv_connectstatus = (TextView) view.findViewById(R.id.connectstatus);
        tv_someonespk = (TextView) view.findViewById(R.id.tv_someonespk);
        nowave = (ImageView) view.findViewById(R.id.nowave);
        aSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(Switch s, boolean isChecked) {
                if(isChecked){
                    tv_connectstatus.setText("已开启直播互动");
                }else {
                    tv_connectstatus.setText("尚未开启直播互动");
                }
            }
        });

        gifView1 = (GifView)view.findViewById(R.id.gif1);
        gifView1.setVisibility(View.INVISIBLE);
        gifView1.play();

        button = (Button) view.findViewById(R.id.button);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    tv_someonespk.setText("当前无人发言");
                    gifView1.setVisibility(View.INVISIBLE);
                    nowave.setVisibility(View.VISIBLE);
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if(aSwitch.isChecked()) {
                        tv_someonespk.setText("我正在讲话...");
                        gifView1.setVisibility(View.VISIBLE);
                        nowave.setVisibility(View.INVISIBLE);
                    }
                }
                return false;
            }
        });
    }

}
