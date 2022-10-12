package com.example.flappy_bird;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.os.Handler;


public class GameView extends View{

    Handler handler;
    Runnable runnable;
    final int UPDATE_MILLIS = 30;
    Bitmap background;
    Display display;
    Point point;
    int deviceWidth, deviceHeight;
    Rect rect;

    Bitmap[] dragons;
    int dragonFrame = 0;

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
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        deviceWidth = point.x;
        deviceHeight = point.y;
        rect = new Rect(0,0,deviceWidth,deviceHeight);

        dragons = new Bitmap[4];
        dragons[0] = BitmapFactory.decodeResource(getResources(),R.drawable.dragon);
        dragons[1] = BitmapFactory.decodeResource(getResources(),R.drawable.dragon2);
        dragons[2] = BitmapFactory.decodeResource(getResources(),R.drawable.dragon3);
        dragons[3] = BitmapFactory.decodeResource(getResources(),R.drawable.dragon4);


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

        handler.postDelayed(runnable,UPDATE_MILLIS);
    }
}
