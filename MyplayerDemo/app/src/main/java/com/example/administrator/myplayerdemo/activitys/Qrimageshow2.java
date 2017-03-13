package com.example.administrator.myplayerdemo.activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.myplayerdemo.QRUtils;
import com.example.administrator.myplayerdemo.R;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class Qrimageshow2 extends Activity {
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String str="http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_erge_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1";

        setContentView(R.layout.imge);
        iv= (ImageView) findViewById(R.id.iv);
        Bitmap qrImage = QRUtils.createQRImage(str);
        iv.setImageBitmap(qrImage);
    }
}
