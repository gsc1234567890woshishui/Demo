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
import com.example.administrator.myplayerdemo.test.ActivityComponent;
import com.example.administrator.myplayerdemo.test.ContainerComponent;
import com.example.administrator.myplayerdemo.test.DaggerActivityComponent;
import com.example.administrator.myplayerdemo.test.DaggerContainerComponent;
import com.example.administrator.myplayerdemo.test.Student;
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
        String[] mdatas={"生成二维码图片","输入网络地址生成二维码","实例网络地址和二维码","添加到数据库Db","添加表单验证库的使用例子"};
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
                }
            }
        });
    }
}
