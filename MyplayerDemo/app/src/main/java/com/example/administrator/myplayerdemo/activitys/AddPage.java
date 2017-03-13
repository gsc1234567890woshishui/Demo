package com.example.administrator.myplayerdemo.activitys;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myplayerdemo.MyApp;
import com.example.administrator.myplayerdemo.R;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10 0010.
 * 添加地址到数据库
 */
@ContentView(value = R.layout.a)
public class AddPage extends Activity {
    private DbManager db;
    @ViewInject(R.id.lv)
    private ListView lv;
    private ArrayAdapter<String> arrayAdapter;
    private String []urls = new String[]{
            "http://live.gslb.letv.com/gslb?stream_id=lb_hkmovie_1300&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1",
            "http://live.gslb.letv.com/gslb?stream_id=lb_ent_1300&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1",
            "http://live.gslb.letv.com/gslb?stream_id=lb_music_1300&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1",
            "http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_dzdy_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1",
            "http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_movie_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1",
            "http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_zxc_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1",
            "http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_cl_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1",
            "http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_comedy_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1",
            "http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_erge_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1",
            "http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_livemusic_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.a);
        //初始化db 配置 设置数据库名字,版本号
        x.view().inject(this);
        initdbconfig();
      selectdb();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(AddPage.this,MakeQrbitmap1.class);
                i.putExtra("url",sum2.get(position));
                startActivity(i);
            }
        });
    }
    public void add(View view){
        for(int i=0;i<urls.length;i++){
            Movies m=new Movies();//创建bean
            m.setName(urls[i]);
            m.setDs("视频"+(i+1));
            try {
                db.save(m);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(getApplicationContext(),"插入数据成功！",Toast.LENGTH_LONG).show();
//         try {
//            Movies m=new Movies();//创建bean
//            m.setName("zhangsan");//Model赋值
//            db.save(m);//保存到db
//
//             arrayAdapter.notifyDataSetChanged();
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
    }
    @Event(value = R.id.tv,type = View.OnClickListener.class)
    private void click(View v){
     Toast.makeText(getApplicationContext(),"点我干毛线",Toast.LENGTH_LONG).show();
    }
    private void initdbconfig() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("ma.db")
                .setAllowTransaction(true)
                .setDbVersion(1)
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                        //更新数据库
                    }
                });
        db = x.getDb(daoConfig);//创建db管理对象
    }
    List<String> sum;
    List<String> sum2;
    public  void selectdb(){
    if (db!=null){
        try {
            List<Movies> all = db.findAll(Movies.class);//查询所有
          sum=new ArrayList<>();
            sum2=new ArrayList<>();
            //db.findById(Movies.class,1); 根据id查询
//            Movies first = db.findFirst(Movies.class);查询第一条数据
//            Log.i("gsc",first.getName()+"******"+first.getId());

            for (int i=0;i<all.size();i++){
                String name = all.get(i).getName();
                int id = all.get(i).getId();
                Log.i("gsc",name+id);
                //sum.add(name);
                sum.add(all.get(i).getDs());
                sum2.add(all.get(i).getName());

            }
            arrayAdapter = new ArrayAdapter<>(AddPage.this, android.R.layout.simple_list_item_1, android.R.id.text1, sum);
            lv.setAdapter(arrayAdapter);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    else{
        Toast.makeText(getApplicationContext(),"请先插入数据！",Toast.LENGTH_LONG).show();
    }

}
}
