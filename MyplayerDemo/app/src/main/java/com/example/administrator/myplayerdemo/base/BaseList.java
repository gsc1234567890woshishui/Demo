package com.example.administrator.myplayerdemo.base;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public abstract class BaseList extends ListActivity {
    private Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx=this;
        doinit();
    }
    protected abstract void doinit();
    protected void startact(Class act){
        startActivity(new Intent(ctx,act));

    }

}
