package com.example.administrator.myplayerdemo.activitys;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
@Table(name="ma")//和数据库表明对应
public class Movies {
    //isId=true autoGen=true 表示是不是主键，是不是自动增长
    @Column(name="id",isId=true,autoGen=true)
    private int id;
    @Column(name = "ds")
   private String ds;

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "name")
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
