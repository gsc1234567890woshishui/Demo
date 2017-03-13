package com.example.administrator.myplayerdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.myplayerdemo.R;

/**
 * Created by Administrator on 2017/3/13 0013.
 */

public abstract class BaseAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        doBusy();
    }
    protected abstract void doBusy();
    protected abstract int getLayoutId();
    protected  <T extends View> T findviews(int res){
        return (T) findViewById(res);
    }
}
