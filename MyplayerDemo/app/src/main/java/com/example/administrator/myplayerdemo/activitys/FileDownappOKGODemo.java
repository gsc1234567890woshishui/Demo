package com.example.administrator.myplayerdemo.activitys;
import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.myplayerdemo.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.io.File;
import java.util.List;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class FileDownappOKGODemo extends Activity {
    private ProgressBar rb;
    private RingProgressBar b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okgo);
        rb= (ProgressBar) findViewById(R.id.pb);
        b= (RingProgressBar) findViewById(R.id.progress_bar_2);
    }
    public void downapp(View view){
        // 先判断是否有权限。
        if(AndPermission.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // 有权限，直接do anything.
            downApp();
        } else {
            // 申请权限。
            AndPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .send();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，第一个参数是当前Acitivity/Fragment，回调方法写在当前Activity/Framgent。
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    // 成功回调的方法，用注解即可，里面的数字是请求时的requestCode。
    @PermissionYes(100)
    private void getLocationYes(List<String> grantedPermissions) {
        // TODO 申请权限成功。
        downApp();
    }
    // 失败回调的方法，用注解即可，里面的数字是请求时的requestCode。
    @PermissionNo(100)
    private void getLocationNo(List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            // 第一种：用默认的提示语。
            //AndPermission.defaultSettingDialog(this, REQUEST_CODE_SETTING).show();
            Toast.makeText(getApplicationContext(),"权限被拒绝....",Toast.LENGTH_LONG).show();
        }
    }
    private void downApp() {
        OkGo.get("http://api.nohttp.net/download/1.apk")//
                .tag(this)//
                .execute(new FileCallback() {  //文件下载时，可以指定下载的文件目录和文件名
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        // file 即为文件数据，文件保存在指定目录
                        String absolutePath = file.getAbsolutePath();
                        Log.i("gsc","下载成功！"+absolutePath);
                    }
                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调下载进度(该回调在主线程,可以直接更新ui)
                     rb.setProgress((int) currentSize);

                        rb.setMax((int) totalSize);
                        b.setProgress((int) currentSize);

                        b.setMax((int) totalSize);
                    }
                });
    }
}
