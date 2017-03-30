package com.example.administrator.myplayerdemo.activitys;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.administrator.myplayerdemo.forms.FormAct;
import com.example.administrator.myplayerdemo.lock.LockTest;
import com.example.administrator.myplayerdemo.lock.SampleConfirmPatternActivity;
import com.example.administrator.myplayerdemo.tabs.Tab1;
import com.example.administrator.myplayerdemo.test.ActivityComponent;
import com.example.administrator.myplayerdemo.test.ContainerComponent;
import com.example.administrator.myplayerdemo.test.DaggerActivityComponent;
import com.example.administrator.myplayerdemo.test.DaggerContainerComponent;
import com.example.administrator.myplayerdemo.test.Student;
import com.example.administrator.myplayerdemo.views.ParallaxScrollViewActivity;
import com.example.administrator.myplayerdemo.views.Zuniview;
import com.example.parallaxmylib.ParallaxScrollView;

import java.util.List;
import javax.inject.Inject;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public class MainListAct extends ListActivity {
    @Inject
    Student test;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerActivityComponent.builder().build().inject(this);
       test.SetName("fdy");
        test.SetAge(30);
        Log.i("gsc",test.getName());
        String[] mdatas={"生成二维码图片","输入网络地址生成二维码","实例网络地址和二维码","添加到数据库Db","添加表单验证库的使用例子","手势解锁例子","验证手势例子","listview阻尼效果视图","scrollview阻尼效果视图","Tabs(包含下拉刷新框架)","文件下载Demo(Nohttp)","文件下载Demo(xutils3)","文件下载（OKGO）","文件下载（filedown）"};
        getListView().setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,mdatas));

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainListAct.this,MakeQrbitmap.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainListAct.this,Addaddress.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainListAct.this,ThirdCenter.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainListAct.this,AddPage.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainListAct.this,FormAct.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainListAct.this,LockTest.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainListAct.this,SampleConfirmPatternActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainListAct.this,Zuniview.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainListAct.this,ParallaxScrollViewActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainListAct.this,Tab1.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainListAct.this,FileDownappDemo.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainListAct.this,FileDownappXutilsDemo.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainListAct.this,FileDownappOKGODemo.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainListAct.this,FileDownDemo.class));
                        break;
                }
            }
        });
    }
}
