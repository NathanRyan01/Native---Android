package com.example.virg.thesis;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import java.io.File;

public class MediaActivity extends MainActivity {

    MediaPlayer mp = new MediaPlayer();
    MediaController vp;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        videoView = findViewById(R.id.videoView2);
        mp = MediaPlayer.create(this, R.raw.queens);
        vp = new MediaController(this);
        vp.setAnchorView(videoView);
        videoView.setMediaController(vp);
    }

    public void StartAudioBtn(View view) {
        try {
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void StopAudioBtn(View view) {
        mp.stop();
    }

    public void StartVideoBtn(View view) {
        try {
            videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.audio);
            videoView.requestFocus();
            videoView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void StopVideoBtn(View view) {
        videoView.pause();
    }
}




