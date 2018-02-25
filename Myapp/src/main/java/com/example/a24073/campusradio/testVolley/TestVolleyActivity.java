package com.example.a24073.campusradio.testVolley;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a24073.campusradio.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class TestVolleyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_volley);
    }

    public void get(){
        RequestQueue requestQueue = Volley.newRequestQueue(TestVolleyActivity.this);
        String url = null;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            //正确接收数据回调
            @Override
            public void onResponse(String s) {
                //s即为所返回的
            }
        }, new Response.ErrorListener() {
            //出现错误
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //所做处理
            }
        });
        requestQueue.add(stringRequest);
    }

    public void post(){
        RequestQueue requestQueue = Volley.newRequestQueue(TestVolleyActivity.this);
        String url = null;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //正确接收数据回调
            @Override
            public void onResponse(String s) {
                //s即为所返回的
            }
        }, new Response.ErrorListener() {
            //出现错误
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("value1","param1");//在这添加参数
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void jsonData(){
        RequestQueue requestQueue = Volley.newRequestQueue(TestVolleyActivity.this);
        String url = null;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //jsonObject即为所得
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //错误处理
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void imageRequest(){
        RequestQueue requestQueue = Volley.newRequestQueue(TestVolleyActivity.this);
        String url = null;
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                //bitmap即为所得
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //错误处理
            }
        });
        requestQueue.add(imageRequest);
    }

    public void imageLoader(){
        RequestQueue requestQueue = Volley.newRequestQueue(TestVolleyActivity.this);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

            }
        });
        String url = null;
        //ImageLoader.ImageListener imageListener = imageLoader.getImageListener(null,null,null);//所要显示的控件，默认显示的图片，出现错误显示的图片
        //imageLoader.get(url,imageListener);
    }
}
