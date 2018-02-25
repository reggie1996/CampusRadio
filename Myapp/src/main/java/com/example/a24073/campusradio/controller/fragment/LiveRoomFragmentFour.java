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

import me.drakeet.materialdialog.MaterialDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveRoomFragmentFour extends Fragment {

    Button button;
    GifView gifView1;
    ImageView nowave;
    TextView tv_someonespk;
    TextView connectstatus;
    View change;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_room_fragment_four, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_someonespk = (TextView) view.findViewById(R.id.tv_someonespk);
        connectstatus = (TextView) view.findViewById(R.id.connectstatus);
        change = view.findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(connectstatus.getText().equals("尚未开启直播互动")){
                    final MaterialDialog mMaterialDialog = new MaterialDialog(getActivity());
                    mMaterialDialog
                            .setTitle("直播互动")
                            .setMessage("主播开启直播互动了，\n赶紧和主播互动聊天吧！")
                            .setPositiveButton("好的", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                }
                            })
                            .setNegativeButton("", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                }
                            });
                    mMaterialDialog.show();
                    connectstatus.setText("主播已开启直播互动");

                }else
                    connectstatus.setText("尚未开启直播互动");
            }
        });

        nowave = (ImageView) view.findViewById(R.id.nowave);

        gifView1 = (GifView)view.findViewById(R.id.gif1);
        gifView1.setVisibility(View.INVISIBLE);
        gifView1.play();

        button = (Button) view.findViewById(R.id.voicebottom);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    tv_someonespk.setText("当前无人发言");
                    gifView1.setVisibility(View.INVISIBLE);
                    nowave.setVisibility(View.VISIBLE);
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if(!connectstatus.getText().equals("尚未开启直播互动")){
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
