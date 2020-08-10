package com.example.wenzhou;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {

    private MediaPlayer mp;
    private static final String TAG = "MusicService";

    public MusicService() {
    }

    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        //替代以前使用的onStart()方法，它还可以控制当Service被运行时终止后重新启动Service的方式。
      String musicName=intent.getStringExtra("MusicName");
        String action=intent.getStringExtra("Action");
        switch (action){
            case"play":
                switch (musicName){
                    case"wenzhou":
                        mp=MediaPlayer.create(this,R.raw.wenzhou);
                        break;
                    case"amei":
                        mp=MediaPlayer.create(this,R.raw.amei);
                        break;
                }
                mp.start();
                break;
            case"stop":
                mp.stop();
                break;
        }
        return super.onStartCommand(intent,flags,startId);
    }
}