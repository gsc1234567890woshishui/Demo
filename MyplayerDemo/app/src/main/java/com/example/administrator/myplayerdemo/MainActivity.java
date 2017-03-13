package com.example.administrator.myplayerdemo;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.administrator.myplayerdemo.Zxing.CaptureActivity;
import com.example.administrator.myplayerdemo.Zxing.decoding.EncodingUtils;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 200;

     List<VideoInfo> allVideoList   = new ArrayList<VideoInfo>();
    private List< VideoInfo> temp=new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            if (data!=null){
               // Bundle bundle = data.getExtras();
                String result = data.getStringExtra("result");
                Toast.makeText(this,result,Toast.LENGTH_LONG).show();
                Log.i("gsc","解析的结果"+result);
                ///vv.setVideoUri(Uri.parse(result));

            }

        }

    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    String str="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //13849726788
        setContentView(R.layout.activity_main);

        System.out.println(str);
        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        String url="http://live.gslb.letv.com/gslb?stream_id=lb_hkmovie_1300&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=letv&expect=1";
String url2="http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_livemusic_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1";
// jcVideoPlayerStandard.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子闭眼睛");
        jcVideoPlayerStandard.setUp(url2
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "嫂子闭眼睛");
       // jcVideoPlayerStandard.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
     //vv= (OkVideoView)findViewById(R.id.vv);
       String str="";//="http://baobab.wdjcdn.com/145076769089714.mp4";
        str="http://live.gslb.letv.com/gslb?tag=live&stream_id=lb_livemusic_720p&tag=live&ext=m3u8&sign=live_tv&platid=10&splatid=1009&format=C1S&expect=1";
        Bitmap qrCode = EncodingUtils.createQRCode(str, 500, 500,null);
        ImageView iv= (ImageView) findViewById(R.id.iv);
//        MediaStore.Images.Media.insertImage(getContentResolver(), qrCode, "title", "description");
//        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
        saveImage(qrCode);

        //saveImageToGallery(this,qrCode);
       iv.setImageBitmap(qrCode);

    }

    private void getMeadiaAll() {
        String progress[]={

                MediaStore.Video.Media.DISPLAY_NAME,//视频的名字
                MediaStore.Video.Media.SIZE,//大小
                MediaStore.Video.Media.DURATION,//长度
                MediaStore.Video.Media.DATA,//播放地址
        };

//获取数据提供者,this是上下文
        ContentResolver cr = this.getContentResolver();

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
//有sd卡的情况
            Cursor cursor = cr.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,progress,null,null,null);
            while(cursor.moveToNext()){
// 到视频文件的信息
                String name = cursor.getString(0);//得到视频的名字
                long size = cursor.getLong(1);//得到视频的大小
                long durantion = cursor.getLong(2);//得到视频的时间长度
                String data = cursor.getString(3);//得到视频的路径，可以转化为uri进行视频播放
//使用静态方法获取视频的缩略图
                Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(data, MediaStore.Video.Thumbnails.MINI_KIND);
                VideoInfo videoInfo = new VideoInfo();
//创建视频信息对象
                videoInfo.setVedioName(name);
                videoInfo.setData(data);
                videoInfo.setDuration(durantion);
                videoInfo.setSize(size);
                videoInfo.setThumImage(thumbnail);

                temp.add(videoInfo);
            }
        }
//不论是否有sd卡都要查询手机内存
        Cursor cursor = cr.query(MediaStore.Video.Media.INTERNAL_CONTENT_URI,progress,null,null,null);
        while(cursor.moveToNext()){
// 到视频文件的信息
            String name = cursor.getString(0);//得到视频的名字
            long size = cursor.getLong(1);//得到视频的大小
            long durantion = cursor.getLong(2);//得到视频的时间长度
            String data = cursor.getString(3);//得到视频的路径，可以转化为uri进行视频播放
//使用静态方法获取视频的缩略图
            Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(data, MediaStore.Video.Thumbnails.MINI_KIND);
            VideoInfo videoInfo = new VideoInfo();
//创建视频信息对象
            videoInfo.setData(data);
            videoInfo.setVedioName(name);
            videoInfo.setDuration(durantion);
            videoInfo.setSize(size);
            videoInfo.setThumImage(thumbnail);
            temp.add(videoInfo);
        }
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        Log.i("gsc","保存图片");
        // 首先保存图
        File appDir = new File(Environment.getExternalStorageDirectory().getPath(), "Boohee");
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" +Environment.getExternalStorageDirectory())));
    }
    public void crateqr(View view){
//        String str="http://192.168.10.101:8080/mp4.mp4";
//        Bitmap qrImage = QRUtils.createQRImage(str);
//        ImageView iv= (ImageView) findViewById(R.id.iv);
//          iv.setImageBitmap(qrImage);
   //     startActivity(new Intent(MainActivity.this,));
        getMeadiaAll();

        for(int i=0;i<temp.size();i++){
            Log.i("gsc",temp.get(i).vedioName);
            Log.i("gsc",""+temp.get(i).duration);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else
        {

            startActivityForResult(new Intent(this, CaptureActivity.class),100);
        }
        ;
    }


    private void getVideoFile(final List<VideoInfo> list, File file) {// 获得视频文件

        file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                // sdCard找到视频名称
                String name = file.getName();

                int i = name.indexOf('.');
                if (i != -1) {
                    name = name.substring(i);
                    if (name.equalsIgnoreCase(".mp4")
                            || name.equalsIgnoreCase(".3gp")
                            || name.equalsIgnoreCase(".wmv")
                            || name.equalsIgnoreCase(".ts")
                            || name.equalsIgnoreCase(".rmvb")
                            || name.equalsIgnoreCase(".mov")
                            || name.equalsIgnoreCase(".m4v")
                            || name.equalsIgnoreCase(".avi")
                            || name.equalsIgnoreCase(".m3u8")
                            || name.equalsIgnoreCase(".3gpp")
                            || name.equalsIgnoreCase(".3gpp2")
                            || name.equalsIgnoreCase(".mkv")
                            || name.equalsIgnoreCase(".flv")
                            || name.equalsIgnoreCase(".divx")
                            || name.equalsIgnoreCase(".f4v")
                            || name.equalsIgnoreCase(".rm")
                            || name.equalsIgnoreCase(".asf")
                            || name.equalsIgnoreCase(".ram")
                            || name.equalsIgnoreCase(".mpg")
                            || name.equalsIgnoreCase(".v8")
                            || name.equalsIgnoreCase(".swf")
                            || name.equalsIgnoreCase(".m2v")
                            || name.equalsIgnoreCase(".asx")
                            || name.equalsIgnoreCase(".ra")
                            || name.equalsIgnoreCase(".ndivx")
                            || name.equalsIgnoreCase(".xvid")) {
                        VideoInfo vi = new VideoInfo();
                        vi.setDisplayName(file.getName());
                        vi.setPath(file.getAbsolutePath());
                        list.add(vi);
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getVideoFile(list, file);
                }
                return false;
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(new Intent(this, CaptureActivity.class),100);

                } else {

                    Toast.makeText(this,"没有权限",Toast.LENGTH_SHORT).show();
                }

            }
            break;
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void onNewIntent(Intent intent) {

    }
}
