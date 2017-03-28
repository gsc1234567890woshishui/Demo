package com.example.administrator.myplayerdemo.activitys;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.myplayerdemo.SSLContextUtil;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import javax.net.ssl.SSLContext;

/**
 * Created by Administrator on 2017/3/24 0024.
 * nohttps 请求实例
 * 学习使用开源项目Nohttp
 */
public class NohttpDemo  extends ListActivity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] madatas={"https请求带证书","https不带证书"};
        getListView().setAdapter(new ArrayAdapter<String>(NohttpDemo.this,android.R.layout.simple_list_item_1,android.R.id.text1,madatas));
        getListView().setOnItemClickListener(this);
        mQueue=NoHttp.newRequestQueue();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                httpsTest();
                break;
            case 1:
                httpsTestNo();
                break;
        }
    }
    /**
     * 没有证书的https
     */
    private void httpsTestNo() {
    }
    private RequestQueue mQueue;
    /**
     * 带证书的https
     */
    private void httpsTest() {

        Request<String> httpsRequest = NoHttp.createStringRequest("https://kyfw.12306.cn/otn/", RequestMethod.GET);
        SSLContext sslContext = SSLContextUtil.getSSLContext();

        // 主要是需要一个SocketFactory对象，这个对象是java通用的，具体用法还请Google、Baidu。
        if (sslContext != null)
            httpsRequest.setSSLSocketFactory(sslContext.getSocketFactory());
       // request(0, httpsRequest, this, false, true);
        mQueue.add(2, httpsRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                Log.i("gsc","https_onStart");
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                Log.i("gsc","https_onSucceed"+response.get());
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Log.i("gsc","https_onFailed"+response.get());
            }
            @Override
            public void onFinish(int what) {
                Log.i("gsc","https_onFinish");
            }
        });
    }
}
