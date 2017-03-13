package com.example.administrator.myplayerdemo.test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/10 0010.
 */

@Module
public class ActivityModule {
    @Provides
    public Student providePersonModel() {
        return new Student(21, "EthanCO");
    }
}
