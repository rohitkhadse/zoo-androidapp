package com.khadse.a.rohit.spidercrush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TitleActivity extends AppCompatActivity implements View.OnClickListener {

    private Button playbutton, hscorebutton;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        playbutton = (Button) findViewById(R.id.button_play);
        hscorebutton = (Button) findViewById(R.id.button_hscore);
        playbutton.setOnClickListener(this);
        hscorebutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_play:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.button_hscore:
                startActivity(new Intent(this, PrefActivity.class));
                break;
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        MainActivity ma = new MainActivity();
        ma.stopMusic();
        Assets.setScore();
        Assets.newScore = 0;
    }
}
