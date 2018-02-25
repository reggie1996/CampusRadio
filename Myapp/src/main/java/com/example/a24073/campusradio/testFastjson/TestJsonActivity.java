package com.example.a24073.campusradio.testFastjson;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a24073.campusradio.R;
import com.example.a24073.campusradio.MyResource.Person2;

public class TestJsonActivity extends Activity {
    private Button button;
    private TextView textView;
    private String jason;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);
        button = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView2);
    }
    public void start(View view){
        RequestQueue requestQueue = Volley.newRequestQueue(TestJsonActivity.this);
        String url = "http://106.14.143.63:8080/testjson/json-Send";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                jason = s;
                textView.setText(jason);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textView.setText("error");
            }
        });
        requestQueue.add(stringRequest);
    }

    public void start2(View view){
        Person2 person2= new Person2(1111,"zhangsan");
        String s = JSON.toJSONString(person2);
        textView.setText(person2.toString()+"\n"+s);
        Person2 person21 = JSON.parseObject(s, Person2.class);
        textView.setText(person21.toString());

    }


}
