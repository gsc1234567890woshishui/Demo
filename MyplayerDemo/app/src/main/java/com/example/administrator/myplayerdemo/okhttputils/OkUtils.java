package com.example.administrator.myplayerdemo.okhttputils;

import android.os.Handler;
import android.os.Message;

import com.example.administrator.myplayerdemo.activitys.OKhttpDemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by Administrator on 2017/4/5 0005.
 */
public class OkUtils {
    public OkHttpClient okHttpClient;
    private int ResultOK=200;
    private int ResultFail=205;
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
//                case ResultFail:
//                    break;

            }
        }
    };


      public void doGet(String url, final Map<String,String> map){
        okHttpClient=new OkHttpClient();
         FormBody.Builder builder = new FormBody.Builder();
        doMap(map, builder);
        FormBody formBody = builder.build();
        Request request=new Request.Builder().url(url).put(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handlermsg(call, e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handleSucess(call, response);
            }
        });

    }
    private void handleSucess(Call call, Response response) {
        Message msg=Message.obtain();
        msg.what=ResultOK;
        Object[] objects=new Object[2];
        objects[0]=call;
        objects[1]=response;
        msg.obj=objects;
        handler.handleMessage(msg);
    }

    private void handlermsg(Call call, IOException e) {
        Message msg=Message.obtain();
        msg.what=ResultFail;
        Object[] objects=new Object[2];
        objects[0]=call;
        objects[1]=e;
        msg.obj=objects;
        handler.handleMessage(msg);
    }

    /**
     * @param map
     * @param builder
     * 处理传递的参数
     */
    private void doMap(Map<String, String> map, FormBody.Builder builder) {
        for(Map.Entry<String,String> e:map.entrySet()){
            String key = e.getKey();
            String value = e.getValue();
            builder.add(key,value);

        }
    }
}
