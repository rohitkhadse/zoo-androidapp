package com.khadse.a.rohit.spidercrush;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    MainView v;
    //MediaPlayer mp;
    public MainActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable the title
        requestWindowFeature (Window.FEATURE_NO_TITLE);  // use the styles.xml file to set no title bar
        // Make full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Start the view
        v = new MainView(this);
        setContentView(v);

        Assets.mp = MediaPlayer.create(this, R.raw.game_music);
        if (Assets.mp!=null){
            Assets.mp.setLooping(true);
            Assets.mp.start();
        }
    }

    @Override
    protected void onPause () {
        super.onPause();
        v.pause();
    }

    @Override
    protected void onResume () {
        super.onResume();
        v.resume();
    }

public void stopMusic(){
    if (Assets.mp != null) {
        Assets.mp.stop();
        Assets.mp.release();
        Assets.mp = null;
    }
}
}