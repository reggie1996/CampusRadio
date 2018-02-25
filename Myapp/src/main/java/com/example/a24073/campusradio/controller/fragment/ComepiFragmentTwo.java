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

import com.cunoraz.gifview.library.GifView;
import com.example.a24073.campusradio.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComepiFragmentTwo extends Fragment {

    Button button;
    GifView gifView1;
    ImageView nowave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comepi_fragment_two, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = (Button) view.findViewById(R.id.voicebottom);
        gifView1 = (GifView) view.findViewById(R.id.gif1);
        nowave = (ImageView) view.findViewById(R.id.nowave);

        gifView1.setVisibility(View.INVISIBLE);
        gifView1.play();

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    gifView1.setVisibility(View.INVISIBLE);
                    nowave.setVisibility(View.VISIBLE);
                }
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                        gifView1.setVisibility(View.VISIBLE);
                        nowave.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
    }
}
