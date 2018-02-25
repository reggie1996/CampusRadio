package com.example.a24073.campusradio.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.a24073.campusradio.R;

public class MomentUtilsActivity extends Activity {

    ImageView moment_what;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment_utils);
        moment_what  = (ImageView) findViewById(R.id.moment_what);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if(bundle != null) {
            String str = bundle.getString("str");
            if(str.equals("resource")){
                moment_what.setImageResource(R.mipmap.moment_resource);
            }else if(str.equals("train")){
                moment_what.setImageResource(R.mipmap.moment_train);
            }else if(str.equals("topic")){
                moment_what.setImageResource(R.mipmap.more_topics);
            }
        }else {
            moment_what.setImageResource(R.mipmap.moment_coimgsoon);
        }


    }

}
