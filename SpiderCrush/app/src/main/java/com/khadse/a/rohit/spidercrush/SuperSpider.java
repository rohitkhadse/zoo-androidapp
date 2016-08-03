package com.khadse.a.rohit.spidercrush;

import android.graphics.Canvas;

public class SuperSpider {
    enum SuperSpiderState{

        Dead,
        ComingBackToLife,
        Alive, 			    // in the game
        DrawDead// draw dead body on screen
    }
    SuperSpiderState ss_state;
    int x,y;				// location on screen (in screen coordinates)
    double speed;			// speed of bug (in pixels per second)
    // All times are in seconds
    float timeToBirth;		// # seconds till birth
    float startBirthTimer;	// starting timestamp when decide to be born
    float deathTime;		// time of death
    float animateTimer;
    public SuperSpider(){
        ss_state = SuperSpiderState.Dead;
    }


    // Bug birth processing
    public void birth (Canvas canvas) {
        // Bring a bug to life?
        if (ss_state == SuperSpiderState.Dead) {
            // Set it to coming alive
            ss_state = SuperSpiderState.ComingBackToLife;
            // Set a random number of seconds before it comes to life
                timeToBirth = (float)Math.random () * 5;
            // Note the current time
            startBirthTimer = System.nanoTime() / 1000000000f;
        }
        // Check if bug is alive yet
        else if (ss_state == SuperSpiderState.ComingBackToLife) {
            float curTime = System.nanoTime() / 1000000000f;
            // Has birth timer expired?
            if (curTime - startBirthTimer >= timeToBirth) {
                // If so, then bring bug to life
                ss_state = SuperSpiderState.Alive;
                // Set bug starting location at top of screen
                x = (int)(Math.random() * canvas.getWidth());
                // Keep entire bug on screen
                if (x < Assets.bigspider1.getWidth()/2)
                    x = Assets.bigspider1.getWidth()/2;
                else if (x > canvas.getWidth() - Assets.bigspider1.getWidth()/2)
                    x = canvas.getWidth() - Assets.bigspider1.getWidth()/2;
                else if (x < Assets.bigspider2.getWidth()/2)
                    x = Assets.bigspider2.getWidth()/2;
                else if (x > canvas.getWidth() - Assets.bigspider2.getWidth()/2)
                    x = canvas.getWidth() - Assets.bigspider2.getWidth()/2;
                y = 0;
                // Set speed of this bug
                speed = canvas.getHeight() / 4; // no faster than 1/4 a screen per second
                // subtract a random amount off of this so some bugs are a little slower
                // ADD CODE HERE
                // Record timestamp of this bug being born
                animateTimer = curTime;
            }
        }
    }

    // Bug movement processing
    public void move (Canvas canvas) {
        // Make sure this bug is alive
        if (ss_state == SuperSpiderState.Alive) {
            // Get elapsed time since last call here
            float curTime = System.nanoTime() / 1000000000f;
            float elapsedTime = curTime - animateTimer;
            animateTimer = curTime;
            // Compute the amount of pixels to move (vertically down the screen)

            //while ( state == SpiderState.Alive) {
            y += (speed * elapsedTime);
            // Draw bug on screen
            if (Assets.bigspider == false) {
                canvas.drawBitmap(Assets.bigspider1, x, y, null);
                Assets.bigspider = true;
            } else if (Assets.bigspider) {
                canvas.drawBitmap(Assets.bigspider2, x, y, null);
                Assets.bigspider = false;
            }

            //}

            // ADD CODE HERE - Draw each frame of animation as appropriate - don't just draw 1 frame

            // Has it reached the bottom of the screen?
            if (y >= canvas.getHeight()) {
                // Kill the bug
                ss_state = SuperSpiderState.Dead;
                // Subtract 1 life
            }
        }

    }

    // Process touch to see if kills bug - return true if bug killed
    public boolean touched (Canvas canvas, int touchx, int touchy) {
        boolean touched = false;
        // Make sure this bug is alive
        if (ss_state == SuperSpiderState.Alive) {
            // Compute distance between touch and center of bug
            float dis = (float)(Math.sqrt ((touchx - x) * (touchx - x) + (touchy - y) * (touchy - y)));
            // Is this close enough for a kill?
            if (dis <= Assets.bigspider1.getWidth()*0.75f || dis <= Assets.bigspider2.getWidth()*0.75f) {
                touched = true;
                Assets.flag++;
                if(Assets.flag==4) {
                    ss_state = SuperSpiderState.DrawDead;    // need to draw dead body on screen for a while
                    //touched = true;
                    Assets.flag=0;
                }
                if (touched){
                    Assets.newScore= Assets.newScore + 10;
                }
                // Record time of death
                deathTime = System.nanoTime() / 1000000000f;

            }
        }
        return (touched);
    }

    public void drawDead (Canvas canvas) {
        if (ss_state == SuperSpiderState.DrawDead) {
            canvas.drawBitmap(Assets.bigspider3, x,  y, null);
            // Get time since death
            float curTime = System.nanoTime() / 1000000000f;
            float timeSinceDeath = curTime - deathTime;
            // Drawn dead body long enough (4 seconds) ?
            if (timeSinceDeath > 4)
                ss_state = SuperSpiderState.Dead;

        }
    }

}
