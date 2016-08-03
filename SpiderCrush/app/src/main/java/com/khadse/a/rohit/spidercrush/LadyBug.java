package com.khadse.a.rohit.spidercrush;

import android.graphics.Canvas;

public class LadyBug {

    enum LadybugState{

        Dead,
        ComingBackToLife,
        Alive, 			    // in the game
        			// draw dead body on screen
    }
    LadybugState lstate;
    int x,y;				// location on screen (in screen coordinates)
    double speed;			// speed of bug (in pixels per second)
    // All times are in seconds
    float timeToBirth;		// # seconds till birth
    float startBirthTimer;	// starting timestamp when decide to be born
    float deathTime;		// time of death
    float animateTimer;
    public LadyBug(){
        lstate = LadybugState.Dead;
    }


    // Bug birth processing
    public void birth (Canvas canvas) {
        // Bring a bug to life?
        if (lstate == LadybugState.Dead) {
            // Set it to coming alive
            lstate = LadybugState.ComingBackToLife;
            // Set a random number of seconds before it comes to life

            timeToBirth = (float)Math.random () * 5;
            // Note the current time
            startBirthTimer = System.nanoTime() / 1000000000f;
        }
        // Check if bug is alive yet
        else if (lstate == LadybugState.ComingBackToLife) {
            float curTime = System.nanoTime() / 1000000000f;
            // Has birth timer expired?
            if (curTime - startBirthTimer >= timeToBirth) {
                // If so, then bring bug to life
                lstate = LadybugState.Alive;
                // Set bug starting location at top of screen
                x = (int)(Math.random() * canvas.getWidth());
                // Keep entire bug on screen
                if (x < Assets.ladybug.getWidth()/2)
                    x = Assets.ladybug.getWidth()/2;
                else if (x > canvas.getWidth() - Assets.ladybug.getWidth()/2)
                    x = canvas.getWidth() - Assets.ladybug.getWidth()/2;
                y = 0;
                // Set speed of this bug
                speed = canvas.getHeight() / 3; // no faster than 1/4 a screen per second
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
        if (lstate == LadybugState.Alive) {
            // Get elapsed time since last call here
            float curTime = System.nanoTime() / 1000000000f;
            float elapsedTime = curTime - animateTimer;
            animateTimer = curTime;
            // Compute the amount of pixels to move (vertically down the screen)

            //while ( state == SpiderState.Alive) {
            y += (speed * elapsedTime);
            // Draw bug on screen
            canvas.drawBitmap(Assets.ladybug, x, y, null);

            //}

            // ADD CODE HERE - Draw each frame of animation as appropriate - don't just draw 1 frame

            // Has it reached the bottom of the screen?
            if (y >= canvas.getHeight()) {
                // Kill the bug
                lstate = LadybugState.Dead;
                // Subtract 1 life
            }
        }

    }

    // Process touch to see if kills bug - return true if bug killed
    public boolean touched (Canvas canvas, int touchx, int touchy) {
        boolean touched = false;
        // Make sure this bug is alive
        if (lstate == LadybugState.Alive) {
            // Compute distance between touch and center of bug
            float dis = (float)(Math.sqrt ((touchx - x) * (touchx - x) + (touchy - y) * (touchy - y)));
            // Is this close enough for a kill?
            if (dis <= Assets.ladybug.getWidth()*0.75f) {
                // need to draw dead body on screen for a while
                touched = true;
                if (touched){
                    Assets.state = Assets.GameState.GameEnding;
                    MainThread m = new MainThread();
                    m.run();
                }
                // Record time of death
                deathTime = System.nanoTime() / 1000000000f;

            }
        }
        return (touched);
    }

}

