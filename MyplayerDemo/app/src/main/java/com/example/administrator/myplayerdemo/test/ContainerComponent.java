package com.example.administrator.myplayerdemo.test;

import com.example.administrator.myplayerdemo.activitys.MainListAct;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/10 0010.
 */

@Component(dependencies = ActivityComponent.class)
public interface ContainerComponent {
    void inject(MainListAct activity);
}
