package com.khadse.a.rohit.spidercrush;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Assets {
    static Bitmap background;
    static Bitmap foodbar;
    static Bitmap scorebar;
    static Bitmap spider1;
    static Bitmap spider2;
    static Bitmap spider3;
    static Bitmap bigspider1;
    static Bitmap bigspider2;
    static Bitmap bigspider3;
    static Bitmap ladybug;
    static Bitmap superbug;
    static boolean spider_boolean =false;
    static boolean bigspider =false;
    static int newScore=0;
    static int oldScore=0;
    static int assetcount=0;
    static int randomInt;
    public static MediaPlayer mp;
    static int flag=0;



    // States of the Game Screen
    enum GameState {
        GettingReady,	// play "get ready" sound and start timer, goto next state
        Starting,		// when 3 seconds have elapsed, goto next state
        Running, 		// play the game, when livesLeft == 0 goto next state
        GameEnding,	    // show game over message
        GameOver,		// game is over, wait for any Touch and go back to title activity screen
    };
    static GameState state;		// current state of the game
    static float gameTimer;	    // in seconds
    static int livesLeft;		// 0-3

    static SoundPool soundPool;
    static int sound_getready;
    static int sound_squish,sound_squish2,sound_squish3,sound_squish4,cave;
    static int sound_thump;
    static int no=0;


    //static Spider spider; // try using an array of bugs instead of only 1 bug (so you can have more than 1 on screen at a time)
    static Spider[] spider = new Spider[3];
    static LadyBug lbug;
    static SuperSpider superSpider;

    public static void setScore(){

        if (Assets.oldScore>=Assets.newScore)
        oldScore=oldScore;
        else
        oldScore=newScore;

    }
}
