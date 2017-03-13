package com.example.administrator.myplayerdemo.test;

import javax.inject.Inject;

import dagger.Module;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
@Module
public class Student {
    private int age;

    public Student(int age, String name) {
        this.age= age;
        Name = name;
    }
@Inject
    public Student() {
    }

    private String Name;
    public void SetName(String Name){
        this.Name=Name;

    }
    public void SetAge(int age){
        this.age=age;
    }
    public String getName(){
        return this.Name;
    }
    public int getAge(){
        return this.age;
    }
}
