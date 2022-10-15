package com.example.flappy_bird;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;

import java.util.Random;


public class GameView extends View{

    // Background creation
    Handler handler;
    Runnable runnable;
    final int UPDATE_MILLIS = 30;
    Bitmap background;
    Display display;
    Point point;
    int deviceWidth, deviceHeight;
    Rect rect;

    // Dragon placement/movement variables
    Bitmap[] dragons;
    int dragonFrame = 0;
    int velocity = 0;
    int gravity = 3;
    int dragonX, dragonY;

    // Game over variable
    boolean gameState = false;

    // Pipe creation/placement
    Bitmap topPipe, bottomPipe;
    int gap = 450;
    int minTubeOffset, maxTubeOffset;
    int numberOfTubes = 4;
    int distanceBetweenTubes;
    int[] pipeX = new int[numberOfTubes];
    int[] topPipeY = new int[numberOfTubes];
    Random random;
    int pipeVelocity = 8;

    public GameView(Context context) {
        super(context);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        background = BitmapFactory.decodeResource(getResources(),R.drawable.backdrop);
        topPipe = BitmapFactory.decodeResource(getResources(),R.drawable.top_pipe_small);
        bottomPipe = BitmapFactory.decodeResource(getResources(),R.drawable.bottom_pipe_small);
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        deviceWidth = point.x;
        deviceHeight = point.y;
        rect = new Rect(0,0,deviceWidth,deviceHeight);

        dragons = new Bitmap[4];
        dragons[0] = BitmapFactory.decodeResource(getResources(),R.drawable.dragon_small);
        dragons[1] = BitmapFactory.decodeResource(getResources(),R.drawable.dragon2_small);
        dragons[2] = BitmapFactory.decodeResource(getResources(),R.drawable.dragon3_small);
        dragons[3] = BitmapFactory.decodeResource(getResources(),R.drawable.dragon4_small);

        dragonX = deviceWidth/2 - dragons[0].getWidth()/2;
        dragonY = deviceHeight/2 - dragons[0].getHeight()/2;

        distanceBetweenTubes = deviceWidth * 3/4;
        minTubeOffset = gap / 2;
        maxTubeOffset = deviceHeight - minTubeOffset - gap;
        random = new Random();

        for (int i=0;i<numberOfTubes;i++){
            pipeX[i] = deviceWidth + i * distanceBetweenTubes;
            topPipeY[i] = minTubeOffset + random.nextInt(maxTubeOffset - minTubeOffset + 1);
        }




    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawBitmap(background, 0,0,null);
        canvas.drawBitmap(background,null,rect,null);

        switch (dragonFrame){
            case 0:
                dragonFrame = 1;
                break;

            case 1:
                dragonFrame = 2;
                break;

            case 2:
                dragonFrame = 3;
                break;

            case 3:
                dragonFrame = 0;
                break;
        }

        if (gameState) {
            if (dragonY < deviceHeight - dragons[0].getHeight() || velocity < 0) {
                velocity += gravity;
                dragonY += velocity;
            }

            for (int i=0;i<numberOfTubes;i++) {
                pipeX[i] -= pipeVelocity;
                if (pipeX[i] < -topPipe.getWidth()){
                    pipeX[i] += numberOfTubes * distanceBetweenTubes;
                    topPipeY[i] = minTubeOffset + random.nextInt(maxTubeOffset - minTubeOffset + 1);
                }
                canvas.drawBitmap(topPipe, pipeX[i], topPipeY[i] - topPipe.getHeight(), null);
                canvas.drawBitmap(bottomPipe, pipeX[i], topPipeY[i] + gap, null);
            }
        }

        canvas.drawBitmap(dragons[dragonFrame],dragonX,
                dragonY,null);

        handler.postDelayed(runnable,UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN){
             velocity = -25;
             gameState = true;
        }

        return true;
    }
}
