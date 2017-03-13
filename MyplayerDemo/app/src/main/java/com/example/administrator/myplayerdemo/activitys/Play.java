package com.example.administrator.myplayerdemo.activitys;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.administrator.myplayerdemo.R;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class Play extends AppCompatActivity {
    JCVideoPlayerStandard jc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        jc= (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        String result="http://baobab.wdjcdn.com/145076769089714.mp4";
        jc.setUp(result
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "测试视频");
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
}
