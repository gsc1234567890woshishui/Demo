package com.example.administrator.myplayerdemo;

import android.app.Application;

import com.example.administrator.myplayerdemo.test.ActivityComponent;
import com.example.administrator.myplayerdemo.test.Student;
import com.lzy.okgo.OkGo;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.logging.Level;


/**
 * Created by Administrator on 2017/3/7 0007.
 */
public class MyApp extends Application {
private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        OkGo.init(this);
        OkGo.getInstance()

                // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                .debug("OkGo", Level.INFO, true);
        x.Ext.init(this);
        x.Ext.setDebug(true); // 是否输出debug日志, 开启debug会影响性能.
        //init demo helper
        NoHttp.initialize(this); // NoHttp默认初始化。
        Logger.setDebug(true); // 开启NoHttp调试模式。
        Logger.setTag("gsc"); // 设置NoHttp打印Log的TAG。
    }




}
