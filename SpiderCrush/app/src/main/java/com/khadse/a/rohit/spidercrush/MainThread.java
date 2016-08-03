package com.khadse.a.rohit.spidercrush;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.util.Timer;

public class MainThread extends Thread {
    private SurfaceHolder holder;
    private Handler handler;		// required for running code in the UI thread
    private boolean isRunning = false;
    Context context;
    Paint paint;
    int touchx, touchy;	// x,y of touch event
    boolean touched;	// true if touch happened
    private static final Object lock = new Object();
    int a=0,b=0,c=0;
    boolean spiderkilled_a,spiderkilled_b,spiderkilled_c,ladyBugKilled,superSpiderKilled;

    public MainThread (SurfaceHolder surfaceHolder, Context context) {
        holder = surfaceHolder;
        this.context = context;
        handler = new Handler();
        touched = false;
    }

    public MainThread() {

    }

    public void setRunning(boolean b) {
        isRunning = b;	// no need to synchronize this since this is the only line of code to writes this variable
    }

    // Set the touch event x,y location and flag indicating a touch has happened
    public void setXY (int x, int y) {
        synchronized (lock) {
            touchx = x;
            touchy = y;
            this.touched = true;
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            // Lock the canvas before drawing
            Canvas canvas = holder.lockCanvas();
            if (canvas != null) {
                // Perform drawing operations on the canvas
                render(canvas);
                // After drawing, unlock the canvas and display it
                holder.unlockCanvasAndPost (canvas);
            }
        }
    }

    // Loads graphics, etc. used in game
    private void loadData (Canvas canvas) {
        Bitmap bmp;
        int newWidth, newHeight;
        float scaleFactor;

        // Create a paint object for drawing vector graphics
        paint = new Paint();

        // Load score bar
        // ADD CODE HERE

        // Load food bar
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.foodbar);
        // Compute size of bitmap needed (suppose want height = 10% of screen height)
        newHeight = (int)(canvas.getHeight() * 0.1f);
        // Scale it to a new size
        Assets.foodbar = Bitmap.createScaledBitmap (bmp, canvas.getWidth(), newHeight, false);
        // Delete the original
        bmp = null;

        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.titlebar1);
        // Compute size of bitmap needed (suppose want height = 10% of screen height)
        newHeight = (int)(canvas.getHeight() * 0.1f);
        // Scale it to a new size
        Assets.scorebar = Bitmap.createScaledBitmap (bmp, canvas.getWidth(), newHeight, false);
        // Delete the original
        bmp = null;


        // Load spider1
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.spider1_250);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.2f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.spider1 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        // Load the other bitmaps similarly
        // ...
        // Load spider1
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.spider2_250);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.2f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.spider2 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.ladybug);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.2f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.ladybug = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        // Load spider3 (dead spider)
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.spider3_250);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.2f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.spider3 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.bigspider1_250);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.3f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.bigspider1 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        // Load the other bitmaps similarly
        // ...
        // Load spider1
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.bigspider2_250);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.3f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.bigspider2 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        // Load spider3 (dead spider)
        bmp = BitmapFactory.decodeResource (context.getResources(), R.drawable.bigspider3_250);
        // Compute size of bitmap needed (suppose want width = 20% of screen width)
        newWidth = (int)(canvas.getWidth() * 0.3f);
        // What was the scaling factor to get to this?
        scaleFactor = (float)newWidth / bmp.getWidth();
        // Compute the new height
        newHeight = (int)(bmp.getHeight() * scaleFactor);
        // Scale it to a new size
        Assets.bigspider3 = Bitmap.createScaledBitmap (bmp, newWidth, newHeight, false);
        // Delete the original
        bmp = null;

        // Create a spider
        for (Assets.no=0;Assets.no<3;Assets.no++)
        Assets.spider[Assets.no] = new Spider();
        Assets.lbug = new LadyBug();
        Assets.superSpider = new SuperSpider();
    }

    // Load specific background screen
    private void loadBackground (Canvas canvas, int resId) {
        // Load background
        Bitmap bmp = BitmapFactory.decodeResource (context.getResources(), resId);
        // Scale it to fill entire canvas
        Assets.background = Bitmap.createScaledBitmap (bmp, canvas.getWidth(), canvas.getHeight(), false);
        // Delete the original
        bmp = null;
    }

    private void render (final Canvas canvas) {
        int i, x, y;

        switch (Assets.state) {
            case GettingReady:
                // Load a special "getting ready screen"
                loadBackground (canvas, R.drawable.scaredperson);
                // Load data and other graphics needed by game
                loadData(canvas);
                // Draw the background screen
                canvas.drawBitmap (Assets.background, 0, 0, null);
                // Play a sound effect
                Assets.soundPool.play(Assets.sound_getready, 1, 1, 1, 0, 1);
                // Start a timer
                Assets.gameTimer = System.nanoTime() / 1000000000f;
                // Go to next state
                Assets.state = Assets.GameState.Starting;
                break;
            case Starting:
                // Draw the background screen
                canvas.drawBitmap (Assets.background, 0, 0, null);
                // Has 3 seconds elapsed?
                float currentTime = System.nanoTime() / 1000000000f;
                if (currentTime - Assets.gameTimer >= 3) {
                    // Load game play background
                    loadBackground (canvas, R.drawable.wood);
                    // Goto next state
                    Assets.state = Assets.GameState.Running;
                }
                break;
            case Running:
                // Draw the background screen
                canvas.drawBitmap (Assets.background, 0, 0, null);
                // Draw the score bar at top of screen
                // ADD CODE HERE
                canvas.drawBitmap (Assets.scorebar, 0, (1/2)*Assets.scorebar.getHeight(), null);

                paint.setColor(Color.parseColor("#DD2C00"));
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(100);
                paint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                canvas.drawText("Score:"+Assets.newScore,10,120,paint);
                // Draw the foodbar at bottom of screen
                canvas.drawBitmap (Assets.foodbar, 0, canvas.getHeight()-Assets.foodbar.getHeight(), null);
                // Draw one circle for each life at top right corner of screen
                // Let circle radius be 5% of width of screen
                int radius = (int)(canvas.getWidth() * 0.05f);
                int spacing = 8; // spacing in between circles
                x = canvas.getWidth() - radius - spacing;	// coordinates for rightmost circle to draw
                y = radius + spacing + 30;
                for (i=0; i<Assets.livesLeft; i++) {
                    paint.setColor(Color.parseColor("#DD2C00"));
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(x, y, radius, paint);
                    paint.setColor(Color.BLACK);
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawCircle(x, y, radius, paint);
                    // Reposition to draw the next circle to the left
                    x -= (radius*2 + spacing);
                }

                // Process a touch
                if (touched) {
                    // Set touch flag to false since we are processing this touch now
                    touched = false;
                    // See if this touch killed a spider
                    spiderkilled_a = Assets.spider[0].touched(canvas, touchx, touchy);
                    spiderkilled_b = Assets.spider[1].touched(canvas, touchx, touchy);
                    spiderkilled_c = Assets.spider[2].touched(canvas, touchx, touchy);
                    ladyBugKilled = Assets.lbug.touched(canvas, touchx, touchy);
                    superSpiderKilled = Assets.superSpider.touched(canvas,touchx,touchy);


                    if (spiderkilled_a == false && spiderkilled_b == false && spiderkilled_c == false && ladyBugKilled == false && superSpiderKilled == false){
                        Assets.soundPool.play(Assets.sound_thump, 1, 1, 1, 0, 1);
                    }

                    if (touched == false ) {
                        if (a == 0)  {
                            if (spiderkilled_a){
                                Spider.randomSound();
                                if (Assets.randomInt == 0)
                                Assets.soundPool.play(Assets.sound_squish, 1, 1, 1, 0, 1);
                                else if (Assets.randomInt == 1)
                                    Assets.soundPool.play(Assets.sound_squish3, 1, 1, 1, 0, 1);
                                else if (Assets.randomInt == 2)
                                    Assets.soundPool.play(Assets.sound_squish4, 1, 1, 1, 0, 1);
                              }

                        }
                        if (b == 1)  {
                            if (spiderkilled_b){
                                Spider.randomSound();
                                if (Assets.randomInt == 0)
                                    Assets.soundPool.play(Assets.sound_squish, 1, 1, 1, 0, 1);
                                else if (Assets.randomInt == 1)
                                    Assets.soundPool.play(Assets.sound_squish3, 1, 1, 1, 0, 1);
                                else if (Assets.randomInt == 2)
                                    Assets.soundPool.play(Assets.sound_squish4, 1, 1, 1, 0, 1);
                            }
                        }
                        if (c == 2)  {

                            if (spiderkilled_c){
                                Spider.randomSound();
                                if (Assets.randomInt == 0)
                                    Assets.soundPool.play(Assets.sound_squish, 1, 1, 1, 0, 1);
                                else if (Assets.randomInt == 1)
                                    Assets.soundPool.play(Assets.sound_squish3, 1, 1, 1, 0, 1);
                                else if (Assets.randomInt == 2)
                                    Assets.soundPool.play(Assets.sound_squish4, 1, 1, 1, 0, 1);
                            }

                        }

                    }

                   // boolean ladyBugKilled = Assets.lbug.touched(canvas, touchx, touchy);
                    if (ladyBugKilled)
                        Assets.soundPool.play(Assets.sound_squish2, 1, 6, 6, 0, 1);
                    if (superSpiderKilled)
                        Assets.soundPool.play(Assets.cave, 1, 6, 6, 0, 1);

                }

                int count=0;
                for (Assets.no=0;Assets.no<3;Assets.no++) {
                    // Draw dead spiders on screen
                    Assets.spider[Assets.no].drawDead(canvas);
                    // Move spiders on screen
                    Assets.spider[Assets.no].move(canvas);
                    // Bring a dead spider to life?
                    Assets.spider[Assets.no].birth(canvas);
                    count++;
                    if (count == 1)
                       a = 0;
                    if (count == 2)
                        b= 1;
                    if (count == 3)
                        c=2;
                }
                currentTime = System.nanoTime() / 1000000000f;

                // ADD MORE CODE HERE TO PLAY GAME
                if (currentTime - Assets.gameTimer >= 10){
                    Assets.lbug.birth(canvas);
                }
                Assets.lbug.move(canvas);
                if (currentTime - Assets.gameTimer >= 20){
                    Assets.superSpider.birth(canvas);
                }
                Assets.superSpider.drawDead(canvas);
                Assets.superSpider.move(canvas);


                // Are no lives left?
                if (Assets.livesLeft == 0)
                    // Goto next state
                    Assets.state = Assets.GameState.GameEnding;
                break;
            case GameEnding:
                // Show a game over message
                handler.post(new Runnable() {
                    public void run() {
                        Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show();
                        if (Assets.newScore > Assets.oldScore)
                        Toast.makeText(context, "New High Score..!!", Toast.LENGTH_SHORT).show();
                        MainActivity ma = new MainActivity();
                        ma.stopMusic();
                    }
                });
                // Goto next state
                Assets.state = Assets.GameState.GameOver;
                break;
            case GameOver:
                // Fill the entire canvas' bitmap with 'black'
                //canvas.drawColor(Color.BLACK);
                canvas.drawBitmap (Assets.background, 0, 0, null);
                loadBackground (canvas, R.drawable.gameover);

                break;
        }
    }
}