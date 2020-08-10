package com.example.wenzhou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;


public class VActivity extends AppCompatActivity {
    private VideoView videoView;
    private MediaController mediaController;
    ImageView play1,play2,stop1,stop2;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v);
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView = (VideoView) findViewById(R.id.videoView);
        play1=findViewById(R.id.play1);
        play2=findViewById(R.id.play2);
        stop1=findViewById(R.id.stop1);
        stop2=findViewById(R.id.stop2);
        intent=new Intent(VActivity.this,MusicService.class);
        mediaController = new MediaController(this);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.wz1;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        videoView.start();

        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("MusicName", "wenzhou");
                intent.putExtra("Action", "play");
                startService(intent);
            }
        });
        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("MusicName", "amei");
                intent.putExtra("Action", "play");
                startService(intent);
            }
        });
        stop1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                intent.putExtra("Action","stop");
                startService(intent);
            }
        });
        stop2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                intent.putExtra("Action","stop");
                startService(intent);
            }
        });
    }
}