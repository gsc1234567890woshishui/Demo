package com.example.administrator.myplayerdemo.test;

import android.app.Activity;

import com.example.administrator.myplayerdemo.activitys.MainListAct;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/10 0010.
 */

@Component
public interface ActivityComponent {
    void inject(MainListAct MainListAct);

}
