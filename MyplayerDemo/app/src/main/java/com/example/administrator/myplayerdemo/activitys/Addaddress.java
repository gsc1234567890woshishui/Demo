package com.example.administrator.myplayerdemo.activitys;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.myplayerdemo.R;
import com.example.administrator.myplayerdemo.Zxing.CaptureActivity;
import com.example.administrator.myplayerdemo.Zxing.decoding.EncodingUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public class Addaddress extends AppCompatActivity {
    private EditText et;
    private ImageView iv;
    private Button btn;
    private Bitmap qrCode;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 200;
    JCVideoPlayerStandard jcVideoPlayerStandard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address);
        et= (EditText) findViewById(R.id.et);
        iv= (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(qrCode);

            }
        });
        btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Addaddress.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {

                    ActivityCompat.requestPermissions(Addaddress.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else
                {

                    startActivityForResult(new Intent(Addaddress.this, CaptureActivity.class),100);
                }

            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(new Intent(Addaddress.this, CaptureActivity.class),100);

                } else {

                    Toast.makeText(this,"没有权限",Toast.LENGTH_SHORT).show();
                }

            }
            break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:
                if (data!=null){
                    // Bundle bundle = data.getExtras();
                    String result = data.getStringExtra("result");
                    Toast.makeText(this,result,Toast.LENGTH_LONG).show();
                    Log.i("gsc","解析的结果"+result);
                    payMovie(result);
                    ///vv.setVideoUri(Uri.parse(result));

                }
                break;
        }
    }

    private void payMovie(String result) {
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
//        String url="http://live.gslb.letv.com/gslb?stream_id=lb_hkmovie_1300&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1";
//        String url2="http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_livemusic_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1";
//// jcVideoPlayerStandard.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子闭眼睛");
        jcVideoPlayerStandard.setUp(result
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子闭眼睛");
    }

    public void saveImage(Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            sendBroadcast(intent);
            Toast.makeText(getApplicationContext(),fileName+"保存成功！",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void crateqr(View view){
        String url="http://live.gslb.letv.com/gslb?stream_id=lb_hkmovie_1300&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1";
        if (TextUtils.isEmpty(et.getText().toString().trim())){
            et.setText(url);
        }
        qrCode = EncodingUtils.createQRCode(et.getText().toString().trim(), 500, 500,null);
        iv.setImageBitmap(qrCode);
    }
}
