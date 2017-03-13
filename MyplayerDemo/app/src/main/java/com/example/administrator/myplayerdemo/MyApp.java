package com.example.administrator.myplayerdemo;

import android.app.Application;

import com.example.administrator.myplayerdemo.test.ActivityComponent;
import com.example.administrator.myplayerdemo.test.Student;

import org.xutils.DbManager;
import org.xutils.x;


/**
 * Created by Administrator on 2017/3/7 0007.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);

    }




}
