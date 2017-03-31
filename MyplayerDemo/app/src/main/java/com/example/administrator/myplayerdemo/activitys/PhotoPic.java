package com.example.administrator.myplayerdemo.activitys;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.myplayerdemo.base.BaseAct;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class PhotoPic  extends TakePhotoActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onPickFromGallery();

    }
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        Log.i("gsc","操作成功！"+result.getImage().getOriginalPath());
    }
    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Log.i("gsc","操作失败！"+msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        Log.i("gsc","操作取消！");
    }
}
